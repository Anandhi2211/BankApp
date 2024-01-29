package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BankTransfer {
    private long transferId;
    private Account sourceAccount;
    private Account destinationAccount;
    private BigDecimal amount;
    private BigDecimal transferCharge;
    private Timestamp transferTimestamp;

    private BankTransfer() {
    }

    public long getTransferId() {
        return transferId;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getTransferCharge() {
        return transferCharge;
    }

    public Timestamp getTransferTimestamp() {
        return transferTimestamp;
    }

    private void calculateSurcharges() {
        if (amount != null) {
            TransferCharges charge;
            if (amount.compareTo(BigDecimal.valueOf(500)) <= 0) {
                charge = TransferCharges.UNDER_500;
            } else if (amount.compareTo(BigDecimal.valueOf(1000)) <= 0) {
                charge = TransferCharges.BETWEEN_500_AND_1000;
            } else {
                charge = TransferCharges.OVER_1000;
            }

            transferCharge = amount.multiply(BigDecimal.valueOf(charge.getRate()));
        }
    }

    @Override
    public String toString() {
        return "BankTransfer{" +
                "transferId=" + transferId +
                ", sourceAccount=" + sourceAccount +
                ", destinationAccount=" + destinationAccount +
                ", amount=" + amount +
                ", transactionCharge=" + transferCharge +
                ", transferDate=" + transferTimestamp +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final BankTransfer bankTransfer;

        private Builder() {
            this.bankTransfer = new BankTransfer();
        }

        public Builder transferId(long transferId) {
            bankTransfer.transferId = transferId;
            return this;
        }

        public Builder sourceAccount(Account sourceAccount) {
            bankTransfer.sourceAccount = sourceAccount;
            return this;
        }

        public Builder destinationAccount(Account destinationAccount) {
            bankTransfer.destinationAccount = destinationAccount;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            bankTransfer.amount = amount;
            return this;
        }

        public Builder transactionCharge(BigDecimal transactionCharge) {
            bankTransfer.transferCharge = transactionCharge;
            return this;
        }

        public Builder transferDate(Timestamp transferDate) {
            bankTransfer.transferTimestamp = transferDate;
            return this;
        }

        public BankTransfer build() {
            if (bankTransfer.transferId == 0 || bankTransfer.sourceAccount == null ||
                    bankTransfer.destinationAccount == null || bankTransfer.amount == null ||
                    bankTransfer.transferTimestamp == null) {
                throw new IllegalArgumentException("TransferId, SourceAccount, DestinationAccount, Amount, and TransferDate are required.");
            }
            bankTransfer.calculateSurcharges();

            return bankTransfer;
        }
    }
}
