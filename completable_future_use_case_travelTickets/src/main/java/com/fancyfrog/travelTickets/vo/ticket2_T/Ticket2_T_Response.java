package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Ticket2_T_Response {
    @JsonProperty("results")
    private List<ResultVO> results = null;
    @JsonProperty("estimated_total")
    private Integer estimatedTotal;
    @JsonProperty("more")
    private Boolean more;
}
