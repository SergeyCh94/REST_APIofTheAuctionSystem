package com.example.course_work_auction.exceptions;

public class BidException extends RuntimeException {
    public BidException(Integer currentPrice, Integer newBidPrice) {
        super("Bid price " + newBidPrice + " should be greater than current price " + currentPrice);
    }
}