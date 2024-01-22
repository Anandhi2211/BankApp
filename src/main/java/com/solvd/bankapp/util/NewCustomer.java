package com.solvd.bankapp.util;

import com.solvd.bankapp.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewCustomer {
    private static final Logger logger = LogManager.getLogger(NewCustomer.class);
    public Customer addNewCustomer( ) {
        logger.info("New Customer details");
        Customer customer = new Customer(1234567,"Anandhi","Jayapal","anandhirmk@gmail.com","9999999999");
        AccountUtil accountUtil = new AccountUtil();
        customer  = accountUtil.createAccount(customer);
        return customer;
    }
}
