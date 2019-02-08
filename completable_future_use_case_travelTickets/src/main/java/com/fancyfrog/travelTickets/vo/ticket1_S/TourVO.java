package com.fancyfrog.travelTickets.vo.ticket1_S;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TourVO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("supplier")
    private String supplier;
    @JsonProperty("title")
    private String title;
    @JsonProperty("perex")
    private String perex;
    @JsonProperty("url")
    private String url;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("review_count")
    private Integer reviewCount;
    @JsonProperty("photo_url")
    private String photoUrl;
    @JsonProperty("price")
    private Double price;
    @JsonProperty("original_price")
    private Double originalPrice;
    @JsonProperty("duration_min")
    private Integer durationMin;
    @JsonProperty("duration_max")
    private Integer durationMax;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("flags")
    private List<String> flags = null;
}
