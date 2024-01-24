package com.solvd.bankapp.domain;

public class Beneficiary {
    private String beneficiaryName;
    private long beneficiaryAccountNumber;
    private long accountNumber;

    public Beneficiary(String beneficiaryName, long beneficiaryAccountNumber, long accountNumber) {
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
        this.accountNumber = accountNumber;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public long getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(long beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "beneficiaryName='" + beneficiaryName + '\'' +
                ", beneficiaryAccountNumber=" + beneficiaryAccountNumber +
                ", accountNumber=" + accountNumber +
                '}';
    }
}