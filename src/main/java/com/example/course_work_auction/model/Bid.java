package com.example.course_work_auction.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Long id;
    private String bidderName;
    private String bidDate;
    @ManyToOne
    private Lot lot;

    public Bid() {
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot( Lot lot ) {
        this.lot = lot;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName( String bidderName ) {
        this.bidderName = bidderName;
    }

    public String getBidDate() {
        return bidDate;
    }

    public void setBidDate( String bidDate ) {
        this.bidDate = bidDate;
    }
}
