package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface AccountDAO extends BaseDAO<Account> {
    @Override
    void create(@Param("account") Account account);

    void delete(Account account);

    long findAccountNumberByUsername(String username);

    BigDecimal displayTotalBalance(long accountNumber);
}
