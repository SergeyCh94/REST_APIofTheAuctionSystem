package com.example.course_work_auction.repositories;

import com.example.course_work_auction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findAllByStatusContainingIgnoreCase( String status);
}
