package com.example.course_work_auction.exceptions;

public class BiddingClosedException extends RuntimeException {
    public BiddingClosedException(String message) {
        super(message);
    }
}