package com.fancyfrog.travelTickets.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Viewport {
    @JsonProperty("northeast")
    private Northeast northeast;
    @JsonProperty("southwest")
    private Southwest southwest;
}
