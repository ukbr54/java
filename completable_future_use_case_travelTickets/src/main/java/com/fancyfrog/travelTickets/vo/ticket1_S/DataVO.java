package com.fancyfrog.travelTickets.vo.ticket1_S;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
public class DataVO {

    @JsonProperty("tours")
    private List<TourVO> tours;
}
