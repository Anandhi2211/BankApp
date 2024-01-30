package com.solvd.bankapp.service;

import com.solvd.bankapp.Main;
import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.LoginCredential;
import com.solvd.bankapp.persistence.LoginCredentialDAO;
import com.solvd.bankapp.persistence.mybatis.LoginCredentialDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class AtmTransfersUtil {

    private  final  LoginCredentialDAO loginCredentialDAO;
    private final AccountUtil accountUtil;
    private final TransactionUtil transactionUtil;

    private static final Logger logger = LogManager.getLogger(Main.class);

    public AtmTransfersUtil() {
        loginCredentialDAO = new LoginCredentialDAOImpl();
        accountUtil = new AccountUtil();
        transactionUtil = new TransactionUtil();
    }
    
    public void deposit(Scanner in) {
        logger.info("Enter the Amount to be deposited");
        BigDecimal amt = in .nextBigDecimal();
        logger.info("Enter username:");
        String username = in.next();
        LoginCredential loginCredential = loginCredentialDAO.findByUsername(username);
        if(loginCredential!=null){
            Account account = accountUtil.getAccount(username);
            logger.info("Enter pin Number:");
            int pin = in.nextInt();
            if(loginCredential.getPin() == pin){
                accountUtil.deposit(username,account.getAccountNumber(),amt);
                transactionUtil.addTransactions(account.getAccountNumber(),amt);
            }
            else{
                logger.info("Pin Not Matching");
            }
        }
        else{
            logger.info("Account Not Fount");
        }
    }

    public void withdraw(Scanner in) {
        logger.info("Enter the Amount to be deposited");
        BigDecimal amt = in .nextBigDecimal();
        logger.info("Enter username:");
        String username = in.next();
        LoginCredential loginCredential = loginCredentialDAO.findByUsername(username);
        if(loginCredential!=null){
            Account account = accountUtil.getAccount(username);
            logger.info("Enter pin Number:");
            int pin = in.nextInt();
            if(loginCredential.getPin() == pin){
                accountUtil.withdraw(username,account.getAccountNumber(),amt);
                transactionUtil.addTransactions(account.getAccountNumber(),amt);
            }
            else{
                logger.info("Pin Not Matching");
            }
        }
        else{
            logger.info("Account Not Fount");
        }


    }
}
