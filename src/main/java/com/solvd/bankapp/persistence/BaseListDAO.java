package com.solvd.bankapp.persistence;

import java.util.List;
import java.util.Optional;

public interface BaseListDAO<T> {
    void create(T t);
    Optional<T> findById(int transactionId);
    List<T> getAll();
}