package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SizesVO {
    @JsonProperty("medium")
    private MediumVO medium;
    @JsonProperty("original")
    private OriginalVO original;
    @JsonProperty("thumbnail")
    private ThumbnailVO thumbnail;
}
