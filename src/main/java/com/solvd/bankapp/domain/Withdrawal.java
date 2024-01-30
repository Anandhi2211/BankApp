package com.solvd.bankapp.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Withdrawal {
    private BigDecimal withdrawalAmount;
    private long accountNumber;
    private String username;
    private int transactionId;
    private Timestamp withdrawalTimestamp;

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getUsername() {
        return username;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Timestamp getWithdrawalTimestamp() {
        return withdrawalTimestamp;
    }


    @Override
    public String toString() {
        return "Withdrawal{" +
                "withdrawalAmount=" + withdrawalAmount +
                ", accountNumber=" + accountNumber +
                ", username='" + username + '\'' +
                ", transactionId=" + transactionId +
                ", withdrawalTimestamp=" + withdrawalTimestamp +
                '}';
    }

    public static Builder builder() {
        return new Builder(new Withdrawal());
    }

    public static class Builder {
        private final Withdrawal withdrawal;

        public Builder(Withdrawal withdrawal) {
            this.withdrawal = withdrawal;
        }

        public Builder setWithdrawalAmount(BigDecimal withdrawalAmount) {
            withdrawal.withdrawalAmount = withdrawalAmount;
            return this;
        }

        public Builder setAccountNumber(long accountNumber) {
            withdrawal.accountNumber = accountNumber;
            return this;
        }

        public Builder setUsername(String username) {
            withdrawal.username = username;
            return this;
        }

        public Builder setTransactionId(int transactionId) {
            withdrawal.transactionId = transactionId;
            return this;
        }

        public Builder setWithdrawalTimestamp(Timestamp withdrawalTimestamp) {
            withdrawal.withdrawalTimestamp = withdrawalTimestamp;
            return this;
        }

        public Withdrawal getWithdrawal() {
            return withdrawal;
        }
    }
}