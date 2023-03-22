package com.example.course_work_auction.service;

import com.example.course_work_auction.dto.BidDTO;
import com.example.course_work_auction.dto.CreateLotDTO;
import com.example.course_work_auction.dto.FullLotDTO;
import com.example.course_work_auction.dto.LotDTO;
import com.example.course_work_auction.enums.LotStatus;
import com.example.course_work_auction.model.Bid;
import com.example.course_work_auction.model.Lot;
import com.example.course_work_auction.repositories.BidRepository;
import com.example.course_work_auction.repositories.LotRepository;
import com.example.course_work_auction.view.FrequentView;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;

    public LotService( LotRepository lotRepository, BidRepository bidRepository ) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
    }


    public Lot createLot(CreateLotDTO createLotDTO){
        Lot createdLot = createLotDTO.toLot();
        createdLot.setStatus(LotStatus.CREATED.toString());
        lotRepository.save(createdLot);
        return createdLot;
    }
    public void startBargain(Long lotId){
        Lot foundedLot = lotRepository.findById(lotId).get();
        foundedLot.setStatus(LotStatus.STARTED.toString());
        lotRepository.save(foundedLot);
    }
    public void createBid(Long lotId, String bidName){
        Lot foundedLot = lotRepository.findById(lotId).get();
        Bid bid = new Bid();
        bid.setLot(foundedLot);
        bid.setBidderName(bidName);
        bid.setBidDate(LocalDate.now().toString());
        bidRepository.save(bid);

    }
    public void stopBargain(Long id){
        Lot foundedLot = lotRepository.findById(id).get();
        foundedLot.setStatus(LotStatus.STOPPED.toString());
        lotRepository.save(foundedLot);
    }
    public FullLotDTO getFullLot(Long id){
        return FullLotDTO.fromLot(lotRepository.findById(id).get());
    }
    public BidDTO getFirstBidPerson(Long id){
        Lot founded = lotRepository.findById(id).get();
        return BidDTO.fromBid(founded.getBids().get(0));
    }
    public FrequentView getFrequentBid(Long id){
        Lot founded = lotRepository.findById(id).get();
        return bidRepository.findFrequentOne(id);
    }
    public List<LotDTO> getAllLots(String status, Integer pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber -1, 10);
        List<Lot> founded = lotRepository.findAllByStatusContainingIgnoreCase(status);
        List<LotDTO> needed = new ArrayList<>();
        for(Lot lot: founded){
            LotDTO dto = LotDTO.fromLot(lot);
            needed.add(dto);
        }
        return needed;
    }
    public List<FullLotDTO> getAllFullLots(){
        return lotRepository.findAll().stream()
                .map(FullLotDTO::fromLot)
                .collect(Collectors.toList());
    }
    public  void export(HttpServletResponse response) throws IOException {
        List<FullLotDTO> lots = getAllFullLots();
        StringWriter sw = new StringWriter();
        CSVPrinter printer = new CSVPrinter(sw, CSVFormat.DEFAULT);
        lots.stream()
                .forEach(fullLotDTO -> {
                    try{
                        printer.printRecord(
                                fullLotDTO.getId(),
                                fullLotDTO.getStatus(),
                                fullLotDTO.getTitle(),
                                fullLotDTO.getDescription(),
                                fullLotDTO.getStartPrice(),
                                fullLotDTO.getBidPrice(),
                                fullLotDTO.getCurrentPrice(),
                                fullLotDTO.getLastBid().getBidderName());
                    } catch (IOException e){
                        throw new RuntimeException(e);
                    }
                });
        printer.flush();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"lots.csv\"");

        PrintWriter pw = response.getWriter();
        pw.write(sw.toString());
        pw.flush();
        pw.close();
    }
    public Lot findLotById(Long id){
        return lotRepository.findById(id).get();
    }

}