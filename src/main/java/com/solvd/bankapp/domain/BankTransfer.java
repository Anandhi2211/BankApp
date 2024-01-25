package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BankTransfer {
    private BigDecimal transferAmount;
    private long beneficiaryAccountNumber;
    // create enum class
    private BigDecimal charge;
    private String username;
    private int transactionId;
    private Timestamp transferTimestamp;
    public BankTransfer(BigDecimal transferAmount, long beneficiaryAccountNumber,
                        BigDecimal charge, String username, int transactionId) {
        this.transferAmount = transferAmount;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.charge = charge;
        this.username = username;
        this.transactionId = transactionId;
    }

    public BankTransfer() {

    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransferTimestamp(Timestamp transferTimestamp) {
        this.transferTimestamp = transferTimestamp;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }
    public long getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }
    public void setBeneficiaryAccountNumber(long beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public BigDecimal getCharge() {
        return charge;
    }
    public String getUsername() {
        return username;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public Timestamp getTransferTimestamp() {
        return transferTimestamp;
    }
    @Override
    public String toString() {
        return "BankTransfer{" +
                "transferAmount=" + transferAmount +
                ", beneficiaryAccountNumber=" + beneficiaryAccountNumber +
                ", charge=" + charge +
                ", username='" + username + '\'' +
                ", transactionId=" + transactionId +
                ", transferTimestamp=" + transferTimestamp +
                '}';
    }

    public static Builder builder() {
        return new Builder(new BankTransfer());
    }
    public static class Builder {
          private final BankTransfer bankTransfer;
        public Builder(BankTransfer bankTransfer) {
            this.bankTransfer = bankTransfer;
        }
        public Builder setTransferAmount(BigDecimal transferAmount) {
            bankTransfer.transferAmount = transferAmount;
            return this;
        }

        public Builder setBeneficiaryAccountNumber(long beneficiaryAccountNumber) {
            bankTransfer.beneficiaryAccountNumber = beneficiaryAccountNumber;
            return this;
        }

        public Builder setCharge(BigDecimal charge) {
            bankTransfer.charge = charge;
            return this;
        }

        public Builder setUsername(String username) {
            bankTransfer.username = username;
            return this;
        }

        public Builder setTransactionId(int transactionId) {
            bankTransfer.transactionId = transactionId;
            return this;
        }

        public Builder setTransferTimestamp(Timestamp transferTimestamp) {
            bankTransfer.transferTimestamp = transferTimestamp;
            return this;
        }

        public BankTransfer getBankTransfer() {
            return bankTransfer;
        }
    }
    }
