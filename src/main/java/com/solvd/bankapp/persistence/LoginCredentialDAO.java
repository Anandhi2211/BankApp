package com.solvd.bankapp.persistence;

import com.solvd.bankapp.domain.LoginCredential;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface LoginCredentialDAO extends BaseDAO<LoginCredential> {
    @Override
    void create(@Param("credentials") LoginCredential credentials);
    void findByUserName(String username);
    void update(LoginCredential credentials);

    LoginCredential findByUsername(String username);
}
