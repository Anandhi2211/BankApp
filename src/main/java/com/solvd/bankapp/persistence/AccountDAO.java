package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Account;

public interface AccountDAO extends BaseDAO<Account> {
    void create(Account account);
}
