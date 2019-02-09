package com.fancyfrog.travelTickets.vo.ticket2_T;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookingInfoVO {
    @JsonProperty("vendor_object_id")
    private String vendorObjectId;
    @JsonProperty("price")
    private PriceVO price;
    @JsonProperty("vendor_object_url")
    private String vendorObjectUrl;
    @JsonProperty("vendor")
    private String vendor;
}
