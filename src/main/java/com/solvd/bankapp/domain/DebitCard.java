package com.solvd.bankapp.domain;

import java.sql.Timestamp;

public class DebitCard {
    private long cardNumber;
    private Timestamp expirationDate;
    private int cvvNumber;
    private String customerFullName;
    private long ssn;
    public DebitCard(long cardNumber, Timestamp expirationDate, int cvvNumber, String customerFullName, long ssn) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.customerFullName = customerFullName;
        this.ssn = ssn;
    }

    public DebitCard() {

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

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
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
                ", customerFullName='" + customerFullName + '\'' +
                ", ssn=" + ssn +
                '}';
    }
}