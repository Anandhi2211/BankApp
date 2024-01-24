package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.LoginCredential;

import java.util.Optional;

public interface LoginCredentialDAO extends BaseDAO<LoginCredential> {
    @Override
    void create(LoginCredential credentials);

    void update(LoginCredential credentials);

    Optional<LoginCredential> findByUsername(String username);
}
