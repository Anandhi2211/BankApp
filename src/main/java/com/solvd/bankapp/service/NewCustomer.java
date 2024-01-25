package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class NewCustomer implements ICustomer {
    Scanner in = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(NewCustomer.class);

    public void addNewCustomer() {
        logger.info("New Customer details");
//        logger.info("Enter your SSN: ");
//        long ssn = in.nextLong();
//        logger.info("Enter your First Name: ");
//        String firstname = in.next();
//        logger.info("Enter your Last Name: ");
//        String lastname = in.next();
//        logger.info("Email Id: ");
//        String email = in.next();
//        logger.info("Enter Mobile Number: ");
//        String mobileNumber = in.next();
//        Customer customer = new Customer(ssn, firstname, lastname, email, mobileNumber);

        Customer customer = new Customer(1234567, "Anandhi", "Jayapal", "anandhirmk@gmail.com", "9999999999");
        AccountUtil accountUtil = new AccountUtil();
        accountUtil.createAccount(customer);
    }
}
