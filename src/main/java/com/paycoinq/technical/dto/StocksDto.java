package com.paycoinq.technical.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StocksDto {
    private String name;
    private BigDecimal amount;
    private String lastUpdate;
}
