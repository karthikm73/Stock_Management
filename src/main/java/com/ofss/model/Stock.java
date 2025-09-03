package com.ofss.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {

    @Id
    @Column(name="STOCK_ID")
    private Long stockId;
    @Column(name="STOCK_NAME")
    private String name;
    @Column(name="STOCK_PRICE")
    private Double price;
    @Column(name = "STOCK_VOLUME")
    private Integer volume;

    private String listedExchange;
    private Date listedDate;
    private Double listingPrice;

}