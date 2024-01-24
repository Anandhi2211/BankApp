package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.persistence.AccountDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

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

    @Override
    public long findAccountNumberByUsername(String username) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        long accountNumber = 0;
        try {
            AccountDAO accountDAO = sqlSession.getMapper(AccountDAO.class);
            accountDAO.findAccountNumberByUsername(username);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding account number by username", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return accountNumber;
    }

    @Override
    public BigDecimal displayTotalBalance(long accountNumber) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        BigDecimal totalBalance = BigDecimal.ZERO;
        try {
            AccountDAO accountDAO = sqlSession.getMapper(AccountDAO.class);
            accountDAO.displayTotalBalance(accountNumber);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding total balance by account number", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return totalBalance;
    }
}
