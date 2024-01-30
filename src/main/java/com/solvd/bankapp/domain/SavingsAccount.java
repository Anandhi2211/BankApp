package com.solvd.bankapp.domain;

import java.math.BigDecimal;

public class SavingsAccount {
    private BigDecimal savingsBalance;
    private long accountNumber;
    private InterestRates interestRate;

    private SavingsAccount() {
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

    public InterestRates getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return "SavingsAccount{" +
                "savingsBalance=" + savingsBalance +
                ", accountNumber=" + accountNumber +
                ", interestRate=" + interestRate +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final SavingsAccount savingsAccount;

        private Builder() {
            this.savingsAccount = new SavingsAccount();
            this.savingsAccount.savingsBalance = BigDecimal.ZERO;
        }

        public Builder setSavingsBalance(BigDecimal savingsBalance) {
            savingsAccount.savingsBalance = savingsBalance;
            return this;
        }

        public Builder setAccountNumber(long accountNumber) {
            savingsAccount.accountNumber = accountNumber;
            return this;
        }

        public Builder setInterestRate(InterestRates interestRate) {
            savingsAccount.interestRate = interestRate;
            return this;
        }

        public SavingsAccount build() {
            if (savingsAccount.savingsBalance == null || savingsAccount.accountNumber == 0) {
                throw new IllegalArgumentException("savingsBalance and accountNumber are required.");
            }
            return savingsAccount;
        }
    }
}
