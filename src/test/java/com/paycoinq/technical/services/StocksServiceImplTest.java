package com.paycoinq.technical.services;

import com.paycoinq.technical.dto.StocksDto;
import com.paycoinq.technical.entities.Stocks;
import com.paycoinq.technical.repository.StocksRepository;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;


import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StocksServiceImplTest {

    @Mock
    private StocksRepository mockStocksRepository;

    private StocksServiceImpl stocksServiceImplUnderTest;

    @Before("")
    public void setUp() {
        stocksServiceImplUnderTest = new StocksServiceImpl(mockStocksRepository);
    }

    @Test
    public void testRetrieveStock() {
        // Setup
        final Stocks expectedResult = new Stocks();
        expectedResult.setName("name");
        expectedResult.setAmount(new BigDecimal("0.00"));
        expectedResult.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure StocksRepository.getById(...).
        final Stocks stocks = new Stocks();
        stocks.setName("name");
        stocks.setAmount(new BigDecimal("0.00"));
        stocks.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockStocksRepository.getById(0L)).thenReturn(stocks);

        // Run the test
        final Stocks result = stocksServiceImplUnderTest.retrieveStock(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testRetrieveStocks() {
        // Setup
        // Configure StocksRepository.findAll(...).
        final Stocks stocks1 = new Stocks();
        stocks1.setName("name");
        stocks1.setAmount(new BigDecimal("0.00"));
        stocks1.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final Page<Stocks> stocks = new PageImpl<>(List.of(stocks1));
        when(mockStocksRepository.findAll(any(Pageable.class))).thenReturn(stocks);

        // Run the test
        final Page<Stocks> result = stocksServiceImplUnderTest.retrieveStocks(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testRetrieveStocks_StocksRepositoryReturnsNoItems() {
        // Setup
        when(mockStocksRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        // Run the test
        final Page<Stocks> result = stocksServiceImplUnderTest.retrieveStocks(PageRequest.of(0, 1));

        // Verify the results
    }

    @Test
    public void testCreateStocks() {
        // Setup
        final Stocks stocks = new Stocks();
        stocks.setName("name");
        stocks.setAmount(new BigDecimal("0.00"));
        stocks.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Stocks expectedResult = new Stocks();
        expectedResult.setName("name");
        expectedResult.setAmount(new BigDecimal("0.00"));
        expectedResult.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure StocksRepository.save(...).
        final Stocks stocks1 = new Stocks();
        stocks1.setName("name");
        stocks1.setAmount(new BigDecimal("0.00"));
        stocks1.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockStocksRepository.save(new Stocks())).thenReturn(stocks1);

        // Run the test
        final Stocks result = stocksServiceImplUnderTest.createStocks(stocks);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testUpdateStocks() {
        // Setup
        final Stocks stocks = new Stocks();
        stocks.setName("name");
        stocks.setAmount(new BigDecimal("0.00"));
        stocks.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        final Stocks expectedResult = new Stocks();
        expectedResult.setName("name");
        expectedResult.setAmount(new BigDecimal("0.00"));
        expectedResult.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Configure StocksRepository.save(...).
        final Stocks stocks1 = new Stocks();
        stocks1.setName("name");
        stocks1.setAmount(new BigDecimal("0.00"));
        stocks1.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        when(mockStocksRepository.save(new Stocks())).thenReturn(stocks1);

        // Run the test
        final Stocks result = stocksServiceImplUnderTest.updateStocks(stocks);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testExistsByStockId() {
        // Setup
        when(mockStocksRepository.existsById(0L)).thenReturn(false);

        // Run the test
        final boolean result = stocksServiceImplUnderTest.existsByStockId(0L);

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testDeleteStocks() {
        // Setup
        // Run the test
        stocksServiceImplUnderTest.deleteStocks(0L);

        // Verify the results
        verify(mockStocksRepository).deleteById(0L);
    }

    @Test
    public void testCreateStockObject() {
        // Setup
        final StocksDto stocksDto = new StocksDto();
        stocksDto.setName("name");
        stocksDto.setAmount(new BigDecimal("0.00"));
        stocksDto.setLastUpdate("lastUpdate");

        final Stocks expectedResult = new Stocks();
        expectedResult.setName("name");
        expectedResult.setAmount(new BigDecimal("0.00"));
        expectedResult.setLastUpdate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));

        // Run the test
        final Stocks result = stocksServiceImplUnderTest.createStockObject(stocksDto);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
