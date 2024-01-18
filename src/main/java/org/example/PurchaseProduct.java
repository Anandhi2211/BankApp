package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class PurchaseProduct {

    private long cardNumber;

    private String purchaseDescription;

    private BigDecimal amount;

    private int transactionId;

    private long ssn;

    public PurchaseProduct(long cardNumber, String purchaseDescription, BigDecimal amount, int transactionId, long ssn) {
        this.cardNumber = cardNumber;
        this.purchaseDescription = purchaseDescription;
        this.amount = amount;
        this.transactionId = transactionId;
        this.ssn = ssn;
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

    @Override
    public String toString() {
        return "PurchaseProduct{" +
                "cardNumber=" + cardNumber +
                ", purchaseDescription='" + purchaseDescription + '\'' +
                ", amount=" + amount +
                ", transactionId=" + transactionId +
                ", ssn=" + ssn +
                '}';
    }
}
