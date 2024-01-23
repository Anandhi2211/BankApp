package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.BankTransfer;
import java.util.List;
import java.util.Optional;

public interface BankTransferDAO extends BaseListDAO<BankTransfer> {
    @Override
    void create(BankTransfer transfer);

    @Override
    Optional<BankTransfer> findById(int transactionId);

    @Override
    List<BankTransfer> getAll();
}