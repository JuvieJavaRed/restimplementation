package com.paycoinq.technical.controllers;

import com.paycoinq.technical.dto.StocksDto;
import com.paycoinq.technical.entities.Stocks;
import com.paycoinq.technical.exceptions.NonExistentStockException;
import com.paycoinq.technical.services.StocksService;
import com.paycoinq.technical.utilities.GenerateTimeStamp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class StocksController {

    private final StocksService stocksService;

    public StocksController(StocksService stocksService){
        this.stocksService = stocksService;
    }

    @PutMapping("/v1/api/stocks/{id}")
    @Operation(summary = "Update a stock price")
    @ApiResponses(value = {
            @ApiResponse(responseCode= "201",
                    description = "Successfully updated a stock record",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource Unavailable")

    })
    public ResponseEntity<Stocks> updateStocks(@PathVariable("id") Long id, @Validated @RequestBody StocksDto stocksDto){
        Stocks stocks = stocksService.retrieveStock(id);
        if(Objects.isNull(stocks)){
            throw new NonExistentStockException("The stock with the ID "+id+ "does not exist");
        }
        stocks.setLastUpdate(GenerateTimeStamp.retrieveTimeStamp());
        stocks.setName(stocksDto.getName());
        stocks.setAmount(stocksDto.getAmount());
        return new ResponseEntity<Stocks>(stocksService.updateStocks(stocks), HttpStatus.CREATED);
    }

    @GetMapping("/v1/api/stocks")
    @Operation(summary = "Returns a list of all stocks")
    @ApiResponses(value = {
            @ApiResponse(responseCode= "200",
                    description = "Successfully fetched the records for required pages",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource Unavailable")

    })
    public ResponseEntity<Page<Stocks>> retrieveStocks(Pageable page){
        log.info("Now retrieving all stocks");
        return new ResponseEntity<Page<Stocks>>(stocksService.retrieveStocks(page), HttpStatus.OK);
    }

    @GetMapping("v1/api/stocks/{id}")
    @Operation(summary = "Returns an object of the stock that is associated with the stock ID passed")
    @ApiResponses(value = {
            @ApiResponse(responseCode= "200",
                    description = "Successfully fetched the record associated with that ID",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource Unavailable")

    })
    public ResponseEntity<Stocks> retrieveSingleStocks(@PathVariable("id") Long id){
        log.info("Now processing stock number :" +id);
        if(stocksService.existsByStockId(id)){
            return new ResponseEntity<Stocks>(stocksService.retrieveStock(id), HttpStatus.OK);
        }
        throw new NonExistentStockException("The stock with the following ID does not exist "+id);
    }

    @PostMapping("v1/api/stocks")
    @Operation(summary = "Creates a new stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode= "201",
                    description = "Successfully created a stock",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Resource Unavailable")

    })
    public ResponseEntity<Stocks> createStock(@Validated @RequestBody StocksDto stocksDto){
        log.info("Now processing the creation of : "+stocksDto.toString());
        Stocks stocks = stocksService.createStockObject(stocksDto);
        return new ResponseEntity<Stocks>(stocksService.createStocks(stocks), HttpStatus.CREATED);
    }

    @DeleteMapping("v1/api/stocks/{id}")
    @Operation(summary = "Deletes a stock using the stock ID")
    public ResponseEntity<String> deleteStock(@PathVariable("id") Long id){
        log.info("Processing deleting record with ID: "+id);
        if(!stocksService.existsByStockId(id)){
            throw new NonExistentStockException("The stock with the following ID does not exist "+id);
        }
        stocksService.deleteStocks(id);
        return  new ResponseEntity<>("The stock has been deleted successfully", HttpStatus.OK);
    }
}
