package com.example.course_work_auction.repositories;

import com.example.course_work_auction.model.Bid;
import com.example.course_work_auction.model.Lot;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("SELECT b FROM Bid b WHERE b.lot.id = :lotId ORDER BY b.bidDateTime ASC")
    List<Bid> findFirstByLotOrderByBidDateTimeAsc(@Param("lotId") Long lotId, Pageable pageable);

    List<Bid> findByLot(Lot lot);

    Optional<Bid> findByLotIdOrderByBidDateTimeDesc(Long lotId);
}
