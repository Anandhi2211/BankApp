package com.solvd.bankapp.domain;

import java.sql.Timestamp;

public class DebitCard {

    private long cardNumber;

    private Timestamp expirationDate;

    private int cvvNumber;

    // should we replace this field with customer name or delete it?
    private int customerId;

    private long ssn;

    public DebitCard(long cardNumber, Timestamp expirationDate, int cvvNumber, int customerId, long ssn) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.customerId = customerId;
        this.ssn = ssn;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "cardNumber=" + cardNumber +
                ", expirationDate=" + expirationDate +
                ", cvvNumber=" + cvvNumber +
                ", customerId=" + customerId +
                ", ssn=" + ssn +
                '}';
    }
}