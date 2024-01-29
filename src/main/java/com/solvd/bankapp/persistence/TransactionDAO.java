package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TransactionDAO extends BaseListDAO<Transaction> {
    @Override
    void create(@Param("transaction") Transaction transaction);

//    @Override
//    Optional<Transaction> findById(@Param("transactionId") int transactionId);

//    @Override
    ArrayList<Transaction> getAll(); // we can remove this

    ArrayList<Transaction> getTransactionHistory(long accountNumber);

    int getTransactionId();
}
