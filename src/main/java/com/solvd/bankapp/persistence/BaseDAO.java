package com.solvd.bankapp.persistence;

public interface BaseDAO<T> {
    void create(T t);
}