package com.fancyfrog.travelTickets.vo.ticket1_S;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
public class Ticket1_S_TourResponse {

    @JsonProperty("status_code")
    private Integer statusCode;
    @JsonProperty("data")
    private DataVO data;
    @JsonProperty("server_timestamp")
    private String serverTimestamp;
}
