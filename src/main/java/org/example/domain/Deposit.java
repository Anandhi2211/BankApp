package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class Deposit {

    private BigDecimal depositAmount;

    private long accountNumber;

    private String username;

    private int transactionId;

    public Deposit(BigDecimal depositAmount, long accountNumber, String username, int transactionId) {
        this.depositAmount = depositAmount;
        this.accountNumber = accountNumber;
        this.username = username;
        this.transactionId = transactionId;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositAmount=" + depositAmount +
                ", accountNumber=" + accountNumber +
                ", username='" + username + '\'' +
                ", transactionId=" + transactionId +
                '}';
    }
}
