package com.example.course_work_auction.dto;

import com.example.course_work_auction.dto.BidDto;
import com.example.course_work_auction.model.Bid;
import org.springframework.stereotype.Component;

@Component
public class BidMapper {

    public BidDto toBidDto(Bid bid) {
        BidDto bidDto = new BidDto();
        bidDto.setId(bid.getId());
        bidDto.setBidderName(bid.getBidderName());
        bidDto.setAmount(bid.getBidAmount());
        bidDto.setBidDateTime(bid.getBidDateTime());
        return bidDto;
    }
}
