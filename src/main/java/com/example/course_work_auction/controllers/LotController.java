package com.example.course_work_auction.controllers;

import com.example.course_work_auction.dto.BidDTO;
import com.example.course_work_auction.dto.CreateLotDTO;
import com.example.course_work_auction.dto.FullLotDTO;
import com.example.course_work_auction.dto.LotDTO;
import com.example.course_work_auction.enums.LotStatus;
import com.example.course_work_auction.model.Lot;
import com.example.course_work_auction.service.LotService;
import com.example.course_work_auction.view.FrequentView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("lot")
public class LotController {
    private final LotService lotService;

    public LotController(LotService lotService) {
        this.lotService = lotService;
    }

    @ApiOperation(value = "Create a new lot", response = LotDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lot created successfully"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping("/lot")
    public ResponseEntity<LotDTO> createNewLot(@RequestBody CreateLotDTO createLotDTO){
        Lot foundedLot = lotService.createLot(createLotDTO);
        if(foundedLot == null){
            ResponseEntity.status(400).build();
        }
        LotDTO dto = LotDTO.fromLot(foundedLot);
        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "Start a bargain for the given lot ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Bargain started successfully"),
            @ApiResponse(code = 404, message = "Lot not found")
    })
    @PostMapping("/lot/{id}/start")
    public ResponseEntity<?> startBargain(@PathVariable Long id){
        if (lotService.findLotById(id).getStatus().equals(LotStatus.STARTED.toString())){
            return ResponseEntity.ok().build();
        }
        if(lotService.findLotById(id).getStatus().equals(LotStatus.CREATED.toString())){
            lotService.startBargain(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Create a new bid for the given lot ID", notes = "Provide a name for the bidder")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Bid created successfully"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Lot not found")
    })
    @PostMapping("/lot/{id}/bid")
    public ResponseEntity<?> createBid( @PathVariable Long id, @RequestBody String name){
        if(lotService.findLotById(id) == null){
            return ResponseEntity.notFound().build();
        }

        if(lotService.findLotById(id).getStatus().equals(LotStatus.STARTED.toString())){
            lotService.createBid(id, name);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "Stop the bargain for the given lot ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Bargain stopped successfully"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Lot not found")
    })
    @PostMapping("/lot/{id}/stop")
    public ResponseEntity<?> stopBargain(@PathVariable Long id){
        if(lotService.findLotById(id) == null){
            return ResponseEntity.notFound().build();
        }

        if(lotService.findLotById(id).getStatus().equals(LotStatus.STOPPED.toString())){
            return ResponseEntity.ok().build();
        }

        if(lotService.findLotById(id).getStatus().equals(LotStatus.STARTED.toString())){
            lotService.stopBargain(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "Get the first bidder for the given lot ID", response = BidDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("/lot/{id}/first")
    public ResponseEntity<BidDTO> getFirstBidPerson( @PathVariable Long id){
        if(lotService.findLotById(id) == null){
            return ResponseEntity.notFound().build();
        }
        BidDTO founded = lotService.getFirstBidPerson(id);
        return ResponseEntity.ok(founded);
    }

    @ApiOperation(value = "Get the frequent bidder and the number of bids they made for the given lot ID", response = FrequentView.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("lot/{id}/frequent")
    public ResponseEntity<FrequentView> getFrequentBid( @PathVariable Long id){
        if(lotService.findLotById(id) == null){
            return ResponseEntity.notFound().build();
        }
        FrequentView founded = lotService.getFrequentBid(id);
        return ResponseEntity.ok(founded);
    }

    @ApiOperation(value = "Get all information of the given lot ID", response = FullLotDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("lot/{id}")
    public ResponseEntity<FullLotDTO> getAllInfoOfLot(@PathVariable Long id){
        if(lotService.getFullLot(id) == null){
            return ResponseEntity.notFound().build();
        }
        FullLotDTO founded = lotService.getFullLot(id);
        return ResponseEntity.ok(founded);
    }

    @ApiOperation(value = "Get all lots with the given status and page number", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping("lot")
    public ResponseEntity<List<LotDTO>> getAllLots(@RequestParam("status") String status,
                                                   @RequestParam("page") Integer pageNumber){
        List<LotDTO> founded = lotService.getAllLots(status, pageNumber);
        if(founded == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(founded);
    }

    @ApiOperation(value = "Export lots as CSV file")
    @GetMapping("lot/export")
    public void exportLots( HttpServletResponse response ) throws IOException {
        lotService.export(response);
    }

}