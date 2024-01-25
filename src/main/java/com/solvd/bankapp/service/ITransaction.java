package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;

import java.math.BigDecimal;

public interface ITransaction {
    void addTransactions(long accountNumber, BigDecimal amount);

    void transactionPage(Account account);
}
