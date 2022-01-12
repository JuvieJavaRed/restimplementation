package com.paycoinq.technical.config;

import com.paycoinq.technical.entities.Stocks;
import com.paycoinq.technical.services.StocksService;
import com.paycoinq.technical.utilities.GenerateTimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoaderSetup {

    @Autowired
    private StocksService stocksService;

    @PostConstruct
    private void DbInit(){
        List<Stocks> listStocks = new ArrayList<>();
        Stocks stocks1 = new Stocks();
        stocks1.setAmount(new BigDecimal(25.00));
        stocks1.setName("Apple");
        stocks1.setLastUpdate(GenerateTimeStamp.retrieveTimeStamp());
        stocksService.createStocks(stocks1);
        Stocks stocks2 = new Stocks();
        stocks2.setAmount(new BigDecimal(30.00));
        stocks2.setName("Banana");
        stocks2.setLastUpdate(GenerateTimeStamp.retrieveTimeStamp());
        stocksService.createStocks(stocks2);
        Stocks stocks3 = new Stocks();
        stocks3.setAmount(new BigDecimal(45.00));
        stocks3.setName("Pear");
        stocks3.setLastUpdate(GenerateTimeStamp.retrieveTimeStamp());
        stocksService.createStocks(stocks3);
        Stocks stocks4 = new Stocks();
        stocks4.setAmount(new BigDecimal(36.00));
        stocks4.setName("Grape");
        stocks4.setLastUpdate(GenerateTimeStamp.retrieveTimeStamp());
        stocksService.createStocks(stocks4);
        Stocks stocks5 = new Stocks();
        stocks5.setAmount(new BigDecimal(56.00));
        stocks5.setName("Strawberry");
        stocks5.setLastUpdate(GenerateTimeStamp.retrieveTimeStamp());
        stocksService.createStocks(stocks5);
        Stocks stocks6 = new Stocks();
        stocks6.setAmount(new BigDecimal(66.00));
        stocks6.setName("Pear");
        stocks6.setLastUpdate(GenerateTimeStamp.retrieveTimeStamp());
        stocksService.createStocks(stocks6);

    }
}
