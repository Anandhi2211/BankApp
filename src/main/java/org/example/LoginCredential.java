package com.solvd.bankapp.domain;

public class LoginCredential {

    // username should be String/varchar not int
    private String username;

    private String password;

    // active status makes sense as boolean instead of String/varchar
    private boolean activeStatus;

    private int pin;

    private long ssn;

    public LoginCredential(String username, String password, boolean activeStatus, int pin, long ssn) {
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                ", password='" + password + '\'' +
                ", activeStatus=" + activeStatus +
                ", pin=" + pin +
                ", ssn=" + ssn +
                '}';
    }
}
