package com.fancyfrog.travelTickets.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Result {
    @JsonProperty("address_components")
    private List<AddressComponent> addressComponents = null;
    @JsonProperty("adr_address")
    private String adrAddress;
    @JsonProperty("formatted_address")
    private String formattedAddress;
    @JsonProperty("formatted_phone_number")
    private String formattedPhoneNumber;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("id")
    private String id;
    @JsonProperty("international_phone_number")
    private String internationalPhoneNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("opening_hours")
    private OpeningHours openingHours;
    @JsonProperty("photos")
    private List<Photo> photos = null;
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("plus_code")
    private PlusCode plusCode;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("reference")
    private String reference;
    @JsonProperty("reviews")
    private List<Review> reviews = null;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("types")
    private List<String> types = null;
    @JsonProperty("url")
    private String url;
    @JsonProperty("user_ratings_total")
    private Integer userRatingsTotal;
    @JsonProperty("utc_offset")
    private Integer utcOffset;
    @JsonProperty("vicinity")
    private String vicinity;
    @JsonProperty("website")
    private String website;
}
