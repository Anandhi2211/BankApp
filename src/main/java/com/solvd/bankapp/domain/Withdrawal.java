package com.solvd.bankapp.domain;

import com.solvd.bankapp.domain.patterns.TransactionBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Withdrawal extends Transaction {

    private Withdrawal (TransactionBuilder<?> builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements TransactionBuilder<Withdrawal> {
        private int transactionId;
        private BigDecimal amount;
        private boolean transactionStatus;
        private long accountNumber;
        private Timestamp transactionTimestamp;

        private Builder() {
        }

        @Override
        public TransactionBuilder<Withdrawal> setTransactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        @Override
        public TransactionBuilder<Withdrawal> setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public TransactionBuilder<Withdrawal> setTransactionStatus(boolean transactionStatus) {
            this.transactionStatus = transactionStatus;
            return this;
        }

        @Override
        public TransactionBuilder<Withdrawal> setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        @Override
        public TransactionBuilder<Withdrawal> setTransactionTimestamp(Timestamp transactionTimestamp) {
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

        @Override
        public boolean getTransactionStatus() {
            return transactionStatus;
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
        public Withdrawal build() {
            if (transactionId == 0 || amount == null || transactionTimestamp == null) {
                throw new IllegalArgumentException("TransactionId, Amount, and TransactionTimestamp are required.");
            }
            return new Withdrawal(this);
        }
    }
}
