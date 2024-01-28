package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.DebitCard;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface DebitCardDAO extends BaseDAO<DebitCard> {
    @Override
    void create(@Param("card") DebitCard card);

    void delete(Long debitCardNumber);

    DebitCard findByCardNumber(long debitCardNumber);

    DebitCard findBySsn(long ssn);

    ArrayList<Account> findAccountBySsn(long ssn);
}
