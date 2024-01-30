package com.solvd.bankapp.domain;

import com.solvd.bankapp.domain.patterns.TransactionBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PurchaseProduct extends Transaction {

    private PurchaseProduct(TransactionBuilder<?> builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements TransactionBuilder<PurchaseProduct> {
        private int transactionId;
        private BigDecimal amount;
        private long accountNumber;
        private Timestamp transactionTimestamp;
        private long cardNumber;
        private String purchaseDescription;
        private long ssn;

        private Builder() {
        }

        @Override
        public TransactionBuilder<PurchaseProduct> setTransactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        @Override
        public TransactionBuilder<PurchaseProduct> setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public TransactionBuilder<PurchaseProduct> setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        @Override
        public TransactionBuilder<PurchaseProduct> setTransactionTimestamp(Timestamp transactionTimestamp) {
            this.transactionTimestamp = transactionTimestamp;
            return this;
        }

        public Builder setCardNumber(long cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setPurchaseDescription(String purchaseDescription) {
            this.purchaseDescription = purchaseDescription;
            return this;
        }

        public Builder setSsn(long ssn) {
            this.ssn = ssn;
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
        public long getAccountNumber() {
            return accountNumber;
        }

        @Override
        public Timestamp getTransactionTimestamp() {
            return transactionTimestamp;
        }

        public long getCardNumber() {
            return cardNumber;
        }

        public String getPurchaseDescription() {
            return purchaseDescription;
        }

        public long getSsn() {
            return ssn;
        }

        @Override
        public PurchaseProduct build() {
            if (transactionId == 0 || amount == null || transactionTimestamp == null ||
                    cardNumber == 0 || purchaseDescription == null || ssn == 0) {
                throw new IllegalArgumentException("TransactionId, Amount, TransactionTimestamp, CardNumber, PurchaseDescription, and SSN are required.");
            }
            return new PurchaseProduct(this);
        }
    }
}