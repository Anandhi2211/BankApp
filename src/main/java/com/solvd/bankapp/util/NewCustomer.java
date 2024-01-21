package com.solvd.bankapp.util;

import com.solvd.bankapp.Main;
import com.solvd.bankapp.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewCustomer {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public Customer addNewCustomer() {
        System.out.println("New Customer details");
        Customer customer = new Customer(123456,"Anandhi","Jayapal","anandhirmk@gmail.com","9999999999");
        NewAccount newAccount = new NewAccount();
        customer  = newAccount.addNewAccount(customer);
        return customer;
    }
}
