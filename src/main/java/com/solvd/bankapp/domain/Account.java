package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class Account {

    long accountNumber;

    BigDecimal totalBalance;

    String userName;

    public Account(long accountNumber, BigDecimal totalBalance, String userName) {
        this.accountNumber = accountNumber;
        this.totalBalance = totalBalance;
        this.userName = userName;
    }

    public Account() {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", totalBalance=" + totalBalance +
                ", userName='" + userName + '\'' +
                '}';
    }
}