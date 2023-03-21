package com.example.course_work_auction.service;

import com.example.course_work_auction.model.Bid;
import com.example.course_work_auction.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    public Bid getFirstBidByLotId(Long lotId) {
        List<Bid> bids = bidRepository.findFirstByLotOrderByBidDateTimeAsc(lotId, PageRequest.of(0, 1));
        return bids.isEmpty() ? null : bids.get(0);
    }

}
