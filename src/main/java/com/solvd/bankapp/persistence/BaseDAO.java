package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.DebitCard;

public interface BaseDAO<T> {
    void create(T t);

}