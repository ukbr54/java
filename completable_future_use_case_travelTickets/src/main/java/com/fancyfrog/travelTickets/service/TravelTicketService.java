package com.fancyfrog.travelTickets.service;

import com.fancyfrog.travelTickets.common.ExternalApiConstants;
import com.fancyfrog.travelTickets.model.ticket3.CityDetails;
import com.fancyfrog.travelTickets.repositories.CityDetailsRepository;
import com.fancyfrog.travelTickets.vo.placeDetails.PlaceDetail;
import com.fancyfrog.travelTickets.vo.ticket1_S.Ticket1_S_TourResponse;
import com.fancyfrog.travelTickets.vo.ticket2_T.Ticket2_T_Response;
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
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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

    private String ticket2_T_baseUrl;
    private String ticket2_T_account;
    private String ticket2_T_token;
    private String ticket2_T_tour;
    private final String POI_ID = "poi_id";
    private final String LOCATION_IDS = "location_ids";
    private final String ANNOTATE = "annotate";
    private final String PRICE_DEFAULT_VALUE = "USD";
    private final String CONVERTED_PRICE = "converted_price";


    private @Autowired Environment env;
    private @Autowired RestTemplate restTemplate;
    private @Autowired CityDetailsRepository cityDetailsRepository;
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

        this.ticket2_T_baseUrl = env.getProperty("travel.ticket2_T.baseurl");
        this.ticket2_T_account = env.getProperty("travel.ticket2_T.account");
        this.ticket2_T_token = env.getProperty("travel.ticket2_T.token");
        this.ticket2_T_tour = env.getProperty("travel.ticket2_T.tour");
    }

    public WSPlaceDetails getPlaceDetailsWithTickets(WSTourRequest tourRequest) {
        WSPlaceDetails placeDetails = new WSPlaceDetails();
        List<WSActivity> wsActivities = new ArrayList<>();

        //Independent Future to get the Place Details
        processPoiDetails(placeDetails,tourRequest);

        CompletableFuture<Void> ticket3Future = getTicket3_V_tourDetails(tourRequest.getTicket3_id())
                .thenApply(ticket3 -> {
                    return ticket3.getTickets().stream()
                            .filter(tour -> tour.getProductName().contains(tourRequest.getQuery()))
                            .collect(Collectors.toList());
                }).thenAccept(matchedActivities ->
                        wsActivities.addAll(matchedActivities.stream().map(WSActivity::new).collect(Collectors.toList()))
                );

        if(!ticket3Future.isDone()){
            ticket3Future.join();
        }

        placeDetails.setActivities(wsActivities);
        return placeDetails;
    }

    private void processTicket2(List<WSActivity> wsActivities,WSTourRequest tourRequest){
        CompletableFuture<Void> ticket2Future = getTicket2_T_tourDetails(tourRequest.getTicket2_id(), tourRequest.getLocationId())
                .thenApply(ticket2 -> {
                    log.info("The number of entries for {} is {} ", tourRequest.getTicket2_id(), ticket2.getResults().size());
                    return ticket2.getResults().stream()
                            .filter(tour -> tour.getName().contains(tourRequest.getQuery()))
                            .collect(Collectors.toList());
                }).thenAccept(matchedActivities ->
                        wsActivities.addAll(matchedActivities.stream().map(WSActivity::new).collect(Collectors.toList()))
                );
        if(!ticket2Future.isDone()){
            ticket2Future.join();
        }
    }

    private void processPoiDetails(WSPlaceDetails placeDetails,WSTourRequest tourRequest){
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
    }

    private void processAllTickets(List<WSActivity> wsActivities,WSTourRequest tourRequest){
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
    }

    private CompletableFuture<CityDetails> getTicket3_V_tourDetails(String id){
        return CompletableFuture.supplyAsync(() -> {
            log.info("Looking for in the redis server: {} ",id);
            long start = System.currentTimeMillis();
            Optional<CityDetails> cityDetails = cityDetailsRepository.findById(id);
            if(cityDetails.isPresent()){
                CityDetails details = cityDetails.get();
                log.info("Reading the data from the redis server : {}",(System.currentTimeMillis() - start));
                return details;
            }else{
                throw new RuntimeException("Resource not present!");
            }
        },service);
    }

    private CompletableFuture<Ticket2_T_Response> getTicket2_T_tourDetails(String id,String locationId){
        HttpHeaders headers = generateHeaders();
        headers.set(ExternalApiConstants.X_ACCOUNT, this.ticket2_T_account);
        headers.set(ExternalApiConstants.X_TOKEN, this.ticket2_T_token);
        HttpEntity<String> entityReq = new HttpEntity<String>(ExternalApiConstants.PARAMETERS, headers);
        return CompletableFuture.supplyAsync(() ->{
            log.info("Fetching the tickets for Ticket2 provider: {}",id);
            return restTemplate.exchange(generateTicket2_T_url(id,locationId),HttpMethod.GET,entityReq,Ticket2_T_Response.class).getBody();
        },service);
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

    private String generateTicket2_T_url(String id,String locationId){
        StringBuilder url = new StringBuilder();
        return  url.append(this.ticket2_T_baseUrl).append(this.ticket2_T_tour).append(ExternalApiConstants.QUESTION_MARK)
           .append(this.POI_ID).append(ExternalApiConstants.EQUALS_TO).append(id).append(ExternalApiConstants.AMPERSAND)
           .append(this.LOCATION_IDS).append(ExternalApiConstants.EQUALS_TO).append(locationId).append(ExternalApiConstants.AMPERSAND)
           .append(this.ANNOTATE).append(ExternalApiConstants.EQUALS_TO).append(this.CONVERTED_PRICE).append(ExternalApiConstants.COLON)
           .append(this.PRICE_DEFAULT_VALUE).append(ExternalApiConstants.AMPERSAND).append(this.CONVERTED_PRICE)
           .append(ExternalApiConstants.EQUALS_TO).append(ExternalApiConstants.GREATER_THAN).append(ExternalApiConstants.EQUALS_TO).append("1").toString();
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
