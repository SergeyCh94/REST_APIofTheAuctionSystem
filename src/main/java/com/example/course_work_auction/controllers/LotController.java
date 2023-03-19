package com.example.course_work_auction.controllers;

import com.example.course_work_auction.model.Lot;
import com.example.course_work_auction.repositories.LotRepository;
import com.example.course_work_auction.service.LotService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/lots")
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private LotRepository lotRepository;

    // Get the number of bids for a lot
    @GetMapping("/{id}/bids/count")
    public ResponseEntity<Integer> getBidsCount(@PathVariable Long id) {
        int count = lotService.getBidCountForLot(id);
        return ResponseEntity.ok(count);
    }

    // Get the current price for a lot
    @GetMapping("/{id}/price")
    public ResponseEntity<Integer> getCurrentPrice(@PathVariable Long id) {
        int price = lotService.getCurrentPrice(id);
        return ResponseEntity.ok(price);
    }

    // Get the bidding status for a lot
    @GetMapping("/{id}/status")
    public ResponseEntity<String> getStatus(@PathVariable Long id) {
        String status = lotService.getStatus(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/lots/export")
    public void exportLots(HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"lots.csv\"");

        StatefulBeanToCsv<Lot> writer = new StatefulBeanToCsvBuilder<Lot>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        writer.write(lotRepository.findAll());
    }
}