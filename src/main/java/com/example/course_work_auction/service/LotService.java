package com.example.course_work_auction.service;

import com.example.course_work_auction.dto.BidDto;
import com.example.course_work_auction.dto.BidMapper;
import com.example.course_work_auction.dto.FullLotDto;
import com.example.course_work_auction.enums.LotState;
import com.example.course_work_auction.exceptions.ResourceNotFoundException;
import com.example.course_work_auction.model.Bid;
import com.example.course_work_auction.model.Lot;
import com.example.course_work_auction.repositories.BidRepository;
import com.example.course_work_auction.repositories.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LotService {

    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BidMapper bidMapper;


    public Bid getFirstBidder(Long lotId) {
        Lot lot = lotRepository.getById(lotId);
        if (lot == null) {
            throw new EntityNotFoundException("Lot not found with id " + lotId);
        }
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "bidTime"));
        List<Bid> bids = bidRepository.findFirstByLotOrderByBidDateTimeAsc(lotId, pageable);
        if (bids.isEmpty()) {
            return null;
        }
        BidDto bidDto = bidMapper.toBidDto(bids.get(0));
        Bid bid = new Bid();
        bid.setId(bidDto.getId());
        bid.setBidderName(bidDto.getBidderName());
        bid.setBidDateTime(bidDto.getBidDateTime());
        bid.setBidAmount(bidDto.getAmount());
        return bid;
    }

    public BidDto getMostFrequentBidder(Long lotID) {
        Lot lot = lotRepository.findById(lotID)
                .orElseThrow(() -> new ResourceNotFoundException("Lot not found with id: " + lotID));
        List<Bid> bids = bidRepository.findByLot(lot);
        if (bids.isEmpty()) {
            throw new ResourceNotFoundException("Lot");
        }
        Map<String, Integer> bidderFrequencyMap = new HashMap<>();
        for (Bid bid : bids) {
            String bidderName = bid.getBidderName();
            bidderFrequencyMap.put(bidderName, bidderFrequencyMap.getOrDefault(bidderName, 0) + 1);
        }
        String mostFrequentBidderName = Collections.max(bidderFrequencyMap.entrySet(),
                Map.Entry.comparingByValue()).getKey();
        LocalDateTime lastBidDateTime = bidRepository.findByLotIdOrderByBidDateTimeDesc(lot.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No bids found for lot with id: " + lotID))
                .getBidDateTime();
        BidDto bidDto = new BidDto();
        bidDto.setBidderName(mostFrequentBidderName);
        bidDto.setBidDateTime(lastBidDateTime);
        return bidDto;
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
