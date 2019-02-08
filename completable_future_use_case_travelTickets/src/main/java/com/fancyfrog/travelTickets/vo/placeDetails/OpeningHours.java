package com.fancyfrog.travelTickets.vo.placeDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpeningHours {
    @JsonProperty("open_now")
    private Boolean openNow;
    @JsonProperty("periods")
    private List<Period> periods = null;
    @JsonProperty("weekday_text")
    private List<String> weekdayText = null;
}
