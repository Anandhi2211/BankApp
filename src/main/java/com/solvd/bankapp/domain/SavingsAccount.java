package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class SavingsAccount {
    private BigDecimal savingsBalance;
    private long accountNumber;
    private double interestRate;
    public SavingsAccount(BigDecimal savingsBalance, long accountNumber, double interestRate) {
        this.savingsBalance = savingsBalance;
        this.accountNumber = accountNumber;
        this.interestRate = interestRate;
    }

    public SavingsAccount() {

    }

    public BigDecimal getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(BigDecimal savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "savingsBalance=" + savingsBalance +
                ", accountNumber=" + accountNumber +
                ", interestRate=" + interestRate +
                '}';
    }
}