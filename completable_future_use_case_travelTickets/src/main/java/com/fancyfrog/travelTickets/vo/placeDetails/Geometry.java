package com.fancyfrog.travelTickets.vo.placeDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Geometry {

    @JsonProperty("location")
    private Location location;
    @JsonProperty("viewport")
    private Viewport viewport;
}
