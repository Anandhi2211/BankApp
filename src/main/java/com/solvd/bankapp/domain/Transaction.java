package com.solvd.bankapp.domain;

import com.solvd.bankapp.domain.patterns.TransactionBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;


public abstract class Transaction {
    private final int transactionId;
    private final BigDecimal amount;
    private final long accountNumber;
    private final Timestamp transactionTimestamp;

    public Transaction(TransactionBuilder<? extends Transaction> builder) {
        this.transactionId = builder.getTransactionId();
        this.amount = builder.getAmount();
        this.accountNumber = builder.getAccountNumber();
        this.transactionTimestamp = builder.getTransactionTimestamp();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Timestamp getTransactionTimestamp() {
        return transactionTimestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", accountNumber=" + accountNumber +
                ", transactionTimestamp=" + transactionTimestamp +
                '}';
    }
}