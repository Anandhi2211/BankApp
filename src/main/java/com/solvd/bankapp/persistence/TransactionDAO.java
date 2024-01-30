package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.ConcreteTransaction;
import com.solvd.bankapp.domain.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionDAO extends BaseListDAO<Transaction> {
    @Override
    void create(@Param("transaction") ConcreteTransaction transaction);

    @Override
    Optional<Transaction> findById(int transactionId);

    @Override
    List<Transaction> getAll();
}
