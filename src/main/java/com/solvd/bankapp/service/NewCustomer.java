package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.persistence.AccountDAO;
import com.solvd.bankapp.persistence.CustomerDAO;
import com.solvd.bankapp.persistence.mybatis.AccountDAOImpl;
import com.solvd.bankapp.persistence.mybatis.CustomerDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class NewCustomer {
    Scanner in = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(NewCustomer.class);
    private final AccountUtil accountUtil;
    private final CustomerDAO customerDAO;
    public NewCustomer() {
        customerDAO = new CustomerDAOImpl();
        this.accountUtil = new AccountUtil();
    }
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
//        long accountNumber = accountDAO.getAccountNumber();
//        logger.info("CURRENT account ID "+accountNumber);
//        accountNumber = (accountNumber != 0) ? accountNumber +1 : 1234567;
        Customer customer = new Customer(1234567 + 1, "Anandhi", "Jayapal", "anandhirmk@gmail.com", "9999999999");
//        Customer dbCustomer = customerDAO.findBySsn(customer.getSsn());
//        if (dbCustomer.getSsn() == customer.getSsn()) {
            accountUtil.createAccount(customer);
//        } else {
//            logger.info("Already bank account created");
//        }
    }

    public Customer findCustomer(long ssn) {
        return customerDAO.findBySsn(ssn);
    }
}

