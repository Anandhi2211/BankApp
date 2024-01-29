package com.solvd.bankapp.service;

import com.solvd.bankapp.domain.Account;

import java.util.Scanner;

public interface IBankTransfers {
    void bankTransferPage(Account account, Scanner in);
}
