package com.solvd.bankapp.domain;

public class Customer {
    private long ssn;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Account account;
    private LoginCredential loginCredential;

    private Customer() {
    }

    public long getSsn() {
        return ssn;
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

    public Account getAccount() {
        if (account == null) {
            throw new IllegalStateException("No account exists for this customer. Please create an account.");
        }
        return account;
    }

    public LoginCredential getLoginCredential() {
        if (loginCredential == null) {
            throw new IllegalStateException("Login credentials not initialized. Please set login credentials.");
        }
        return loginCredential;
    }

    public void setLoginCredential(LoginCredential loginCredential) {
        this.loginCredential = loginCredential;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Customer customer;

        private Builder() {
            this.customer = new Customer();
        }

        public Builder ssn(long ssn) {
            customer.ssn = ssn;
            return this;
        }

        public Builder firstName(String firstName) {
            customer.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            customer.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            customer.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            customer.phoneNumber = phoneNumber;
            return this;
        }

        public Builder account(Account account) {
            customer.account = account;
            return this;
        }

        public Builder loginCredential(LoginCredential loginCredential) {
            customer.loginCredential = loginCredential;
            return this;
        }

        public Customer build() {
            if (customer.ssn == 0 || customer.firstName == null || customer.lastName == null ||
                    customer.email == null || customer.phoneNumber == null) {
                throw new IllegalArgumentException("SSN, FirstName, LastName, Email, and PhoneNumber are required.");
            }
            return customer;
        }
    }
}