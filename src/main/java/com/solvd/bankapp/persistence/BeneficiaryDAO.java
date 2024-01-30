package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Beneficiary;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BeneficiaryDAO extends BaseDAO<Beneficiary> {
    @Override
    void create(Beneficiary beneficiary);
    Beneficiary findByName(@Param("beneficiaryName") String beneficiaryName);
    ArrayList<Beneficiary> getAll(long accountNumber);
}
