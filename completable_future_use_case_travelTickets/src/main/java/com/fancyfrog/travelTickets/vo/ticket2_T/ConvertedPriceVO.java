package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConvertedPriceVO {
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("amount")
    private Double amount;
}
