package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountDAO extends BaseDAO<Account> {
    @Override
    void create(@Param("account") Account account);

    void delete(Account account);

}
