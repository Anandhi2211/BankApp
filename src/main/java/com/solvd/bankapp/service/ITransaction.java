package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Transaction;

import java.math.BigDecimal;

public interface ITransaction {
    Transaction addTransactions(long accountNumber, BigDecimal amount);
    void transactionPage(Account account);
}
