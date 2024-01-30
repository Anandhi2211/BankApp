package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {
    private final int transactionId;
    private final BigDecimal amount;
    private final boolean transactionStatus;
    private final long accountNumber;
    private final Timestamp transactionTimestamp;

    private Transaction(Builder builder) {
        this.transactionId = builder.transactionId;
        this.amount = builder.amount;
        this.transactionStatus = builder.transactionStatus;
        this.accountNumber = builder.accountNumber;
        this.transactionTimestamp = builder.transactionTimestamp;
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
                ", transactionStatus=" + transactionStatus +
                ", accountNumber=" + accountNumber +
                ", transactionTimestamp=" + transactionTimestamp +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int transactionId;
        private BigDecimal amount;
        private boolean transactionStatus;
        private long accountNumber;
        private Timestamp transactionTimestamp;

        private Builder() {
        }

        public Builder setTransactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setTransactionStatus(boolean transactionStatus) {
            this.transactionStatus = transactionStatus;
            return this;
        }

        public Builder setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setTransactionTimestamp(Timestamp transactionTimestamp) {
            this.transactionTimestamp = transactionTimestamp;
            return this;
        }

        public Transaction build() {
            if (transactionId == 0 || amount == null) {
                throw new IllegalArgumentException("transactionId and amount are required.");
            }
            return new Transaction(this);
        }
    }
}