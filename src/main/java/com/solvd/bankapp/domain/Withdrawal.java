package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Withdrawal {

    private BigDecimal withdrawalAmount;

    private long accountNumber;

    private String username;

    private int transactionId;

    private Timestamp withdrawalTimestamp;

    public Withdrawal(BigDecimal withdrawalAmount, long accountNumber, String username,
                      int transactionId) {
        this.withdrawalAmount = withdrawalAmount;
        this.accountNumber = accountNumber;
        this.username = username;
        this.transactionId = transactionId;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
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

    public Timestamp getWithdrawalTimestamp() {
        return withdrawalTimestamp;
    }

    public void setWithdrawalTimestamp(Timestamp withdrawalTimestamp) {
        this.withdrawalTimestamp = withdrawalTimestamp;
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "withdrawalAmount=" + withdrawalAmount +
                ", accountNumber=" + accountNumber +
                ", username='" + username + '\'' +
                ", transactionId=" + transactionId +
                ", withdrawalTimestamp=" + withdrawalTimestamp +
                '}';
    }
}