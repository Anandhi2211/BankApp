package com.solvd.bankapp.domain;

public class LoginCredential {

    private String username;

    private String userPassword;

    private boolean activeStatus;

    private int pin;

    private long ssn;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public long getSsn() {
        return ssn;
    }

    public void setSsn(long ssn) {
        this.ssn = ssn;
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
}