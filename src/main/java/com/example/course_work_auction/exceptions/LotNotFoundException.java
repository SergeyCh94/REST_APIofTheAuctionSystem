package com.example.course_work_auction.exceptions;

public class LotNotFoundException extends RuntimeException {
    public LotNotFoundException(Long id) {
        super("Lot with id " + id + " not found");
    }
}