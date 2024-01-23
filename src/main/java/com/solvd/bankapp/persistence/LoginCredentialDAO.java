package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.LoginCredential;

public interface LoginCredentialDAO extends BaseDAO<LoginCredential> {
    @Override
    void create(LoginCredential credentials);

    void update(LoginCredential credentials);
}
