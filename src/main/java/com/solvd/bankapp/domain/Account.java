package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {
    private long accountNumber;
    private BigDecimal totalBalance;
    private String username;
    private ArrayList<Transaction> transactionList;

    public Account(long accountNumber, BigDecimal totalBalance, String username) {
        this.accountNumber = accountNumber;
        this.totalBalance = totalBalance;
        this.username = username;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Account() {
    }

    public long getAccountNumber() {
        return accountNumber;
    }
    public BigDecimal getTotalBalance() {
        return totalBalance;
    }
    public String getUsername() {
        return username;
    }
    public ArrayList<Transaction> getTransactionList() {
        if(this.transactionList==null){
            this.transactionList = new ArrayList<>();
        }
        return this.transactionList;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", totalBalance=" + totalBalance +
                ", username='" + username + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder(new Account());
    }
  public static class Builder{
        private  final Account account;
      public Builder(Account account) {
          this.account = account;
      }
      public Builder setAccountNumber(long accountNumber) {
          account.accountNumber = accountNumber;
          return this;
      }
      public Builder setTotalBalance(BigDecimal totalBalance) {
          account.totalBalance = totalBalance;
          return this;
      }
      public Builder setUsername(String username) {
          account.username = username;
          return this;
      }
      public Builder setTransactionList(ArrayList<Transaction> transactionList) {
          account.transactionList = transactionList;
          return this;
      }

      public  Account getAccount(){
          return  account;
      }

  }

}