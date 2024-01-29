package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.Transaction;
import com.solvd.bankapp.persistence.TransactionDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TransactionDAOImpl implements TransactionDAO {

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
    public List<Transaction> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        List<Transaction> transactions = null;
        try {
            transactions = sqlSession.selectList("com.solvd.bankapp.persistence.TransactionDAO.getAll");
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all transactions", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return transactions;
    }
}
