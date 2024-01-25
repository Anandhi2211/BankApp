package com.solvd.bankapp.domain;

public class Customer {
    private long ssn;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Account account;
    private LoginCredential loginCredential;

    public Customer(long ssn, String firstName, String lastName, String email, String phoneNumber) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

    public Customer() {

    }
    public LoginCredential getLoginCredential() {

        if(this.loginCredential==null){
            this.loginCredential = new LoginCredential();
        }
        return this.loginCredential;
    }

    public Account getAccount() {
        if(this.account == null){
            this.account = new Account();
        }
        return this.account;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setLoginCredential(LoginCredential loginCredential) {
        this.loginCredential = loginCredential;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getSsn() {
        return ssn;
    }

    @Override
    public String toString() {
        return "ICustomer{" +
                "ssn=" + ssn +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static Builder builder(){
        return new Builder(new Customer());
    }

    public static class Builder{

        private  final Customer customer;
        public  Builder(Customer customer) {
            this.customer = customer;
        }

        public Builder Ssn(long ssn) {
            customer.ssn = ssn;
            return this;

        }

        public Builder FirstName(String firstName) {
            customer.firstName = firstName;
            return this;
        }

        public Builder LastName(String lastName) {
            customer.lastName = lastName;
            return this;
        }

        public Builder Email(String email) {
            customer.email = email;
            return this;
        }

        public Builder PhoneNumber(String phoneNumber) {
            customer.phoneNumber = phoneNumber;
            return this;
        }

        public Builder Account(Account account) {
            customer.account = account;
            return this;
        }

        public Builder LoginCredential(LoginCredential loginCredential) {
            customer.loginCredential = loginCredential;
            return this;
        }

        public Customer getCustomer() {
            return customer;
        }
    }
}