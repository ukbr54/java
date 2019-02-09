package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageAttributionVO {
    @JsonProperty("license_link")
    private Object licenseLink;
    @JsonProperty("attribution_link")
    private String attributionLink;
    @JsonProperty("attribution_text")
    private String attributionText;
    @JsonProperty("license_text")
    private Object licenseText;
    @JsonProperty("format")
    private String format;
}
