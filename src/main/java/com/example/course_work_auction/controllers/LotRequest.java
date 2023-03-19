package com.example.course_work_auction.controllers;

import com.sun.istack.NotNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class LotRequest {

    @NotBlank
    @Size(min = 3, max = 64)
    private String title;

    @NotBlank
    @Size(min = 1, max = 4096)
    private String description;

    @NotNull
    @Min(value = 1)
    private Integer startPrice;

    @NotNull
    @Min(value = 1)
    private Integer bidPrice;

    @NotNull
    private LocalDateTime biddingStartTime;

    @NotNull
    private LocalDateTime biddingEndTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Integer bidPrice) {
        this.bidPrice = bidPrice;
    }

    public LocalDateTime getBiddingStartTime() {
        return biddingStartTime;
    }

    public void setBiddingStartTime(LocalDateTime biddingStartTime) {
        this.biddingStartTime = biddingStartTime;
    }

    public LocalDateTime getBiddingEndTime() {
        return biddingEndTime;
    }

    public void setBiddingEndTime(LocalDateTime biddingEndTime) {
        this.biddingEndTime = biddingEndTime;
    }
}
