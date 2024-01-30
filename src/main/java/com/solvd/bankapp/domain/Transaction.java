package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;


public abstract class Transaction {
    private final int transactionId;
    private final BigDecimal amount;
    private final boolean transactionStatus;
    private final long accountNumber;
    private final Timestamp transactionTimestamp;

    public Transaction(TransactionBuilder<? extends Transaction> builder) {
        this.transactionId = builder.getTransactionId();
        this.amount = builder.getAmount();
        this.transactionStatus = builder.getTransactionStatus();
        this.accountNumber = builder.getAccountNumber();
        this.transactionTimestamp = builder.getTransactionTimestamp();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isTransactionStatus() {
        return transactionStatus;
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
                ", transactionStatus=" + transactionStatus +
                ", accountNumber=" + accountNumber +
                ", transactionTimestamp=" + transactionTimestamp +
                '}';
    }
}