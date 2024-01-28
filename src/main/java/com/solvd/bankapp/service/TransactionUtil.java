package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.domain.Transaction;
import com.solvd.bankapp.exception.BankException;
import com.solvd.bankapp.persistence.TransactionDAO;
import com.solvd.bankapp.persistence.mybatis.TransactionDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TransactionUtil implements ITransaction {
    //    private static int transactionID = 20000;
    private final TransactionDAO transactionDAO;
    private final AccountUtil accountUtil;
    Scanner in = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(TransactionUtil.class);

    public TransactionUtil() {
        this.transactionDAO = new TransactionDAOImpl();
        this.accountUtil = new AccountUtil();
    }

    @Override
    public void transactionPage(Account account) {
        do {
            account = this.accountUtil.getAccount(account.getUsername());
            logger.info("1. Transaction History");
            logger.info("2. Filter by Date");
            logger.info("3. Find by Transaction Number");
            logger.info("4. Exiting");
            logger.info("Enter your options: ");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 4)) {
                throw new BankException("Invalid Input");
            }
            switch (answer) {
                case 1: {
                    ArrayList<Transaction> transactions = this.transactionDAO.getTransactionHistory(account.getAccountNumber());
                    if (transactions != null) {
                        for (Transaction transaction : transactions) {
                            logger.info("Account Number: " + account.getAccountNumber());
                            logger.info("Transaction Number: " + transaction.getTransactionId());
                            logger.info("Transaction Amount: " + transaction.getAmount());
                            logger.info("Transaction Status: " + transaction.isTransactionStatus());
                            logger.info("Transaction Time: " + transaction.getTransactionTimestamp());
                        }
                    }
                }
                break;
                case 2: {
                    logger.info("Enter the date in YYYY-MM-DD Format: ");
                    String date = in.next();
                    ArrayList<Transaction> transactions = this.transactionDAO.getTransactionHistory(account.getAccountNumber());
                    if (!transactions.isEmpty()) {
                        for (Transaction transaction : transactions) {
                            if (transaction.getTransactionTimestamp().contains(date)) {
                                logger.info("Account Number: " + account.getAccountNumber());
                                logger.info("Transaction Number: " + transaction.getTransactionId());
                                logger.info("Transaction Amount: " + transaction.getAmount());
                                logger.info("Transaction Status: " + transaction.isTransactionStatus());
                                logger.info("Transaction Time: " + transaction.getTransactionTimestamp());
                            }
                        }
                    }
                }
                break;
                case 3: {
                    logger.info("Enter the Transaction ID: ");
                    int transactionId = in.nextInt();
                    ArrayList<Transaction> transactions = this.transactionDAO.getTransactionHistory(account.getAccountNumber());
                    if (transactions != null) {
                        for (Transaction transaction : transactions) {
                            if (transaction.getTransactionId() == transactionId) {
                                logger.info("Account Number: " + account.getAccountNumber());
                                logger.info("Transaction Number: " + transaction.getTransactionId());
                                logger.info("Transaction Amount: " + transaction.getAmount());
                                logger.info("Transaction Status: " + transaction.isTransactionStatus());
                                logger.info("Transaction Time: " + transaction.getTransactionTimestamp());
                            }
                        }
                    }
                }
                break;
                case 4:
                    logger.info("Exiting");
                    return;
                default:
                    return;
            }
        } while (true);
    }

    @Override
    public Transaction addTransactions(long accountNumber, BigDecimal amount) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        int transactionID = transactionDAO.getTransactionId();
        logger.info("CURRENT TRANSACTION ID " + transactionID);
        transactionID = (transactionID != 0) ? transactionID + 1 : 20000;
        Transaction transaction = new Transaction(transactionID, amount, true, accountNumber, currentTime.toString());
        this.transactionDAO.create(transaction);
        return transaction;
    }
}
