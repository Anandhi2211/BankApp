package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private long accountNumber;
    private BigDecimal totalBalance;
    private String username;
    private ArrayList<Transaction> transactionList;

    Account() {
    }

    public long getAccountNumber() {
        return accountNumber;
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

    public List<Transaction> getTransactionList() {
        if (transactionList == null) {
            transactionList = new ArrayList<>();
        }
        return transactionList;
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
        return new Builder();
    }

    public static class Builder {
        private final Account account;

        private Builder() {
            this.account = new Account();
            this.account.totalBalance = BigDecimal.ZERO;
        }

        public Builder setAccountNumber(long accountNumber) {
            this.account.accountNumber = accountNumber;
            return this;
        }

        public Builder setTotalBalance(BigDecimal totalBalance) {
            this.account.totalBalance = totalBalance;
            return this;
        }

        public Builder setUsername(String username) {
            this.account.username = username;
            return this;
        }

        public Builder addTransaction(Transaction transaction) {
            if (this.account.transactionList == null) {
                this.account.transactionList = new ArrayList<>();
            }
            this.account.transactionList.add(transaction);
            return this;
        }

        public Account build() {
            if (account.accountNumber == 0 || account.totalBalance == null || account.username == null) {
                throw new IllegalStateException("AccountNumber, TotalBalance, and Username are required.");
            }
            return account;
        }
    }
}