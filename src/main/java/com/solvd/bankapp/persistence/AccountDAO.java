package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Account;

public interface AccountDAO extends BaseDAO<Account> {
    @Override
    void create(Account account);

    void delete(Account account);
}
