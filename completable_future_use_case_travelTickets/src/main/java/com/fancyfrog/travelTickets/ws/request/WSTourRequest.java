package com.fancyfrog.travelTickets.ws.request;

import lombok.Data;

@Data
public class WSTourRequest {

    private String poiId;
    private String ticket1_id;
    private String query;
}
