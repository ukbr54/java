package com.fancyfrog.travelTickets.ws.request;

import com.fancyfrog.travelTickets.model.ticket3.TravelTickets;
import com.fancyfrog.travelTickets.vo.ticket1_S.TourVO;
import com.fancyfrog.travelTickets.vo.ticket2_T.ResultVO;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
public class WSActivity {
    private String duration;
    private Integer durationMax;
    private List<String> flags ;
    private Double originalPrice;
    private String description;
    private String photoUrl;
    private String originalPhotoUrl;
    private Double price;
    private Double rating;
    private String title;
    private String url;
    private String vendorId;
    private String vendor;

    public WSActivity(TourVO tourVO){
        this.duration = tourVO.getDuration();
        this.durationMax = tourVO.getDurationMax();
        this.flags = tourVO.getFlags();
        this.originalPrice = tourVO.getOriginalPrice();
        this.description = tourVO.getPerex();
        this.photoUrl = StringUtils.isNotBlank(tourVO.getPhotoUrl()) ? tourVO.getPhotoUrl().replace("[format_id]", "7") : null;
        this.originalPhotoUrl = StringUtils.isNotBlank(tourVO.getPhotoUrl()) ? tourVO.getPhotoUrl().replace("[format_id]", "151") : null;
        this.price = tourVO.getPrice();
        this.rating = tourVO.getRating();
        this.title = tourVO.getTitle();
        this.url = tourVO.getUrl();
    }

    public WSActivity(ResultVO resultVO){
        this.title = resultVO.getName();
        this.vendorId = resultVO.getBookingInfo().getVendorObjectId();
        this.price = Math.ceil(resultVO.getConvertedPrice().getAmount());
        this.originalPrice = resultVO.getPrice().getAmount();
        if(CollectionUtils.isNotEmpty(resultVO.getImages())){
            this.photoUrl = resultVO.getImages().get(0).getSizes().getThumbnail().getUrl();
        }
    }

    public WSActivity(TravelTickets travelTickets){
        this.duration = travelTickets.getDuration();
        this.originalPrice = travelTickets.getPriceUSD();
        this.description = travelTickets.getIntroduction();
        this.photoUrl = travelTickets.getProductImageThumb();
        this.originalPhotoUrl = travelTickets.getProductImage();
        this.price = travelTickets.getPriceUSD();
        this.rating = travelTickets.getAvgRating();
        this.title = travelTickets.getProductName();
        this.url = travelTickets.getProductURL();
        this.vendor = "Viator";
    }
}
