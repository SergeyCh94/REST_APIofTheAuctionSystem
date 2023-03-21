package com.example.course_work_auction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FullLotDto {

    private Long id;

    private String name;

    private String description;

    private BigDecimal startPrice;

    private BigDecimal currentPrice;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String status;

    private BidDto lastBid;

    public FullLotDto() {
    }

    public FullLotDto(Long id, String name, String description, BigDecimal startPrice, BigDecimal currentPrice,
                      LocalDateTime startDateTime, LocalDateTime endDateTime, String status, BidDto lastBid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
        this.lastBid = lastBid;
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

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BidDto getLastBid() {
        return lastBid;
    }

    public void setLastBid(BidDto lastBid) {
        this.lastBid = lastBid;
    }
}