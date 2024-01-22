package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class BankTransfer {

    private BigDecimal transferAmount;

    private long beneficiaryAccountNumber;

    // what exactly is charge? is it a service fee?
    private BigDecimal charge;

    private String username;

    private int transactionId;

    public BankTransfer(BigDecimal transferAmount, long beneficiaryAccountNumber, BigDecimal charge, String username, int transactionId) {
        this.transferAmount = transferAmount;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.charge = charge;
        this.username = username;
        this.transactionId = transactionId;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
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

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
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

    @Override
    public String toString() {
        return "BankTransfer{" +
                "transferAmount=" + transferAmount +
                ", beneficiaryAccountNumber=" + beneficiaryAccountNumber +
                ", charge=" + charge +
                ", userName='" + username + '\'' +
                ", transactionId=" + transactionId +
                '}';
    }
}