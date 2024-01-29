package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentDAO extends BaseListDAO<Payment> {
    @Override
    void create(Payment payment);

    Payment findPaymentById(long billAccountNumber);

    @Override
    Optional<Payment> findById(int transactionId);

    @Override
    List<Payment> getAll();
}
