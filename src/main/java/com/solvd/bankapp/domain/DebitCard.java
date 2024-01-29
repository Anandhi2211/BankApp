package com.solvd.bankapp.domain;

import java.sql.Timestamp;

public class DebitCard {
    private long cardNumber;
    private Timestamp expirationDate;
    private int cvvNumber;
    private String customerFullName;
    private long ssn;

    private DebitCard() {
    }

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

    public static DebitCardBuilder builder() {
        return new DebitCardBuilder(new DebitCard());
    }

    public static class DebitCardBuilder {
        private final DebitCard debitCard;

        private DebitCardBuilder(DebitCard debitCard) {
            this.debitCard = debitCard;
        }

        public DebitCardBuilder setCardNumber(long cardNumber) {
            debitCard.cardNumber = cardNumber;
            return this;
        }

        public DebitCardBuilder setExpirationDate(Timestamp expirationDate) {
            debitCard.expirationDate = expirationDate;
            return this;
        }

        public DebitCardBuilder setCvvNumber(int cvvNumber) {
            debitCard.cvvNumber = cvvNumber;
            return this;
        }

        public DebitCardBuilder setCustomerFullName(String customerFullName) {
            debitCard.customerFullName = customerFullName;
            return this;
        }

        public DebitCardBuilder setSsn(long ssn) {
            debitCard.ssn = ssn;
            return this;
        }

        public DebitCard build() {
            if (debitCard.cardNumber == 0 || debitCard.expirationDate == null || debitCard.cvvNumber == 0 ||
                    debitCard.customerFullName == null || debitCard.ssn == 0) {
                throw new IllegalStateException("CardNumber, ExpirationDate, CvvNumber, CustomerFullName, and Ssn are required.");
            }
            return debitCard;
        }
    }
}
