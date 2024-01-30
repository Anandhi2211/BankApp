package com.solvd.bankapp.domain.patterns;

import com.solvd.bankapp.domain.Transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface TransactionBuilder<T extends Transaction> {
    TransactionBuilder<T> setTransactionId(int transactionId);
    TransactionBuilder<T> setAmount(BigDecimal amount);
    TransactionBuilder<T> setAccountNumber(long accountNumber);
    TransactionBuilder<T> setTransactionTimestamp(Timestamp transactionTimestamp);

    int getTransactionId();
    BigDecimal getAmount();
    long getAccountNumber();
    Timestamp getTransactionTimestamp();

    T build();
}
