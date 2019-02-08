package com.fancyfrog.travelTickets.vo.placeDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Open {
    @JsonProperty("day")
    private Integer day;
    @JsonProperty("time")
    private String time;
}
