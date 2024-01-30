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

    public long getCardNumber() {
        return cardNumber;
    }
    public String getPurchaseDescription() {
        return purchaseDescription;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public long getSsn() {
        return ssn;
    }
    public Timestamp getPurchaseTimestamp() {
        return purchaseTimestamp;
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

    public static  Builder builder(){
        return new Builder (new PurchaseProduct());
    }

    public static class Builder{
        private final PurchaseProduct purchaseProduct;

        public Builder(PurchaseProduct purchaseProduct) {
            this.purchaseProduct = purchaseProduct;
        }

        public Builder setCardNumber(long cardNumber) {
            purchaseProduct.cardNumber = cardNumber;
            return this;
        }

        public Builder setPurchaseDescription(String purchaseDescription) {
            purchaseProduct.purchaseDescription = purchaseDescription;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            purchaseProduct.amount = amount;
            return this;
        }

        public Builder setTransactionId(int transactionId) {
            purchaseProduct.transactionId = transactionId;
            return this;
        }

        public Builder setSsn(long ssn) {
            purchaseProduct.ssn = ssn;
            return this;
        }

        public Builder setPurchaseTimestamp(Timestamp purchaseTimestamp) {
            purchaseProduct.purchaseTimestamp = purchaseTimestamp;
            return this;
        }

        public PurchaseProduct getPurchaseProduct() {
            return purchaseProduct;
        }
    }
}