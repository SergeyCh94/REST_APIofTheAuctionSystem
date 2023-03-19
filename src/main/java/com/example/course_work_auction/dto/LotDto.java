package com.example.course_work_auction.dto;

import java.time.LocalDateTime;

public class LotDto {
    private Long id;
    private String name;
    private String description;
    private int startingPrice;
    private int bidPrice;
    private LocalDateTime biddingStartTime;
    private LocalDateTime biddingEndTime;
    private int numberOfBids;
    private int currentPrice;

    public LotDto(Long id, String name, String description, int startingPrice, int bidPrice,
                  LocalDateTime biddingStartTime, LocalDateTime biddingEndTime, int numberOfBids, int currentPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startingPrice = startingPrice;
        this.bidPrice = bidPrice;
        this.biddingStartTime = biddingStartTime;
        this.biddingEndTime = biddingEndTime;
        this.numberOfBids = numberOfBids;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
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

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(int numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }
}