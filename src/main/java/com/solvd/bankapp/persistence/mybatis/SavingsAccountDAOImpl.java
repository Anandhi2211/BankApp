package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.SavingsAccount;
import com.solvd.bankapp.persistence.SavingsAccountDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
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

    @Override
    public void deposit(long accountNumber, BigDecimal amount) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            SavingsAccountDAO savingsAccountDAO = sqlSession.getMapper(SavingsAccountDAO.class);
            SavingsAccount account = savingsAccountDAO.findByNumber(accountNumber).orElse(null);

            if (account != null) {
                BigDecimal newBalance = account.getSavingsBalance().add(amount);
                account.setSavingsBalance(newBalance);
                savingsAccountDAO.update(account);
                sqlSession.commit();
            } else {
                LOGGER.error("Savings account not found for deposit");
            }
        } catch (PersistenceException e) {
            LOGGER.error("Error depositing into savings account", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void withdraw(long accountNumber, BigDecimal amount) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            SavingsAccountDAO savingsAccountDAO = sqlSession.getMapper(SavingsAccountDAO.class);
            SavingsAccount account = savingsAccountDAO.findByNumber(accountNumber).orElse(null);

            if (account != null) {
                BigDecimal currentBalance = account.getSavingsBalance();

                if (currentBalance.compareTo(amount) >= 0) {
                    BigDecimal newBalance = currentBalance.subtract(amount);
                    account.setSavingsBalance(newBalance);
                    savingsAccountDAO.update(account);
                    sqlSession.commit();
                } else {
                    LOGGER.error("Insufficient funds for withdrawal");
                }
            } else {
                LOGGER.error("Savings account not found for withdrawal");
            }
        } catch (PersistenceException e) {
            LOGGER.error("Error withdrawing from savings account", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(SavingsAccount savingsAccount) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            SavingsAccountDAO savingsAccountDAO = sqlSession.getMapper(SavingsAccountDAO.class);
            savingsAccountDAO.update(savingsAccount);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error updating savings account", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}