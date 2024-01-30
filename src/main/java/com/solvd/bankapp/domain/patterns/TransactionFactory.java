package com.solvd.bankapp.domain.patterns;

import com.solvd.bankapp.domain.Transaction;

public interface TransactionFactory {
    Transaction createTransaction();
}
