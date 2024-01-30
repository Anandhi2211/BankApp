package com.solvd.bankapp.domain;

public class Beneficiary {
    private String beneficiaryName;
    private long beneficiaryAccountNumber;
    private long sourceAccountNumber;

    private Beneficiary() {
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public long getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public long getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "beneficiaryName='" + beneficiaryName + '\'' +
                ", beneficiaryAccountNumber=" + beneficiaryAccountNumber +
                ", accountNumber=" + sourceAccountNumber +
                '}';
    }

    public static Builder builder() {
        return new Builder(new Beneficiary());
    }

    public static class Builder {
        private Beneficiary beneficiary;//changed from final

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
            beneficiary.sourceAccountNumber = accountNumber;
            return this;
        }

        public Beneficiary build() {
            if (beneficiary.beneficiaryName == null || beneficiary.beneficiaryAccountNumber == 0 || beneficiary.sourceAccountNumber == 0) {
//            if(this.beneficiary==null){
                    beneficiary = new Beneficiary();
//                throw new IllegalArgumentException("BeneficiaryName, BeneficiaryAccountNumber, and AccountNumber are required.");
            }
            return beneficiary;
        }
    }
}
