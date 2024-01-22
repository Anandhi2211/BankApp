package com.solvd.bankapp.util;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.domain.LoginCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountUtil {
    private static final Logger logger = LogManager.getLogger(AccountUtil.class);
    Scanner in = new Scanner(System.in);

    public Customer createAccount(Customer customer) {
        Account account;
        if (customer != null) {
            logger.info("Deposit minimum balance of 100$");
            BigDecimal amt = in.nextBigDecimal();
            int answer = amt.compareTo(BigDecimal.valueOf(100));
            switch (answer) {
                case 0:
                case 1: {
                    logger.info("Account Created");
                    String userName = customer.getFirstName() + "_" + customer.getSsn();
                    account = new Account(1122334455, amt, userName);
                    customer.setAccount(account);
                    customer = setLoginDetails(account, customer);
                    TransactionUtil transactionUtil = new TransactionUtil();
                    customer = transactionUtil.createTransaction(customer, amt);
                    break;
                }
                case -1: {
                    logger.info("Enter minimum balance at least 100$");
                    logger.info("Account Not Created");
                    customer = null;
                    break;
                }
            }
        }
        return customer;
    }
    public Customer setLoginDetails(Account account, Customer customer) {
        LoginCredential loginCredential;
        logger.info("Enter NetBanking Password");
        String password = in.next();
        logger.info("Retype the Password");
        String retypePassword = in.next();
        if (password.equals(retypePassword)) {
            int pin = Integer.parseInt(Long.toString(customer.getSsn()).substring(Long.toString(customer.getSsn()).length() - 4));
            logger.info(pin);
            loginCredential = new LoginCredential(account.getUserName(), password, true, pin, customer.getSsn());
            customer.setLoginCredential(loginCredential);
        } else {
            logger.info("Mismatch Password");
            customer = null;
        }
        return customer;
    }

    public void displayAccountDetails(String userName) {
        logger.info("Account Details");

    }
}
