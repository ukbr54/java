package com.fancyfrog.travelTickets.resource;

import com.fancyfrog.travelTickets.service.TravelTicketService;
import com.fancyfrog.travelTickets.ws.WSPlaceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TravelController {

    private @Autowired TravelTicketService travelTicketService;

    @GetMapping("/travels")
    public WSPlaceDetails getPlaceDetails(){
        WSPlaceDetails detailsWithTickets = travelTicketService.getPlaceDetailsWithTickets("ChIJE_iu2AUZ2jERLxbinkmm-hM");
        return detailsWithTickets;
    }
}
