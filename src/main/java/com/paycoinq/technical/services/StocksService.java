package com.paycoinq.technical.services;

import com.paycoinq.technical.dto.StocksDto;
import com.paycoinq.technical.entities.Stocks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StocksService {
    public Stocks retrieveStock(Long id);
    public Page<Stocks> retrieveStocks(Pageable page);
    public Stocks createStocks(Stocks stocks);
    public Stocks updateStocks(Stocks stocks);
    public boolean existsByStockId(Long id);
    public void deleteStocks(Long id);
    public Stocks createStockObject(StocksDto stocksDto);
}
