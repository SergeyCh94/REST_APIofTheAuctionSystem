package com.example.course_work_auction.exceptions;

import com.example.course_work_auction.enums.LotStatus;

public class LotStatusException extends RuntimeException {
    public LotStatusException(LotStatus currentStatus, LotStatus newStatus) {
        super("Cannot change lot status from " + currentStatus + " to " + newStatus);
    }
}