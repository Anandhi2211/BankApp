package com.solvd.bankapp.domain;

import java.sql.Timestamp;

public class DebitCard {
    private long cardNumber;
    private Timestamp expirationDate;
    private int cvvNumber;
    private String customerFullName;
    private long ssn;


    public long getCardNumber() {
        return cardNumber;
    }
    public Timestamp getExpirationDate() {
        return expirationDate;
    }
    public int getCvvNumber() {
        return cvvNumber;
    }
    public String getCustomerFullName() {
        return customerFullName;
    }
    public long getSsn() {
        return ssn;
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

    public  static Builder builder(){
        return  new Builder(new DebitCard());
    }

    public static class Builder{
        private final DebitCard debitCard;

        public Builder(DebitCard debitCard) {
            this.debitCard = debitCard;
        }

        public Builder setCardNumber(long cardNumber) {
            debitCard.cardNumber = cardNumber;
            return this;
        }

        public Builder setExpirationDate(Timestamp expirationDate) {
            debitCard.expirationDate = expirationDate;
            return this;
        }

        public Builder setCvvNumber(int cvvNumber) {
            debitCard.cvvNumber = cvvNumber;
            return this;
        }

        public Builder setCustomerFullName(String customerFullName) {
            debitCard.customerFullName = customerFullName;
            return this;
        }
        public Builder setSsn(long ssn) {
            debitCard.ssn = ssn;
            return this;
        }

        public com.solvd.bankapp.domain.DebitCard getDebitCard() {
            return debitCard;
        }
    }
}