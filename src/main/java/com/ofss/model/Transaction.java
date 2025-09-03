package com.ofss.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @Column(name = "TXN_ID", nullable = false)
    private Long txnId;

    @Column(name = "CUST_ID", nullable = false)
    private Long customerId;

    @Column(name = "STOCK_ID", nullable = false)
    private Long stockId;

    @Column(name = "TXN_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal txnPrice;

    @Column(name = "TXN_TYPE", length = 10)
    private String txnType;

    @Column(name = "QTY", nullable = false)
    private Integer qty;

    @CreatedDate
    @Column(name = "TXN_DATE")
    private Date txnDate;
}
