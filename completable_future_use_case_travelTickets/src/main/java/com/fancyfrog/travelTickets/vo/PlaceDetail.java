package com.fancyfrog.travelTickets.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PlaceDetail {
    @JsonProperty("html_attributions")
    private List<Object> htmlAttributions = null;
    @JsonProperty("result")
    private Result result;
    @JsonProperty("status")
    private String status;

    @Data
    private class Result{
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

        @Data
        private class AddressComponent {
            @JsonProperty("long_name")
            private String longName;
            @JsonProperty("short_name")
            private String shortName;
            @JsonProperty("types")
            private List<String> types = null;
        }

        @Data
        private class Geometry {
            @JsonProperty("location")
            private Location location;
            @JsonProperty("viewport")
            private Viewport viewport;

            @Data
            private class Location {
                @JsonProperty("lat")
                private Double lat;
                @JsonProperty("lng")
                private Double lng;
            }

            @Data
            private class Viewport {
                @JsonProperty("northeast")
                private Northeast northeast;
                @JsonProperty("southwest")
                private Southwest southwest;

                @Data
                private class Northeast {
                    @JsonProperty("lat")
                    private Double lat;
                    @JsonProperty("lng")
                    private Double lng;
                }

                @Data
                private class Southwest {
                    @JsonProperty("lat")
                    private Double lat;
                    @JsonProperty("lng")
                    private Double lng;
                }
            }
        }

        @Data
        private class OpeningHours {
            @JsonProperty("open_now")
            private Boolean openNow;
            @JsonProperty("periods")
            private List<Period> periods = null;
            @JsonProperty("weekday_text")
            private List<String> weekdayText = null;

            @Data
            private class Period {
                @JsonProperty("close")
                private Close close;
                @JsonProperty("open")
                private Open open;

                @Data
                private class Close {
                    @JsonProperty("day")
                    private Integer day;
                    @JsonProperty("time")
                    private String time;
                }

                @Data
                private class Open {
                    @JsonProperty("day")
                    private Integer day;
                    @JsonProperty("time")
                    private String time;
                }
            }
        }

        @Data
        private class Photo {
            @JsonProperty("height")
            private Integer height;
            @JsonProperty("html_attributions")
            private List<String> htmlAttributions = null;
            @JsonProperty("photo_reference")
            private String photoReference;
            @JsonProperty("width")
            private Integer width;
        }

        @Data
        private class PlusCode {
            @JsonProperty("compound_code")
            private String compoundCode;
            @JsonProperty("global_code")
            private String globalCode;
        }

        @Data
        private class Review {
            @JsonProperty("author_name")
            private String authorName;
            @JsonProperty("author_url")
            private String authorUrl;
            @JsonProperty("language")
            private String language;
            @JsonProperty("profile_photo_url")
            private String profilePhotoUrl;
            @JsonProperty("rating")
            private Integer rating;
            @JsonProperty("relative_time_description")
            private String relativeTimeDescription;
            @JsonProperty("text")
            private String text;
            @JsonProperty("time")
            private Integer time;
        }
    }
}
