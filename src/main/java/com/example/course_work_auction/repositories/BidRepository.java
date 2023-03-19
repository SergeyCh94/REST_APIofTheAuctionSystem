package com.example.course_work_auction.repositories;

import com.example.course_work_auction.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
