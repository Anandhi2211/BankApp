package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.Transaction;

import java.math.BigDecimal;
import java.util.Scanner;

public interface ITransaction {
    Transaction addTransactions(long accountNumber, BigDecimal amount);
    void transactionPage(Account account, Scanner in);
}
