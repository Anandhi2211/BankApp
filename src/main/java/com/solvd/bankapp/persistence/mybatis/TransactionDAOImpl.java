package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.ConnectionPool;
import com.solvd.bankapp.domain.SavingsAccount;
import com.solvd.bankapp.domain.Transaction;
import com.solvd.bankapp.persistence.SavingsAccountDAO;
import com.solvd.bankapp.persistence.TransactionDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.AnnotatedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionDAOImpl implements TransactionDAO {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.TransactionDAOImpl.class);

    @Override
    public void create(Transaction transaction) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            TransactionDAO transactionDAO = sqlSession.getMapper(TransactionDAO.class);
            transactionDAO.create(transaction);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating transaction", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Transaction> findById(int transactionId) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Optional<Transaction> optionalTransaction = Optional.empty();
        try {
            TransactionDAO transactionDAO = sqlSession.getMapper(TransactionDAO.class);
            optionalTransaction = transactionDAO.findById(transactionId);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding transaction by ID", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return optionalTransaction;
    }

    @Override
    public ArrayList<Transaction> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        ArrayList<Transaction> transactions = null;
        try {
            TransactionDAO transactionDAO = sqlSession.getMapper(TransactionDAO.class);
            transactions = transactionDAO.getAll();
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all transactions", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return transactions;
    }

    @Override
    public ArrayList<Transaction> getTransactionHistory(long accountNumber) {
        ArrayList<Transaction> transactions;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("Select * from transactions where account_number = " + accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            transactions = displayTheResults(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return transactions;
    }

    @Override
    public int getTransactionId() {

        int transactionId = 0;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT transaction_id FROM transactions ORDER BY transaction_id DESC LIMIT 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactionId = resultSet.getInt("transaction_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return transactionId;
    }

    private ArrayList<Transaction> displayTheResults(ResultSet resultSet) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Transaction transaction = Transaction.builder()
                        .setTransactionId(resultSet.getInt("transaction_id"))
                        .setAmount(resultSet.getBigDecimal("amount"))
                        .setAccountNumber(resultSet.getLong("account_number"))
                        .setTransactionStatus(resultSet.getBoolean("transaction_status"))
                        .setTransactionTimestamp(Timestamp.valueOf(resultSet.getString("transaction_timestamp")))
                        .build();
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
