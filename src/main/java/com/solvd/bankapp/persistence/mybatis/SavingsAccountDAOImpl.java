package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.SavingsAccount;
import com.solvd.bankapp.persistence.SavingsAccountDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class SavingsAccountDAOImpl implements SavingsAccountDAO {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.SavingsAccountDAOImpl.class);

    @Override
    public void create(SavingsAccount savingsAccount) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            SavingsAccountDAO savingsAccountDAO = sqlSession.getMapper(SavingsAccountDAO.class);
            savingsAccountDAO.create(savingsAccount);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating savings account", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<SavingsAccount> findByNumber(long accountNumber) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Optional<SavingsAccount> optionalSavingsAccount = Optional.empty();
        try {
            SavingsAccountDAO savingsAccountDAO= sqlSession.getMapper(SavingsAccountDAO.class);
            optionalSavingsAccount = savingsAccountDAO.findByNumber(accountNumber);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding savings account by account number", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return optionalSavingsAccount;
    }

    @Override
    public List<SavingsAccount> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        List<SavingsAccount> savingsAccounts = null;
        try {
            savingsAccounts = sqlSession.selectList("com.solvd.bankapp.persistence.SavingsAccountDAO.getAll");
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all savings accounts", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return savingsAccounts;
    }

    @Override
    public void delete(SavingsAccount savingsAccount) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            SavingsAccountDAO savingsAccountDAO = sqlSession.getMapper(SavingsAccountDAO.class);
            savingsAccountDAO.delete(savingsAccount);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error deleting savings account", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
