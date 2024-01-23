package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.LoginCredential;
import com.solvd.bankapp.persistence.LoginCredentialDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCredentialDAOImpl implements LoginCredentialDAO {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.LoginCredentialDAOImpl.class);

    @Override
    public void create(LoginCredential credentials) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            LoginCredentialDAO loginCredentialDAO = sqlSession.getMapper(LoginCredentialDAO.class);
            loginCredentialDAO.create(credentials);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating login credentials", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(LoginCredential credentials) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            LoginCredentialDAO loginCredentialDAO = sqlSession.getMapper(LoginCredentialDAO.class);
            loginCredentialDAO.update(credentials);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error updating login credentials", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
