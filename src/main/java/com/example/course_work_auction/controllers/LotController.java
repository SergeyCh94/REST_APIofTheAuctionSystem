package com.example.course_work_auction.controllers;

import com.example.course_work_auction.dto.BidDto;
import com.example.course_work_auction.dto.FullLotDto;
import com.example.course_work_auction.model.Bid;
import com.example.course_work_auction.model.Lot;
import com.example.course_work_auction.repositories.LotRepository;
import com.example.course_work_auction.service.LotService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("/lot")
@Api(value = "Lot Controller", tags = {"Lot Controller"})
public class LotController {

    @Autowired
    private LotService lotService;

    @Autowired
    private LotRepository lotRepository;

    @ApiOperation(value = "Get the first bidder for a lot", response = Bid.class)
    @GetMapping("/{id}/first")
    public ResponseEntity<Bid> getFirstBidder(@PathVariable Long id) {
        Bid bid = lotService.getFirstBidder(id);
        return ResponseEntity.ok(bid);
    }

    @ApiOperation(value = "Get the most frequent bidder for a lot", response = BidDto.class)
    @GetMapping("/{id}/frequent")
    public ResponseEntity<BidDto> getMostFrequentBidder(@PathVariable("id") Long lotID) {
        BidDto bidDto = lotService.getMostFrequentBidder(lotID);
        return ResponseEntity.ok(bidDto);
    }

    @GetMapping("/lot/{id}")
    public FullLotDto getFullLot(@PathVariable Long id) {
        return lotService.getFullLotById(id);
    }

    // Export lots to CSV
    @GetMapping("/export")
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