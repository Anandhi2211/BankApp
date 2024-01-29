package com.solvd.bankapp.domain;

public class Beneficiary {
    private String beneficiaryName;
    private long beneficiaryAccountNumber;
    private long accountNumber;

    private Beneficiary() {
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public long getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "beneficiaryName='" + beneficiaryName + '\'' +
                ", beneficiaryAccountNumber=" + beneficiaryAccountNumber +
                ", accountNumber=" + accountNumber +
                '}';
    }

    public static Builder builder() {
        return new Builder(new Beneficiary());
    }

    public static class Builder {
        private final Beneficiary beneficiary;

        private Builder(Beneficiary beneficiary) {
            this.beneficiary = beneficiary;
        }

        public Builder setBeneficiaryName(String beneficiaryName) {
            beneficiary.beneficiaryName = beneficiaryName;
            return this;
        }

        public Builder setBeneficiaryAccountNumber(long beneficiaryAccountNumber) {
            beneficiary.beneficiaryAccountNumber = beneficiaryAccountNumber;
            return this;
        }

        public Builder setAccountNumber(long accountNumber) {
            beneficiary.accountNumber = accountNumber;
            return this;
        }

        public Beneficiary build() {
            // Validate that essential fields are set
            if (beneficiary.beneficiaryName == null || beneficiary.beneficiaryAccountNumber == 0 || beneficiary.accountNumber == 0) {
                throw new IllegalStateException("BeneficiaryName, BeneficiaryAccountNumber, and AccountNumber are required.");
            }
            return beneficiary;
        }
    }
}
