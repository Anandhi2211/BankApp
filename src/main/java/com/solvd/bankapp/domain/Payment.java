package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payment {
    private int companyAccountNumber;
    private String companyName;
    private BigDecimal billAmount;
    private String username;
    private int transactionId;
    private long ssn;
    private Timestamp paymentTimestamp;

    public int getCompanyAccountNumber() {
        return companyAccountNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public String getUsername() {
        return username;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public long getSsn() {
        return ssn;
    }

    public Timestamp getPaymentTimestamp() {
        return paymentTimestamp;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "companyAccountNumber=" + companyAccountNumber +
                ", companyName='" + companyName + '\'' +
                ", billAmount=" + billAmount +
                ", username='" + username + '\'' +
                ", transactionId=" + transactionId +
                ", ssn=" + ssn +
                ", paymentTimestamp=" + paymentTimestamp +
                '}';
    }

    public static  Builder builder(){
        return new Builder (new Payment());
    }

    public static class Builder{
        private final Payment payment;

        public Builder(Payment payment) {
            this.payment = payment;
        }

        public Builder setCompanyAccountNumber(int companyAccountNumber) {
            payment.companyAccountNumber = companyAccountNumber;
            return this;
        }

        public Builder setCompanyName(String companyName) {
            payment.companyName = companyName;
            return this;
        }

        public Builder setBillAmount(BigDecimal billAmount) {
            payment.billAmount = billAmount;
            return this;
        }

        public Builder setUsername(String username) {
            payment.username = username;
            return this;
        }

        public Builder setTransactionId(int transactionId) {
            payment.transactionId = transactionId;
            return this;
        }

        public Builder setSsn(long ssn) {
            payment.ssn = ssn;
            return this;
        }

        public Builder setPaymentTimestamp(Timestamp paymentTimestamp) {
            payment.paymentTimestamp = paymentTimestamp;
            return this;
        }

        public com.solvd.bankapp.domain.Payment build() {
            return payment;
        }
    }
}