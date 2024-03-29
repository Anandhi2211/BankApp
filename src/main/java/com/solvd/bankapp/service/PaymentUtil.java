package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.*;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.LoginCredentialDAO;
import com.solvd.bankapp.persistence.PaymentDAO;
import com.solvd.bankapp.persistence.mybatis.LoginCredentialDAOImpl;
import com.solvd.bankapp.persistence.mybatis.PaymentDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Scanner;

public class PaymentUtil {
    private static final Logger logger = LogManager.getLogger(PaymentUtil.class);
    private final TransactionUtil transactionUtil;
    private final NewCustomer customer;
    private final LoginCredentialDAO loginCredentialDAO;
    private final PaymentDAO paymentDAO;

    public PaymentUtil() {
        this.paymentDAO = new PaymentDAOImpl();
        this.transactionUtil = new TransactionUtil();
        this.customer = new NewCustomer();
        this.loginCredentialDAO = new LoginCredentialDAOImpl();
    }

    public void PayBillPage(Account account, Scanner in) {
        do {
            logger.info("1. Add Bills");
            logger.info("2. Exit");
            logger.info("Enter your options: ");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 2)) {
                throw new BankException("Invalid Input");
            }
            switch (answer) {
                case 1: {
                    logger.info("Enter Bill Account Number");
                    int billAccountNumber = in.nextInt();
                    Payment payment = this.paymentDAO.findPaymentById(billAccountNumber);
                    if (payment == null) {
                        logger.info("Enter the Bill company Name: ");
                        String billCompanyName = in.next();
                        logger.info("Enter the Bill Amount");
                        BigDecimal amt = in.nextBigDecimal();
                        if (amt.compareTo(account.getTotalBalance()) == -1) {
                            LoginCredential loginCredential = this.loginCredentialDAO.findByUsername(account.getUsername());
                            Transaction transaction = this.transactionUtil.addTransactions(account.getAccountNumber(), amt);
                            Customer customer = this.customer.getCustomerByUserName(loginCredential.getUsername());
                            payment = Payment.builder()
                                    .setCompanyAccountNumber(billAccountNumber)
                                    .setCompanyName(billCompanyName)
                                    .setBillAmount(amt)
                                    .setSsn(customer.getSsn())
                                    .setUsername(account.getUsername())
                                    .setPaymentTimestamp(transaction.getTransactionTimestamp())
                                    .setTransactionId(transaction.getTransactionId()).build();
                            this.paymentDAO.create(payment);
                        } else {
                            logger.info("Not sufficient Balance");
                        }
                    } else {
                        logger.info("Already Bill Exists");
                    }
                }
                break;
                case 2:
                    logger.info("Exit");
                    return;
                default:
                    return;
            }
        } while (true);
    }
}