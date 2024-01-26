package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;

public interface IBankTransfers {
    void bankTransferPage(long accountNumber);

    void bankTransferPage(Account account);
}
