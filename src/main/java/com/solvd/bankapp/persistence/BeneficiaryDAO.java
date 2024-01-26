package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Beneficiary;
import java.util.List;
import java.util.Optional;

public interface BeneficiaryDAO extends BaseDAO<Beneficiary> {
    @Override
    void create(Beneficiary beneficiary);

    Optional<Beneficiary> findByName(String beneficiaryName);

    List<Beneficiary> getAll(); //done
}
