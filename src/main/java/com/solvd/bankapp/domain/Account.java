package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {

    private long accountNumber;

    private BigDecimal totalBalance;

    private String userName;

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

    private ArrayList<Transaction> transactionList;


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