package com.solvd.bankapp.domain;

import com.solvd.bankapp.domain.patterns.TransactionBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment extends Transaction {

    private Payment(TransactionBuilder<?> builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements TransactionBuilder<Payment> {
        private int transactionId;
        private BigDecimal amount;
        private boolean transactionStatus;
        private long accountNumber;
        private Timestamp transactionTimestamp;
        private int companyAccountNumber;
        private String companyName;
        private long ssn;

        private Builder() {
        }

        @Override
        public TransactionBuilder<Payment> setTransactionId(int transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        @Override
        public TransactionBuilder<Payment> setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        @Override
        public TransactionBuilder<Payment> setTransactionStatus(boolean transactionStatus) {
            this.transactionStatus = transactionStatus;
            return this;
        }

        @Override
        public TransactionBuilder<Payment> setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        @Override
        public TransactionBuilder<Payment> setTransactionTimestamp(Timestamp transactionTimestamp) {
            this.transactionTimestamp = transactionTimestamp;
            return this;
        }

        public Builder setCompanyAccountNumber(int companyAccountNumber) {
            this.companyAccountNumber = companyAccountNumber;
            return this;
        }

        public Builder setCompanyName(String companyName) {
            this.companyName = companyName;
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

        public int getCompanyAccountNumber() {
            return companyAccountNumber;
        }

        public String getCompanyName() {
            return companyName;
        }

        public long getSsn() {
            return ssn;
        }

        @Override
        public Payment build() {
            if (transactionId == 0 || amount == null || transactionTimestamp == null ||
                    companyAccountNumber == 0 || companyName == null || ssn == 0) {
                throw new IllegalArgumentException("TransactionId, Amount, TransactionTimestamp, CompanyAccountNumber, CompanyName, and SSN are required.");
            }
            return new Payment(this);
        }
    }
}