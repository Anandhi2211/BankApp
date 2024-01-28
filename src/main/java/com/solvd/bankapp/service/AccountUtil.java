package com.solvd.bankapp.service;

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
import com.solvd.bankapp.service.IAccount;
//import com.solvd.bankapp.service.ICustomer;
import com.solvd.bankapp.service.ITransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AccountUtil implements IAccount {

    private static long accountNumber = 300005000;
    private final AccountDAO accountDAO;
    private final LoginCredentialDAO loginCredentialDAO;
    private final CustomerDAO customerDAO;
    private static final Logger logger = LogManager.getLogger(AccountUtil.class);
    Scanner in = new Scanner(System.in);

    public AccountUtil() {
        this.accountDAO = new AccountDAOImpl();
        this.loginCredentialDAO = new LoginCredentialDAOImpl();
        customerDAO = new CustomerDAOImpl();

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
                    account = new Account(accountNumber++, amt, userName);
                    customer.setAccount(account);
                    customer = setLoginDetails(account, customer);
                    if (customer != null) {
                        TransactionUtil transactionUtil = new TransactionUtil();
                        this.customerDAO.create(customer);
                        this.loginCredentialDAO.create(customer.getLoginCredential());
                        this.accountDAO.create(customer.getAccount());
                        Transaction transaction = transactionUtil.addTransactions(customer.getAccount().getAccountNumber(), amt);
                        customer.getAccount().setTransactionList(transaction);
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
            logger.info("You Pin Number: "+pin);
            loginCredential = new LoginCredential(account.getUsername(), password, true, pin, customer.getSsn());
            customer.setLoginCredential(loginCredential);
        } else {
            logger.info("Mismatch Password");
            customer = null;
        }
        return customer;
    }
    public void displayAccountDetails(String username) {
        //can add update methods to update account
        logger.info("Account Details");
        Account account = accountDAO.findAccountByUsername(username);
        Customer customer = customerDAO.display(username);
        logger.info("Account number: " + account.getAccountNumber());
        logger.info("Account balance" + account.getTotalBalance());
        logger.info("Account UserName" + account.getUsername());
        logger.info("First Name: "+customer.getFirstName());
        logger.info("Last Name: "+customer.getLastName());
    }

//    public BigDecimal getTotaleBalance(long accountNumber) {
//        return accountDAO.displayTotalBalance(accountNumber);
//    }

    @Override
    public void updateAmount(long accountNumber, BigDecimal amount) {
        this.accountDAO.update(accountNumber, amount);
    }

    @Override
    public Account getAccount(String userName) {
        return accountDAO.findAccountByUsername(userName);
    }

}
