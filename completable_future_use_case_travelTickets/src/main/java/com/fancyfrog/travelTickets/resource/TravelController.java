package com.fancyfrog.travelTickets.resource;

import com.fancyfrog.travelTickets.service.TravelTicketService;
import com.fancyfrog.travelTickets.ws.WSPlaceDetails;
import com.fancyfrog.travelTickets.ws.request.WSTourRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class TravelController {

    private @Autowired TravelTicketService travelTicketService;

    @GetMapping("/travels")
    public WSPlaceDetails getPlaceDetails(){

        WSTourRequest tourRequest = new WSTourRequest();
        tourRequest.setPoiId("ChIJE_iu2AUZ2jERLxbinkmm-hM");
        tourRequest.setTicket1_id("city:1");
        tourRequest.setQuery("Tower of London");

        WSPlaceDetails detailsWithTickets = travelTicketService.getPlaceDetailsWithTickets(tourRequest);
        return detailsWithTickets;
    }
}
