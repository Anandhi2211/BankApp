package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Withdrawal;
import java.util.List;
import java.util.Optional;

public interface WithdrawalDAO extends BaseListDAO<Withdrawal> {
    @Override
    void create(Withdrawal withdrawal);
    @Override
    Optional<Withdrawal> findById(int transactionId);
    @Override
    List<Withdrawal> getAll();
}
