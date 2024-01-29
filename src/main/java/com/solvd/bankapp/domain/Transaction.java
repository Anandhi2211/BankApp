package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private BigDecimal amount;
    private boolean transactionStatus;
    private long accountNumber;
    private Timestamp transactionTimestamp;

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isTransactionStatus() {
        return transactionStatus;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Timestamp getTransactionTimestamp() {
        return transactionTimestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", accountNumber=" + accountNumber +
                ", transactionTimestamp=" + transactionTimestamp +
                '}';
    }
    public static Builder builder(){
        return new Builder (new Transaction());
    }

    public static class Builder{
        private final Transaction transaction;

        public Builder(com.solvd.bankapp.domain.Transaction transaction) {
            this.transaction = transaction;
        }

        public Builder setTransactionId(int transactionId) {
            transaction.transactionId = transactionId;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            transaction.amount = amount;
            return this;
        }

        public Builder setTransactionStatus(boolean transactionStatus) {
            transaction.transactionStatus = transactionStatus;
            return this;
        }

        public Builder setAccountNumber(long accountNumber) {
            transaction.accountNumber = accountNumber;
            return this;
        }

        public Builder setTransactionTimestamp(Timestamp transactionTimestamp) {
            transaction.transactionTimestamp = transactionTimestamp;
            return this;
        }

        public com.solvd.bankapp.domain.Transaction getTransaction() {
            return transaction;
        }
    }
}