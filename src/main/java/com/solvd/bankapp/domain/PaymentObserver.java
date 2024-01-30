package com.solvd.bankapp.domain;

public interface PaymentObserver {
    void update(Account account);
}
