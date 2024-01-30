package com.solvd.bankapp.domain.patterns;

import com.solvd.bankapp.domain.Account;

public interface PaymentObserver {
    void update(Account account);
}
