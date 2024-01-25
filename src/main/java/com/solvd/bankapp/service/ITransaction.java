package com.solvd.bankapp.service;

import java.math.BigDecimal;

public interface ITransaction {
    void addTransactions(long accountNumber, BigDecimal amount);
}