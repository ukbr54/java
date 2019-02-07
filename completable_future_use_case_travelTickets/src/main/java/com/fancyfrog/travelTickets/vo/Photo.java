package com.fancyfrog.travelTickets.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Photo {
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("html_attributions")
    private List<String> htmlAttributions = null;
    @JsonProperty("photo_reference")
    private String photoReference;
    @JsonProperty("width")
    private Integer width;
}
