package com.solvd.bankapp.service.Impl;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.domain.LoginCredential;
import com.solvd.bankapp.domain.Transaction;
import com.solvd.bankapp.persistence.AccountDAO;
import com.solvd.bankapp.persistence.CustomerDAO;
import com.solvd.bankapp.persistence.LoginCredentialDAO;
import com.solvd.bankapp.persistence.TransactionDAO;
import com.solvd.bankapp.persistence.mybatis.AccountDAOImpl;
import com.solvd.bankapp.persistence.mybatis.CustomerDAOImpl;
import com.solvd.bankapp.persistence.mybatis.LoginCredentialDAOImpl;
import com.solvd.bankapp.persistence.mybatis.TransactionDAOImpl;
import com.solvd.bankapp.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountUtil implements IAccount {

    private final AccountDAO accountDAO;
    private final LoginCredentialDAO loginCredentialDAO;
    private final CustomerDAO customerDAO;
    private final TransactionDAO transactionDAO;
    private final ITransaction iTransaction;
    private final ICustomer iCustomer;
    private static final Logger logger = LogManager.getLogger(AccountUtil.class);
    Scanner in = new Scanner(System.in);

    public AccountUtil() {
        this.accountDAO = new AccountDAOImpl();
        this.loginCredentialDAO = new LoginCredentialDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
        this.transactionDAO = new TransactionDAOImpl();
        this.iTransaction = new TransactionUtil();
        this.iCustomer = new NewCustomer();

    }

    public void createAccount(Customer customer) {
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
                    if (customer != null) {
                        TransactionUtil transactionUtil = new TransactionUtil();
//                        customer =
                        this.customerDAO.create(customer);
                        this.loginCredentialDAO.create(customer.getLoginCredential());
                        this.accountDAO.create(customer.getAccount());
                        transactionUtil.addTransactions(customer.getAccount().getAccountNumber(), amt);
//                        for (Transaction transaction : customer.getAccount().getTransactionList()) {
//                            this.transactionDAO.create(transaction);
//                        }

                    } else {
                        customer = null;
                    }
                    break;
                }
                case -1: {
                    logger.info("Enter minimum balance at least 100$");
                    logger.info("Account Not Created");
                    break;
                }
            }
        }
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
            loginCredential = new LoginCredential(account.getUsername(), password, true, pin, customer.getSsn());
            customer.setLoginCredential(loginCredential);
        } else {
            logger.info("Mismatch Password");
            customer = null;
        }
        return customer;
    }

    public void displayAccountDetails(String userName) {
//        this.accountDAO.display(userName);
        logger.info("Account Details");

    }


    @Override
    public void login() {
        logger.info("Enter Login userName");
    }
}