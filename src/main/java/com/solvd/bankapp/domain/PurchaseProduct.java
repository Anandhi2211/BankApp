package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PurchaseProduct {

    private long cardNumber;

    private String purchaseDescription;

    private BigDecimal amount;

    private int transactionId;

    private long ssn;

    private Timestamp purchaseTimestamp;

    public PurchaseProduct(long cardNumber, String purchaseDescription, BigDecimal amount,
                           int transactionId, long ssn, Timestamp purchaseTimestamp) {
        this.cardNumber = cardNumber;
        this.purchaseDescription = purchaseDescription;
        this.amount = amount;
        this.transactionId = transactionId;
        this.ssn = ssn;
        this.purchaseTimestamp = purchaseTimestamp;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPurchaseDescription() {
        return purchaseDescription;
    }

    public void setPurchaseDescription(String purchaseDescription) {
        this.purchaseDescription = purchaseDescription;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public Timestamp getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    public void setPurchaseTimestamp(Timestamp purchaseTimestamp) {
        this.purchaseTimestamp = purchaseTimestamp;
    }

    @Override
    public String toString() {
        return "PurchaseProduct{" +
                "cardNumber=" + cardNumber +
                ", purchaseDescription='" + purchaseDescription + '\'' +
                ", amount=" + amount +
                ", transactionId=" + transactionId +
                ", ssn=" + ssn +
                ", purchaseTimestamp=" + purchaseTimestamp +
                '}';
    }
}