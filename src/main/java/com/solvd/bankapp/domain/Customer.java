package com.solvd.bankapp.domain;

public class Customer {
    private long ssn;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Account account;
    private LoginCredential loginCredential;
    public Customer() {

    }
    public LoginCredential getLoginCredential() {

        if(this.loginCredential==null){
            this.loginCredential = new LoginCredential();
        }
        return this.loginCredential;
    }

    public void setLoginCredential(LoginCredential loginCredential) {
        this.loginCredential = loginCredential;
    }


    public Account getAccount() {
        if(this.account == null){
            this.account = new Account();
        }
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public Customer(long ssn, String firstName, String lastName, String email, String phoneNumber) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ssn=" + ssn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}