package com.paycoinq.technical.services;

import com.paycoinq.technical.dto.StocksDto;
import com.paycoinq.technical.entities.Stocks;
import com.paycoinq.technical.repository.StocksRepository;
import com.paycoinq.technical.utilities.GenerateTimeStamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class StocksServiceImpl implements StocksService {

    private final StocksRepository stocksRepository;

    public StocksServiceImpl(StocksRepository stocksRepository){
        this.stocksRepository = stocksRepository;
    }

    @Override
    /**
     * A method to retrieve an individual stock
     * @param Long id
     * return the Object of Type Stock
     */
    public Stocks retrieveStock(Long id) {
        return stocksRepository.getById(id);
    }

    @Override
    /**
     * A method to retrieve a list of stocks.
     * @param null
     * return a list stocks.
     */
    public Page<Stocks> retrieveStocks(Pageable page) {
        return stocksRepository.findAll(page);
    }

    @Override
    /**
     * A method to create a Stock.
     * @param Stocks stock
     * return Object of Stocks
     */
    public Stocks createStocks(Stocks stocks) {
        return stocksRepository.save(stocks);
    }

    @Override
    /**
     * A method to update a stock
     * @param Stocks
     * return object of Stocks
     */
    public Stocks updateStocks(Stocks stocks) {
        return stocksRepository.save(stocks);
    }

    @Override
    /**
     * A method to check whether or not a stock exists using the ID
     * @param Long id
     * return boolean
     */
    public boolean existsByStockId(Long id) {
        return stocksRepository.existsById(id);
    }

    @Override
    /**
     * A method to delete a Stock
     * @param Long id
     * returns null
     */
    public void deleteStocks(Long id) {
        stocksRepository.deleteById(id);
    }

    /**
     * A method to create a stock object
     * @param Object StockDto
     * return Stock
     */
    public Stocks createStockObject(StocksDto stocksDto){
        Stocks stocks = new Stocks();
        stocks.setAmount(stocksDto.getAmount());
        stocks.setName(stocksDto.getName());
        stocks.setLastUpdate(GenerateTimeStamp.retrieveTimeStamp());
        return  stocks;
    }
}
