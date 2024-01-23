package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class Transaction {

    private int transactionId;
    private BigDecimal amount;
    // may consider creating an enum class just for this field
    private boolean transactionStatus;
    private long accountNumber;

    public Transaction(int transactionId, BigDecimal amount, boolean transactionStatus, long accountNumber) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
        this.accountNumber = accountNumber;
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

    public boolean getTransactionStatus() {
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

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }
}