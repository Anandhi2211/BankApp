package com.solvd.bankapp;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.util.NewAccount;
import com.solvd.bankapp.util.NewCustomer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        System.out.println("daaa sdfsdf!");

        logger.info("Enter the choices: ");

        NewCustomer newCustomer = new NewCustomer();
        Customer customer = newCustomer.addNewCustomer();
        NewAccount newAccount = new NewAccount();
        logger.info(customer);
        Account account = newAccount.addNewAccount(customer);
        logger.info(account);
    }
}