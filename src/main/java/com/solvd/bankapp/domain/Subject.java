package com.solvd.bankapp.domain;

public interface Subject {
    void addObserver(PaymentObserver observer);
    void removeObserver(PaymentObserver observer);
    void notifyObservers();
}