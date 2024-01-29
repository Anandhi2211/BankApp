package com.solvd.bankapp.domain;

public interface PaymentObserver {
    void update(Payment payment);
}
