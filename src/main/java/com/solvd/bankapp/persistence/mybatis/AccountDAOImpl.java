package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.ConnectionPool;
import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.persistence.AccountDAO;
import com.solvd.bankapp.persistence.SavingsAccountDAO;
import com.solvd.bankapp.persistence.TransactionDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountDAOImpl implements AccountDAO {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
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

//    @Override
//    public long findAccountNumberByUsername(String username) {
//        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
//        long accountNumber = 0;
//        try {
//            AccountDAO accountDAO = sqlSession.getMapper(AccountDAO.class);
//            accountNumber = accountDAO.findAccountNumberByUsername(username);
//            sqlSession.commit();
//        } catch (PersistenceException e) {
//            LOGGER.error("Error finding account number by username", e);
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//        return accountNumber;
//    }

    @Override
    public Account findAccountByUsername(String username) {

        try (SqlSession sqlSession = Config.getSessionFactory().openSession(true)) {
            AccountDAO accountDAO = sqlSession.getMapper(AccountDAO.class);
            return accountDAO.findAccountByUsername(username);
        }    }

    @Override
    public void update(long accountNumber, BigDecimal amount) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            AccountDAO accountDAO = sqlSession.getMapper(AccountDAO.class);
            accountDAO.update(accountNumber,amount);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error Updating amount in the account", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public long getAccountNumber() {
        long accountNumber = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT transaction_id FROM transactions ORDER BY transaction_id DESC LIMIT 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accountNumber = resultSet.getLong("account_number");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return accountNumber;
    }

//    @Override
//    public BigDecimal displayTotalBalance(long accountNumber) {
//        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
//        BigDecimal totalBalance = null;
//        try {
//            AccountDAO accountDAO = sqlSession.getMapper(AccountDAO.class);
//            totalBalance = accountDAO.displayTotalBalance(accountNumber);
//            sqlSession.commit();
//        } catch (PersistenceException e) {
//            LOGGER.error("Error finding total balance by account number", e);
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//        return totalBalance;
//    }
}
