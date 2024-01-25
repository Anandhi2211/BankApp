package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private BigDecimal amount;
    private boolean transactionStatus;
    private long accountNumber;
    private String transactionTimestamp;
    public Transaction(int transactionId, BigDecimal amount, boolean transactionStatus,
                       long accountNumber, String currentTime) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
        this.accountNumber = accountNumber;
        this.transactionTimestamp = currentTime;
    }
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(boolean transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionTimestamp() {
        return transactionTimestamp;
    }

    public void setTransactionTimestamp(String transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", accountNumber=" + accountNumber +
                ", transactionTimestamp=" + transactionTimestamp +
                '}';
    }
}