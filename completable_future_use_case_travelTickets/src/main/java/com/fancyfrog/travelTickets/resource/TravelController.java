package com.fancyfrog.travelTickets.resource;

import com.fancyfrog.travelTickets.service.Ticket3_V_Service;
import com.fancyfrog.travelTickets.service.TravelTicketService;
import com.fancyfrog.travelTickets.ws.WSPlaceDetails;
import com.fancyfrog.travelTickets.ws.request.WSTourRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class TravelController {

    private @Autowired TravelTicketService travelTicketService;
    private @Autowired Ticket3_V_Service ticket3_v_service;

    @GetMapping("/travels")
    public WSPlaceDetails getPlaceDetails(){

        WSTourRequest tourRequest = new WSTourRequest();
        tourRequest.setPoiId("ChIJE_iu2AUZ2jERLxbinkmm-hM");
        tourRequest.setTicket1_id("city:1");
        tourRequest.setQuery("Tower of London");
        tourRequest.setTicket2_id("T__384b451628dd");
        tourRequest.setLocationId("London");
        tourRequest.setTicket3_id("London");

        WSPlaceDetails detailsWithTickets = travelTicketService.getPlaceDetailsWithTickets(tourRequest);
        return detailsWithTickets;
    }

    @GetMapping("/dump")
    public ResponseEntity<?> storeDataInRedis(){
        ticket3_v_service.dumpCsvFileIntoRedisServer("C:\\Users\\QuaQUa\\Desktop\\vapProducts.csv");
        return new ResponseEntity<>("Dump Successfully!",HttpStatus.OK);
    }
}
