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

    public Payment(int companyAccountNumber, String companyName, BigDecimal billAmount,
                   String username, int transactionId, long ssn) {
        this.companyAccountNumber = companyAccountNumber;
        this.companyName = companyName;
        this.billAmount = billAmount;
        this.username = username;
        this.transactionId = transactionId;
        this.ssn = ssn;;
    }

    public int getCompanyAccountNumber() {
        return companyAccountNumber;
    }

    public void setCompanyAccountNumber(int companyAccountNumber) {
        this.companyAccountNumber = companyAccountNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public Timestamp getPaymentTimestamp() {
        return paymentTimestamp;
    }

    public void setPaymentTimestamp(Timestamp paymentTimestamp) {
        this.paymentTimestamp = paymentTimestamp;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "companyAccountNumber" + companyAccountNumber +
                "companyName='" + companyName + '\'' +
                ", billAmount=" + billAmount +
                ", username='" + username + '\'' +
                ", transactionId=" + transactionId +
                ", ssn=" + ssn +
                ", paymentTimestamp=" + paymentTimestamp +
                '}';
    }
}