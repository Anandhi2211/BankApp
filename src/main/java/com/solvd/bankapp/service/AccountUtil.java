package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.*;
import com.solvd.bankapp.persistence.AccountDAO;
import com.solvd.bankapp.persistence.CustomerDAO;
import com.solvd.bankapp.persistence.LoginCredentialDAO;
import com.solvd.bankapp.persistence.mybatis.AccountDAOImpl;
import com.solvd.bankapp.persistence.mybatis.CustomerDAOImpl;
import com.solvd.bankapp.persistence.mybatis.LoginCredentialDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountUtil implements IAccount {

    private static long accountNumber = 300005000;
    private final AccountDAO accountDAO;
    private final LoginCredentialDAO loginCredentialDAO;
    private final CustomerDAO customerDAO;
    private static final Logger logger = LogManager.getLogger(AccountUtil.class);

    public AccountUtil() {
        this.accountDAO = new AccountDAOImpl();
        this.loginCredentialDAO = new LoginCredentialDAOImpl();
        this.customerDAO = new CustomerDAOImpl();
    }

    public void createAccount(Customer customer, Scanner in) {
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
                    account = Account.builder()
                            .setAccountNumber(accountNumber++)
                            .setUsername(userName)
                            .setTotalBalance(amt).build();
                    customer = setLoginDetails(account, customer, in);
                    if (customer != null) {
                        TransactionUtil transactionUtil = new TransactionUtil();
                        this.customerDAO.create(customer);
                        this.loginCredentialDAO.create(customer.getLoginCredential());
                        this.accountDAO.create(account);
                        Transaction transaction = transactionUtil.addTransactions(account.getAccountNumber(), amt);
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

    public Customer setLoginDetails(Account account, Customer customer, Scanner in) {
        LoginCredential loginCredential;
        logger.info("Enter NetBanking Password");
        String password = in.next();
        logger.info("Retype the Password");
        String retypePassword = in.next();
        if (password.equals(retypePassword)) {
            int pin = Integer.parseInt(Long.toString(customer.getSsn()).substring(Long.toString(customer.getSsn()).length() - 4));
            logger.info("You Pin Number: " + pin);
            loginCredential = LoginCredential.builder()
                    .setUsername(account.getUsername())
                    .setUserPassword(password)
                    .setActiveStatus(true)
                    .setPin(pin)
                    .setSsn(customer.getSsn()).build();
            customer.setLoginCredential(loginCredential);
        } else {
            logger.info("Mismatch Password");
            customer = null;
        }
        return customer;
    }

    public void displayAccountDetails(String username, Scanner in) {
        logger.info("Account Details");
        NotificationService notificationService = new NotificationService();
        Account account = this.accountDAO.findAccountByUsername(username);
        notificationService.update(account);
        Customer customer = this.customerDAO.display(username);
        logger.info("Account number: " + account.getAccountNumber());
        logger.info("Account balance" + account.getTotalBalance());
        logger.info("Account UserName" + account.getUsername());
        logger.info("First Name: " + customer.getFirstName());
        logger.info("Last Name: " + customer.getLastName());

    }

    @Override
    public void updateAmount(long accountNumber, BigDecimal amount) {
        this.accountDAO.update(accountNumber, amount);
    }

    @Override
    public Account getAccount(String userName) {
        return accountDAO.findAccountByUsername(userName);
    }

    @Override
    public void deposit(String username, long accountNumber, BigDecimal amt) {
        accountDAO.deposit(username, accountNumber, amt);
    }

    @Override
    public void withdraw(String username, long accountNumber, BigDecimal amt) {
        accountDAO.withdraw(username, accountNumber, amt);
    }
}
