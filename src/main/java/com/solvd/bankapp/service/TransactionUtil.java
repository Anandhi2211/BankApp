package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.domain.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionUtil {
    private static final Logger logger = LogManager.getLogger(TransactionUtil.class);
    public Customer createTransaction(Customer customer, BigDecimal amt) {
        if(customer!=null){
            Transaction transaction = new Transaction(12345, amt,true,customer.getAccount().getAccountNumber());
            customer.getAccount().setTransactionList(transaction);
        }
        else{
            customer = null;
        }
        return customer;
    }
}
