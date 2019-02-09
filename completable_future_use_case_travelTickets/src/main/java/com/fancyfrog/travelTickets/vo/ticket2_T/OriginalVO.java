package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OriginalVO {
    @JsonProperty("url")
    private String url;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("format")
    private String format;
    @JsonProperty("bytes")
    private Integer bytes;
    @JsonProperty("height")
    private Integer height;
}
