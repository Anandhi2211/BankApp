package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.persistence.AccountDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountDAOImpl implements AccountDAO {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.AccountDAOImpl.class);

    @Override
    public void create(Account account) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            AccountDAO admissionDAO = sqlSession.getMapper(AccountDAO.class);
            admissionDAO.create(account);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating account", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delete(Account account) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            AccountDAO admissionDAO = sqlSession.getMapper(AccountDAO.class);
            admissionDAO.delete(account);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error deleting account", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
