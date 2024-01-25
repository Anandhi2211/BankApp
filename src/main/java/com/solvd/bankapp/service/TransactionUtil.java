package com.solvd.bankapp.service;

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
    private static int transactionID = 20000;
    private final TransactionDAO transactionDAO;
    Scanner in = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(TransactionUtil.class);

    public TransactionUtil() {
        this.transactionDAO = new TransactionDAOImpl();
    }

    @Override
    public void transactionPage(long accountNumber) {
        do{
            logger.info("1. Transaction History");
            logger.info("2. Filter by Date");
            logger.info("3. Find by Transaction Number");
            logger.info("Enter your options: ");
            int answer = in.nextInt();
            if (!(answer >= 1) || !(answer <= 3)) {
                throw new BankException("Invalid Input");
            }
            switch (answer){
                case 1:{
                    ArrayList<Transaction> transactionList = (ArrayList<Transaction>) transactionDAO.getAll();//modify add account Number parameter
                    for(Transaction transaction : transactionList){
                        logger.info("Account Number: "+accountNumber);
                        logger.info("Transaction Number: "+transaction.getTransactionId());
                        logger.info("Transaction Amount: " + transaction.getAmount());
                        logger.info("Transaction Status: "+ transaction.isTransactionStatus());
                        logger.info("Transaction Time: "+ transaction.getTransactionTimestamp());
                    }
                }
                    break;
                case 2: {
                    logger.info("Enter the date in YYYY-MM-DD Format: ");
                    String date = in.next();
//                    Arralist <> = transactionDAO.findByDate(date);
                    ArrayList<Transaction> transactionList = (ArrayList<Transaction>) transactionDAO.getAll();//modify add account Number parameter
                    for(Transaction transaction : transactionList){
                        if(transaction.getTransactionTimestamp().contains(date)){
                            logger.info("Account Number: "+accountNumber);
                            logger.info("Transaction Number: "+transaction.getTransactionId());
                            logger.info("Transaction Amount: " + transaction.getAmount());
                            logger.info("Transaction Status: "+ transaction.isTransactionStatus());
                            logger.info("Transaction Time: "+ transaction.getTransactionTimestamp());}
                    }
                }
                    break;
                case 3:{
                    logger.info("Enter the Transaction ID: ");
                    int transactionId = in.nextInt();
                    Optional<Transaction> transactions = transactionDAO.findById(transactionId);
                    if(transactions.isPresent()){
                        ArrayList<Transaction> transactionArrayList = (ArrayList<Transaction>) transactions.stream().collect(Collectors.toList());
                            for(Transaction transaction : transactionArrayList){
                                logger.info("Account Number: "+accountNumber);
                                logger.info("Transaction Number: "+transaction.getTransactionId());
                                logger.info("Transaction Amount: " + transaction.getAmount());
                                logger.info("Transaction Status: "+ transaction.isTransactionStatus());
                                logger.info("Transaction Time: "+ transaction.getTransactionTimestamp());
                        }
                    }
                }
                    break;
                default:
                    return;
            }
        }while (true);

    }

    public Customer createTransaction(Customer customer, BigDecimal amt) {
        if(customer!=null){
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            Transaction transaction = new Transaction(12345, amt,true,customer.getAccount().getAccountNumber(),currentTime.toString());
//            customer.getAccount().setTransactionList(transaction);
        }
        else{
            customer = null;
        }
        return customer;
    }
    @Override
    public void addTransactions(long accountNumber, BigDecimal amount) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Transaction transaction = new Transaction(transactionID++,amount,true,accountNumber,currentTime.toString());
        //set Account
        this.transactionDAO.create(transaction);
    }


}
