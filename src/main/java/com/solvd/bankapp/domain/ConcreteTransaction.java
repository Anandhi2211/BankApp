package com.solvd.bankapp.domain;

import com.solvd.bankapp.domain.patterns.TransactionBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ConcreteTransaction extends Transaction {

    private ConcreteTransaction(ConcreteTransactionBuilder builder) {
        super(builder);
    }

    public static class ConcreteTransactionBuilder implements TransactionBuilder<ConcreteTransaction> {
        private int transactionId;
        private BigDecimal amount;
        private boolean transactionStatus;
        private long accountNumber;
        private Timestamp transactionTimestamp;

        @Override
        public TransactionBuilder<ConcreteTransaction> setTransactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        @Override
        public TransactionBuilder<ConcreteTransaction> setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public ConcreteTransactionBuilder setTransactionStatus(boolean transactionStatus) {
            this.transactionStatus = transactionStatus;
            return this;
        }

        @Override
        public TransactionBuilder<ConcreteTransaction> setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        @Override
        public TransactionBuilder<ConcreteTransaction> setTransactionTimestamp(Timestamp transactionTimestamp) {
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
        public ConcreteTransaction build() {
            return new ConcreteTransaction(this);
        }
    }
}
