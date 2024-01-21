package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class Payment {

    // changed this field name to be a more generic placeholder for any bill recipient
    private int companyName;

    private BigDecimal billAmount;

    private String username;

    private int transactionId;

    private long ssn;

    public Payment(int companyName, BigDecimal billAmount, String username, int transactionId, long ssn) {
        this.companyName = companyName;
        this.billAmount = billAmount;
        this.username = username;
        this.transactionId = transactionId;
        this.ssn = ssn;
    }

    public int getCompanyName() {
        return companyName;
    }

    public void setCompanyName(int companyName) {
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

    @Override
    public String toString() {
        return "Payment{" +
                "companyName=" + companyName +
                ", billAmount=" + billAmount +
                ", username='" + username + '\'' +
                ", transactionId=" + transactionId +
                ", ssn=" + ssn +
                '}';
    }
}