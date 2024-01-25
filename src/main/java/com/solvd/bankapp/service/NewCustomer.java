package com.solvd.bankapp.service.Impl;

import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.service.ICustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewCustomer implements ICustomer {
    private static final Logger logger = LogManager.getLogger(NewCustomer.class);

    public void addNewCustomer() {
        logger.info("New ICustomer details");
        Customer customer = new Customer(1234567, "Anandhi", "Jayapal", "anandhirmk@gmail.com", "9999999999");
        AccountUtil accountUtil = new AccountUtil();
        accountUtil.createAccount(customer);
    }
}
