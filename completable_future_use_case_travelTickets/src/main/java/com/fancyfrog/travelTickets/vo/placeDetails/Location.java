package com.fancyfrog.travelTickets.vo.placeDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {

    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;
}
