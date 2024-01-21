package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.DebitCard;

public interface DebitCardDAO extends BaseDAO<DebitCard> {
    @Override
    void create(DebitCard card);

    void delete(DebitCard card);
}
