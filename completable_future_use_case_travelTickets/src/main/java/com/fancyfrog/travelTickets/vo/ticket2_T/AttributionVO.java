package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AttributionVO {
    @JsonProperty("source_id")
    private String sourceId;
    @JsonProperty("url")
    private String url;
}
