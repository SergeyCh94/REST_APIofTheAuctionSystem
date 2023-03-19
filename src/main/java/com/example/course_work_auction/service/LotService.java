package com.example.course_work_auction.service;

import com.example.course_work_auction.controllers.LotRequest;
import com.example.course_work_auction.enums.LotState;
import com.example.course_work_auction.model.Lot;
import com.example.course_work_auction.repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;

    public Lot createLot(String name, String title, Integer startingPrice, Integer biddingPrice, LocalDateTime biddingStartTime, LocalDateTime biddingEndTime) {
        Lot lot = new Lot();
        lot.setName(name);
        lot.setStartingPrice(startingPrice);
        lot.setBiddingPrice(biddingPrice);
        lot.setBiddingStartTime(biddingStartTime);
        lot.setBiddingEndTime(biddingEndTime);
        lot.setStatus(LotState.BIDDING_STARTED);
        return lotRepository.save(lot);
    }

    public int getBidCountForLot(Long lotId) {
        return lotRepository.getBidCountById(lotId);
    }

    public int getCurrentPrice(Long id) {
        Lot lot = lotRepository.getById(id);
        return lot.getCurrentPrice();
    }

    public String getStatus(Long id) {
        Lot lot = lotRepository.getById(id);
        LotState state = lot.getStatus();
        switch (state) {
            case BIDDING_STARTED:
                return "Bidding is currently ongoing";
            case BIDDING_OVER:
                return "Bidding has ended";
            default:
                return "Unknown status";
        }
    }
}
