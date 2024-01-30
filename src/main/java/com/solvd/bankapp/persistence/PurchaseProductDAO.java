package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.ConcreteTransaction;
import com.solvd.bankapp.domain.PurchaseProduct;
import java.util.List;
import java.util.Optional;

public interface
PurchaseProductDAO extends BaseListDAO<PurchaseProduct> {
    @Override
    void create(ConcreteTransaction purchase);

    @Override
    Optional<PurchaseProduct> findById(int transactionId);

    @Override
    List<PurchaseProduct> getAll();
}
