package com.example.course_work_auction.model;

import com.example.course_work_auction.enums.LotState;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer startingPrice;

    @NotNull
    private Integer biddingPrice;

    @NotNull
    private LocalDateTime biddingStartTime;

    @NotNull
    private LocalDateTime biddingEndTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LotState status;

    @OneToMany(mappedBy = "lot", cascade = CascadeType.ALL)
    private List<Bid> bids;

    public Lot() {
        this.bids = new ArrayList<>();
        this.status = LotState.BIDDING_STARTED;
    }

    public Integer getCurrentPrice() {
        return (bids != null && !bids.isEmpty()) ?
                bids.size() * biddingPrice : startingPrice;
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

    public Integer getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Integer startingPrice) {
        this.startingPrice = startingPrice;
    }

    public Integer getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(Integer biddingPrice) {
        this.biddingPrice = biddingPrice;
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

    public LotState getStatus() {
        return status;
    }

    public void setStatus(LotState status) {
        this.status = status;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public int getBidsCount() {
        return (bids != null) ? bids.size() : 0;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
        bid.setLot(this);
    }

    public boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(biddingEndTime);
    }

    public boolean canBeBidded() {
        LocalDateTime now = LocalDateTime.now();
        return (now.isAfter(biddingStartTime) && now.isBefore(biddingEndTime) && status == LotState.BIDDING_STARTED);
    }
}