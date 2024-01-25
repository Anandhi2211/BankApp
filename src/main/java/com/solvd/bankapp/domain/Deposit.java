package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Deposit {
    private BigDecimal depositAmount;
    private long accountNumber;
    private String username;
    private int transactionId;
    private Timestamp depositTimestamp;

    public Deposit(BigDecimal depositAmount, long accountNumber, String username, int transactionId) {
        this.depositAmount = depositAmount;
        this.accountNumber = accountNumber;
        this.username = username;
        this.transactionId = transactionId;
    }

    public Deposit() {

    }


    public BigDecimal getDepositAmount() {
        return depositAmount;
    }
    public long getAccountNumber() {
        return accountNumber;
    }
    public String getUsername() {
        return username;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public Timestamp getDepositTimestamp() {
        return depositTimestamp;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositAmount=" + depositAmount +
                ", accountNumber=" + accountNumber +
                ", username='" + username + '\'' +
                ", transactionId=" + transactionId +
                ", depositTimestamp=" + depositTimestamp +
                '}';
    }

    public static  Builder builder(){
        return new Builder(new Deposit());
    }
    public static class Builder{

        private final Deposit deposit;

        public Builder(Deposit deposit) {
            this.deposit = deposit;
        }

        public Builder setDepositAmount(BigDecimal depositAmount) {
            deposit.depositAmount = depositAmount;
            return this;
        }

        public Builder setAccountNumber(long accountNumber) {
            deposit.accountNumber = accountNumber;
            return this;
        }

        public Builder setUsername(String username) {
            deposit.username = username;
            return this;
        }

        public Builder setTransactionId(int transactionId) {
            deposit.transactionId = transactionId;
            return this;
        }

        public Builder setDepositTimestamp(Timestamp depositTimestamp) {
            deposit.depositTimestamp = depositTimestamp;
            return this;
        }

        public com.solvd.bankapp.domain.Deposit getDeposit() {
            return deposit;
        }
    }

}