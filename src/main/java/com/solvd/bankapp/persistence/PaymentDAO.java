package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.ConcreteTransaction;
import com.solvd.bankapp.domain.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentDAO extends BaseListDAO<Payment> {
    @Override
    void create(ConcreteTransaction payment);

    @Override
    Optional<Payment> findById(int transactionId);

    @Override
    List<Payment> getAll();
}
