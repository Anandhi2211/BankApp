package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface TransactionBuilder<T extends Transaction> {
    TransactionBuilder<T> setTransactionId(int transactionId);
    TransactionBuilder<T> setAmount(BigDecimal amount);
    TransactionBuilder<T> setTransactionStatus(boolean transactionStatus);
    TransactionBuilder<T> setAccountNumber(long accountNumber);
    TransactionBuilder<T> setTransactionTimestamp(Timestamp transactionTimestamp);

    int getTransactionId();
    BigDecimal getAmount();
    boolean getTransactionStatus();
    long getAccountNumber();
    Timestamp getTransactionTimestamp();

    T build();
}
