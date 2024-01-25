package com.solvd.bankapp.domain;

public class LoginCredential {
    private String username;
    private String userPassword;
    private boolean activeStatus;
    private int pin;
    private long ssn;

    public LoginCredential() {
    }

    public LoginCredential(String username, String userPassword, boolean activeStatus, int pin, long ssn) {
        this.username = username;
        this.userPassword = userPassword;
        this.activeStatus = activeStatus;
        this.pin = pin;
        this.ssn = ssn;
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
                ", password='" + userPassword + '\'' +
                ", activeStatus=" + activeStatus +
                ", pin=" + pin +
                ", ssn=" + ssn +
                '}';
    }
    public static  Builder builder(){
        return new Builder (new LoginCredential());
    }

    public static class Builder{
        private final LoginCredential loginCredential;

        public Builder(com.solvd.bankapp.domain.LoginCredential loginCredential) {
            this.loginCredential = loginCredential;
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

        public com.solvd.bankapp.domain.LoginCredential getLoginCredential() {
            return loginCredential;
        }
    }

}