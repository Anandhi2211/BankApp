package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.ConcreteTransaction;

import java.util.List;
import java.util.Optional;

public interface BaseListDAO<T> {

    void create(ConcreteTransaction t);

    Optional<T> findById(int transactionId);

    List<T> getAll();
}