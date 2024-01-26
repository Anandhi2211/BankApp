package com.solvd.bankapp.domain;

public class Beneficiary {
    private String beneficiaryName;
    private long beneficiaryAccountNumber;
    private long accountNumber;

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

    public static BeneficiaryBuilder builder() {
        return new Builder(new Beneficiary());
    }
    public static class Builder {
        private final Beneficiary beneficiary;
        public Builder(Beneficiary beneficiary) {
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

        public Beneficiary getBeneficiary() {
            return beneficiary;
        }
    }
    }
