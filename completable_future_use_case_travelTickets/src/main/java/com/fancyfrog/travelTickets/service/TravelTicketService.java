package com.fancyfrog.travelTickets.service;

import com.fancyfrog.travelTickets.common.ExternalApiConstants;
import com.fancyfrog.travelTickets.vo.PlaceDetail;
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
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TravelTicketService {

    private String baseUrl;
    private String apiKey;
    private String findPlaces;
    private String poiDetailUrl;
    private String nearby;
    private final String MEDIA_TYPE_FORMAT = "json";
    private final String PLACE_ID = "placeid";
    private final String KEY = "key";
    private @Autowired Environment env;
    private @Autowired RestTemplate restTemplate;

    @PostConstruct
    public void init(){
        this.baseUrl = env.getProperty("travel.poiDetailsApis.baseurl");
        this.apiKey = env.getProperty("travel.poiDetailsApis.apikey");
        this.findPlaces = env.getProperty("travel.poiDetailsApis.findPlace");
        this.poiDetailUrl = env.getProperty("travel.poiDetailsApis.poiDetail");
        this.nearby = env.getProperty("travel.poiDetailsApis");
    }

    public void getPlaceDetailsWithTickets(){

    }

    private CompletableFuture<PlaceDetail> getPlaceDetails(String poiId){
       HttpHeaders headers = generateHeaders();
        HttpEntity<String> entity = new HttpEntity<>(ExternalApiConstants.PARAMETERS, headers);
        return CompletableFuture.supplyAsync(() ->
                restTemplate.exchange(generateUrl(poiId),HttpMethod.GET,entity,PlaceDetail.class).getBody()
        );
    }

    private String generateUrl(String poiId){
        StringBuilder url = new StringBuilder(this.baseUrl);
        url.append(this.poiDetailUrl).append(this.MEDIA_TYPE_FORMAT).append(ExternalApiConstants.QUESTION_MARK)
                .append(PLACE_ID).append(ExternalApiConstants.EQUALS_TO).append(poiId).append(ExternalApiConstants.AMPERSAND)
                .append(KEY).append(ExternalApiConstants.EQUALS_TO).append(this.apiKey);
        return url.toString();
    }

    private HttpHeaders generateHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(ExternalApiConstants.Accept,MediaType.APPLICATION_JSON_UTF8_VALUE);
        return headers;
    }

}
