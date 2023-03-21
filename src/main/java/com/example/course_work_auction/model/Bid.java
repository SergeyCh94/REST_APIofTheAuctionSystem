package com.example.course_work_auction.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String bidderName;

    private Double bidAmount;

    private LocalDateTime bidDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id")
    private Lot lot;

    public Bid(String bidderName, Double bidAmount, LocalDateTime bidDateTime) {
        this.bidderName = bidderName;
        this.bidAmount = bidAmount;
        this.bidDateTime = bidDateTime;
    }

    public Bid() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public Double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(Double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public LocalDateTime getBidDateTime() {
        return bidDateTime;
    }

    public void setBidDateTime(LocalDateTime bidDateTime) {
        this.bidDateTime = bidDateTime;
    }
}
