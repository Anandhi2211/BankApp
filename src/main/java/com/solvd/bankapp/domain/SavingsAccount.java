package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class SavingsAccount {
    private BigDecimal savingsBalance;
    private long accountNumber;
    private int interestRate;
    public SavingsAccount(BigDecimal savingsBalance, long accountNumber, int interestRate) {
        this.savingsBalance = savingsBalance;
        this.accountNumber = accountNumber;
        this.interestRate = interestRate;
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

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
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