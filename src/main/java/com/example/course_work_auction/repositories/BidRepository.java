package com.example.course_work_auction.repositories;

import com.example.course_work_auction.model.Bid;

import com.example.course_work_auction.view.FrequentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    @Query(nativeQuery = true, value =
            "SELECT bidder_name as bidderName, max(bid_date) as bidDate FROM bids WHERE lot_lot_id = ?1 GROUP BY bidder_name ORDER BY count(*) desc limit 1")
    FrequentView findFrequentOne(Long lotId);
}
