package com.fancyfrog.travelTickets.ws;

import com.fancyfrog.travelTickets.vo.PlaceDetail;
import com.fancyfrog.travelTickets.vo.Result;
import lombok.Data;

import java.util.List;

@Data
public class WSPoiDetails {
    //private List<Reviews> reviews;
    //private List<AddressComponents> addressComponents;
    private String formattedAddress;
    private String formattedPhoneNumber;
    //private Geometry geometry;
    private String internationalPhoneNumber;
    private String name;
    //private WSOpeningHours openingHours;
    //private List<Photos> photos;
    private Double rating;
    private String priceLevel;
    private String vicinity;
    private String website;

    public WSPoiDetails(Result result){
        this.formattedAddress = result.getFormattedAddress();
        this.formattedPhoneNumber = result.getFormattedPhoneNumber();
        this.internationalPhoneNumber = result.getFormattedPhoneNumber();
        this.name = result.getName();
        this.rating = result.getRating();
        this.vicinity = result.getVicinity();
        this.website = result.getWebsite();
    }
}
