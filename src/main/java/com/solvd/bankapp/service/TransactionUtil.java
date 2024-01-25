package com.solvd.bankapp.service.Impl;

import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.domain.Transaction;
import com.solvd.bankapp.persistence.TransactionDAO;
import com.solvd.bankapp.persistence.mybatis.TransactionDAOImpl;
import com.solvd.bankapp.service.ITransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

public class TransactionUtil implements ITransaction {
    private static int transactionID = 20000;
    private final TransactionDAO transactionDAO;
    private static final Logger logger = LogManager.getLogger(TransactionUtil.class);

    public TransactionUtil() {
        this.transactionDAO = new TransactionDAOImpl();
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
        this.transactionDAO.create(transaction);
    }
}
