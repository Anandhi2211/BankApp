package com.solvd.bankapp.domain.patterns;

import com.solvd.bankapp.domain.patterns.PaymentObserver;

public interface Subject {
    void addObserver(PaymentObserver observer);
    void removeObserver(PaymentObserver observer);
    void notifyObservers();
}