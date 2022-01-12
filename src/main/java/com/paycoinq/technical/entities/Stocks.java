package com.paycoinq.technical.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "stocks", indexes = {@Index(name = "stocks_id", columnList = "id", unique = true)})
public class Stocks extends BaseEntity {
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;
    @Column(name = "amount", nullable = false, length = 10)
    private BigDecimal amount;
    @Column(name = "lastUpdate", nullable = false)
    private LocalDateTime lastUpdate;
}
