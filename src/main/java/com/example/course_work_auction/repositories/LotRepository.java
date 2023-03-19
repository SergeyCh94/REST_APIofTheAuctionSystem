package com.example.course_work_auction.repositories;

import com.example.course_work_auction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotRepository extends JpaRepository<Lot, Long> {
    @Query(value = "SELECT l.id, COUNT(b.id) as num_bids FROM Lot l LEFT JOIN Bid b ON l.id = b.lot_id GROUP BY l.id", nativeQuery = true)
    List<Object[]> findNumBidsByLot();

    @Query("SELECT COUNT(b) FROM Bid b WHERE b.lot.id = :lotId")
    int getBidCountById(@Param("lotId") Long lotId);
}
