package com.ofss.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @Column(name = "CUST_ID", nullable = false)
    private Long customerId;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "PHONE_NUMBER", length = 15)
    private String phoneNumber;

    @Column(name = "CITY", length = 50)
    private String city;

    @Column(name = "EMAIL_ID", length = 100)
    private String emailId;

}