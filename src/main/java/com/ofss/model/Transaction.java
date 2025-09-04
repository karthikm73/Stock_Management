package com.ofss.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @Column(name = "TXN_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
    @SequenceGenerator(name = "transaction_generator", sequenceName = "transaction_seq", allocationSize = 1)
    private Long txnId;

    @ManyToOne
    @JoinColumn(name = "CUST_ID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "STOCK_ID", nullable = false)
    private Stock stock;

    @Column(name = "TXN_PRICE", nullable = false, precision = 10, scale = 2)
    private BigDecimal txnPrice;

    @Column(name = "TXN_TYPE", length = 10)
    private String txnType;

    @Column(name = "QTY", nullable = false)
    private Integer qty;

    @Temporal(TemporalType.DATE)
    @Column(name = "TXN_DATE")
    @CreatedDate
    private Date txnDate;

}
