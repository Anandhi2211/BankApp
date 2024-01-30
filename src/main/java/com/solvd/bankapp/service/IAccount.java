package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;

import java.math.BigDecimal;

public interface IAccount {
    void updateAmount(long accountNumber, BigDecimal amount);

    Account getAccount(String userName);

    void deposit(String username ,long accountNumber, BigDecimal amt);

    void withdraw(String username ,long accountNumber, BigDecimal amt);
}
