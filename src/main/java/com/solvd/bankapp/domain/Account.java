package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {

    private long accountNumber;

    private BigDecimal totalBalance;

    private String username;

    private ArrayList<Transaction> transactionList;

    public Account() {
    }

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

    public ArrayList<Transaction> getTransactionList() {
        if(this.transactionList==null){
            this.transactionList = new ArrayList<>();
        }
        return this.transactionList;
    }

    public void setTransactionList(Transaction transaction) {
        if(this.transactionList==null){
            this.transactionList = new ArrayList<>();
        }
        this.transactionList.add(transaction);
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