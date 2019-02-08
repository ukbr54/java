package com.fancyfrog.travelTickets.vo.placeDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Northeast {

    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;
}
