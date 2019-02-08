package com.fancyfrog.travelTickets.vo.placeDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Period {
    @JsonProperty("close")
    private Close close;
    @JsonProperty("open")
    private Open open;
}
