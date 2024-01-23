package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class Account {

    private long accountNumber;

    private BigDecimal totalBalance;

    private String username;

    public Account(long accountNumber, BigDecimal totalBalance, String username) {
        this.accountNumber = accountNumber;
        this.totalBalance = totalBalance;
        this.username = username;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", totalBalance=" + totalBalance +
                ", username='" + username + '\'' +
                '}';
    }
}