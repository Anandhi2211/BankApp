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

    public SavingsAccount() {

    }

    public BigDecimal getSavingsBalance() {
        return savingsBalance;
    }
    public long getAccountNumber() {
        return accountNumber;
    }
    public int getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "savingsBalance=" + savingsBalance +
                ", accountNumber=" + accountNumber +
                ", interestRate=" + interestRate +
                '}';
    }

    public static  Builder builder(){
        return new Builder (new SavingsAccount());
    }

    public static class Builder{
        private final SavingsAccount savingsAccount;

        public Builder(com.solvd.bankapp.domain.SavingsAccount savingsAccount) {
            this.savingsAccount = savingsAccount;
        }

        public Builder setSavingsBalance(BigDecimal savingsBalance) {
            savingsAccount.savingsBalance = savingsBalance;
            return this;
        }

        public Builder setAccountNumber(long accountNumber) {
            savingsAccount.accountNumber = accountNumber;
            return this;
        }

        public Builder setInterestRate(int interestRate) {
            savingsAccount.interestRate = interestRate;
            return this;
        }

        public com.solvd.bankapp.domain.SavingsAccount getSavingsAccount() {
            return savingsAccount;
        }
    }
}