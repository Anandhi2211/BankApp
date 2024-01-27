package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.SavingsAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SavingsAccountDAO extends BaseDAO<SavingsAccount> {
    @Override
    void create(SavingsAccount savingsAccount);

    Optional<SavingsAccount> findByNumber(long accountNumber);

    List<SavingsAccount> getAll();

    void delete(SavingsAccount savingsAccount);

    void deposit(long accountNumber, BigDecimal amount);

    void withdraw(long accountNumber, BigDecimal amount);

    void update(SavingsAccount savingsAccount);
}
