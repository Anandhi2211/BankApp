package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Deposit;
import java.util.List;
import java.util.Optional;

public interface DepositDAO extends BaseListDAO<Deposit> {
    @Override
    void create(Deposit deposit);

    @Override
    Optional<Deposit> findById(int transactionId);

    @Override
    List<Deposit> getAll();
}
