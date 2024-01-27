package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountDAO extends BaseDAO<Account> {
    @Override
    void create(@Param("account") Account account);

    void delete(Account account);

//    long findAccountNumberByUsername(String username);
    Account findAccountByUsername(String username);

    void update(@Param("accountNumber") long accountNumber,@Param("amount") BigDecimal amount);

//    BigDecimal displayTotalBalance(long accountNumber);

    long getAccountNumber();

}
