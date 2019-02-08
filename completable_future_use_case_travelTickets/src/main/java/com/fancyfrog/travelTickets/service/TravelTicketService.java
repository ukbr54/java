package com.fancyfrog.travelTickets.service;

import com.fancyfrog.travelTickets.common.ExternalApiConstants;
import com.fancyfrog.travelTickets.vo.placeDetails.PlaceDetail;
import com.fancyfrog.travelTickets.vo.ticket1_S.Ticket1_S_TourResponse;
import com.fancyfrog.travelTickets.ws.WSPlaceDetails;
import com.fancyfrog.travelTickets.ws.WSPoiDetails;
import com.fancyfrog.travelTickets.ws.request.WSActivity;
import com.fancyfrog.travelTickets.ws.request.WSTourRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TravelTicketService {

    private String placeDetailBaseUrl;
    private String placeDetailApiKey;
    private String findPlaces;
    private String poiDetailUrl;
    private String nearby;
    private final String MEDIA_TYPE_FORMAT = "json";
    private final String PLACE_ID = "placeid";
    private final String KEY = "key";

    private String ticket1_S_BaseUrl;
    private String ticket1_S_apiKey;
    private String ticket1_S_tours ;
    private final String PARENT_PLACE_ID = "parent_place_id";
    private final String QUERY = "query";

    private @Autowired Environment env;
    private @Autowired RestTemplate restTemplate;
    private static ExecutorService service = Executors.newCachedThreadPool();

    @PostConstruct
    public void init(){
        this.placeDetailBaseUrl = env.getProperty("travel.poiDetailsApis.baseurl");
        this.placeDetailApiKey = env.getProperty("travel.poiDetailsApis.apikey");
        this.findPlaces = env.getProperty("travel.poiDetailsApis.findPlace");
        this.poiDetailUrl = env.getProperty("travel.poiDetailsApis.poiDetail");
        this.nearby = env.getProperty("travel.poiDetailsApis");

        this.ticket1_S_BaseUrl = env.getProperty("travel.ticket1_S.baseurl");
        this.ticket1_S_apiKey = env.getProperty("travel.ticket1_S.apiKey");
        this.ticket1_S_tours = env.getProperty("travel.ticket1_S.tour");
    }

    public WSPlaceDetails getPlaceDetailsWithTickets(WSTourRequest tourRequest) {
        WSPlaceDetails placeDetails = new WSPlaceDetails();
        List<WSActivity> wsActivities = new ArrayList<>();

        //Independent Future to get the Place Details
        long start = System.currentTimeMillis();
        CompletableFuture<Void> placeDetailsFuture = getPlaceDetails(tourRequest.getPoiId())
                .thenAccept(poi -> {
                    log.info("fetching the details: {}", poi.getResult().getName());
                    placeDetails.setPoiDetails(new WSPoiDetails(poi.getResult()));
                });
        if(!placeDetailsFuture.isDone()){
            placeDetailsFuture.join();
        }
        log.info("looking for place details for: {} take time: {}",tourRequest.getPoiId(),(System.currentTimeMillis() - start));

        //
        long start1 = System.currentTimeMillis();
        CompletableFuture<Void> ticket1_S_future = getTicket1_SToursDetails(tourRequest.getTicket1_id(), tourRequest.getQuery())
                .thenApply(ticket1 ->{
                       log.debug("The number of entries for {} is {}",tourRequest.getQuery(),ticket1.getData().getTours().size());
                       return ticket1.getData().getTours().stream()
                            .filter(tour -> tour.getTitle().contains(tourRequest.getQuery()))
                            .collect(Collectors.toList());
                    }
                ).thenAccept(matchedActivities ->
                       wsActivities.addAll(matchedActivities.stream().map(WSActivity::new).collect(Collectors.toList()))
                );

        if(!ticket1_S_future.isDone()){
            ticket1_S_future.join();
        }
        log.info("looking for ticket1_s for: {} take time: {}",tourRequest.getTicket1_id(),(System.currentTimeMillis() - start1));

        placeDetails.setActivities(wsActivities);
        return placeDetails;
    }

    private CompletableFuture<Ticket1_S_TourResponse> getTicket1_SToursDetails(String id,String query){
        HttpHeaders headers = generateHeaders();
        headers.set(ExternalApiConstants.X_API_KEY,this.ticket1_S_apiKey);
        HttpEntity<String> entity = new HttpEntity<>(ExternalApiConstants.PARAMETERS, headers);
        return CompletableFuture.supplyAsync(() ->{
           log.info("Calling the Ticket1_S_ API");
           return restTemplate.exchange(generateTicket1_S_url(id,query),HttpMethod.GET,entity,Ticket1_S_TourResponse.class).getBody();
        },service);
    }

    private CompletableFuture<PlaceDetail> getPlaceDetails(String poiId){
        HttpHeaders headers = generateHeaders();
        HttpEntity<String> entity = new HttpEntity<>(ExternalApiConstants.PARAMETERS, headers);
        return CompletableFuture.supplyAsync(() ->{
                 log.info("Poi place Api get called");
                 return restTemplate.exchange(generatePlaceDetailUrl(poiId),HttpMethod.GET,entity,PlaceDetail.class).getBody();
             },service
        );
    }

    private String generateTicket1_S_url(String id,String query){
        return new StringBuilder(this.ticket1_S_BaseUrl)
                .append(this.ticket1_S_tours).append(ExternalApiConstants.QUESTION_MARK)
                .append(PARENT_PLACE_ID).append(ExternalApiConstants.EQUALS_TO).append(id)
                .append(ExternalApiConstants.AMPERSAND).append(QUERY).append(ExternalApiConstants.EQUALS_TO).append(query)
                .toString();
    }

    private String generatePlaceDetailUrl(String poiId){
        StringBuilder url = new StringBuilder(this.placeDetailBaseUrl);
        url.append(this.poiDetailUrl).append(this.MEDIA_TYPE_FORMAT).append(ExternalApiConstants.QUESTION_MARK)
                .append(PLACE_ID).append(ExternalApiConstants.EQUALS_TO).append(poiId).append(ExternalApiConstants.AMPERSAND)
                .append(KEY).append(ExternalApiConstants.EQUALS_TO).append(this.placeDetailApiKey);
        return url.toString();
    }

    private HttpHeaders generateHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(ExternalApiConstants.Accept,MediaType.APPLICATION_JSON_UTF8_VALUE);
        return headers;
    }
}
