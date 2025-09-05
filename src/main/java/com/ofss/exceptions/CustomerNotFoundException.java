package com.ofss.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id) {
        super("Customer with ID " + id + " not found");
    }
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
