package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.PurchaseProduct;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface
PurchaseProductDAO extends BaseListDAO<PurchaseProduct> {
    @Override
    void create(@Param("purchase") PurchaseProduct purchase);

    @Override
    Optional<PurchaseProduct> findById(int transactionId);

    @Override
    ArrayList<PurchaseProduct> getAll();
}
