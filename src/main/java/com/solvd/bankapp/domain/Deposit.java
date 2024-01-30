package com.solvd.bankapp.domain;

import com.solvd.bankapp.domain.patterns.TransactionBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Deposit extends Transaction {

    private Deposit(TransactionBuilder<?> builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements TransactionBuilder<Deposit> {
        private int transactionId;
        private BigDecimal amount;
        private String username;
        private long accountNumber;
        private Timestamp transactionTimestamp;

        private Builder() {
        }

        @Override
        public TransactionBuilder<Deposit> setTransactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        @Override
        public TransactionBuilder<Deposit> setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        @Override
        public TransactionBuilder<Deposit> setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        @Override
        public TransactionBuilder<Deposit> setTransactionTimestamp(Timestamp transactionTimestamp) {
            this.transactionTimestamp = transactionTimestamp;
            return this;
        }

        @Override
        public int getTransactionId() {
            return transactionId;
        }

        @Override
        public BigDecimal getAmount() {
            return amount;
        }

        public String getUsername() {
            return username;
        }

        @Override
        public long getAccountNumber() {
            return accountNumber;
        }

        @Override
        public Timestamp getTransactionTimestamp() {
            return transactionTimestamp;
        }

        @Override
        public Deposit build() {
            if (transactionId == 0 || amount == null || transactionTimestamp == null) {
                throw new IllegalArgumentException("TransactionId, Amount, and TransactionTimestamp are required.");
            }
            return new Deposit(this);
        }
    }
}