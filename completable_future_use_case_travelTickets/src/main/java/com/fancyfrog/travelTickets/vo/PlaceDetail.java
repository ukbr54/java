package com.fancyfrog.travelTickets.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlaceDetail {
    @JsonProperty("html_attributions")
    private List<Object> htmlAttributions = null;
    @JsonProperty("result")
    private Result result;
    @JsonProperty("status")
    private String status;
}
