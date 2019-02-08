package com.fancyfrog.travelTickets.ws;

import com.fancyfrog.travelTickets.ws.request.WSActivity;
import lombok.Data;

import java.util.List;

@Data
public class WSPlaceDetails {
    private WSPoiDetails poiDetails;
    private List<WSActivity> activities;
}
