package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.SavingsAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SavingsAccountDAO extends BaseDAO<SavingsAccount> {
    @Override
    void create(@Param("savingsAccount") SavingsAccount savingsAccount);
    SavingsAccount findSavingByNumber(@Param("accountNumber") long accountNumber);
    List<SavingsAccount> getAll();//we can remove
    void delete(long accountNumber);
    void update(@Param("accountNumber") long accountNumber, @Param("amount") BigDecimal amount);


}
