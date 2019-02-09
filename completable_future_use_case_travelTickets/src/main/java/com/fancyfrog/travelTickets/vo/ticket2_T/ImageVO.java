package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageVO {

    @JsonProperty("attribution")
    private ImageAttributionVO attribution;
    @JsonProperty("license")
    private Object license;
    @JsonProperty("sizes")
    private SizesVO sizes;
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("source_url")
    private String sourceUrl;
    @JsonProperty("caption")
    private Object caption;
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("owner_url")
    private String ownerUrl;
}
