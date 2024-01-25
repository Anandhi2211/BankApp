package com.solvd.bankapp.service;

import java.math.BigDecimal;

public interface IAccount {
    long getAccountNumber(String userName);

    BigDecimal getTotaleBalance(long accountNumber);
}
