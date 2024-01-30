package com.solvd.bankapp.domain;

public class LoginCredential {
    private String username;
    private String userPassword;
    private boolean activeStatus;
    private int pin;
    private long ssn;

    private LoginCredential() {
    }

    public String getUsername() {
        return username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public int getPin() {
        return pin;
    }

    public long getSsn() {
        return ssn;
    }

    @Override
    public String toString() {
        return "LoginCredential{" +
                "username='" + username + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", activeStatus=" + activeStatus +
                ", pin=" + pin +
                ", ssn=" + ssn +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final LoginCredential loginCredential;

        private Builder() {
            this.loginCredential = new LoginCredential();
        }

        public Builder setUsername(String username) {
            loginCredential.username = username;
            return this;
        }

        public Builder setUserPassword(String userPassword) {
            loginCredential.userPassword = userPassword;
            return this;
        }

        public Builder setActiveStatus(boolean activeStatus) {
            loginCredential.activeStatus = activeStatus;
            return this;
        }

        public Builder setPin(int pin) {
            loginCredential.pin = pin;
            return this;
        }

        public Builder setSsn(long ssn) {
            loginCredential.ssn = ssn;
            return this;
        }

        public LoginCredential build() {
            if (loginCredential.username == null || loginCredential.userPassword == null) {
                throw new IllegalArgumentException("Username and UserPassword are required.");
            }
            return loginCredential;
        }
    }
}
