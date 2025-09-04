package com.ofss.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_generator")
    @SequenceGenerator(name = "stock_generator", sequenceName = "stock_seq", allocationSize = 1)
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