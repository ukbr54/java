package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResultVO {
    @JsonProperty("attribution")
    private List<AttributionVO> attribution = null;
    @JsonProperty("name")
    private String name;
    @JsonProperty("duration_unit")
    private Object durationUnit;
    @JsonProperty("price")
    private PriceVO price;
    @JsonProperty("converted_price")
    private ConvertedPriceVO convertedPrice;
    @JsonProperty("vendor_tour_url")
    private String vendorTourUrl;
    @JsonProperty("vendor")
    private String vendor;
    @JsonProperty("duration")
    private Object duration;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("images")
    private List<ImageVO> images = null;
    @JsonProperty("price_is_per_person")
    private Boolean priceIsPerPerson;
    @JsonProperty("booking_info")
    private BookingInfoVO bookingInfo;
    @JsonProperty("id")
    private String id;
}
