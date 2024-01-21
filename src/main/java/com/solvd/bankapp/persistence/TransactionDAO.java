package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Transaction;
import java.util.List;
import java.util.Optional;

public interface TransactionDAO extends BaseListDAO<Transaction> {
    @Override
    void create(Transaction transaction);

    @Override
    Optional<Transaction> findById(int transactionId);

    @Override
    List<Transaction> getAll();
}
