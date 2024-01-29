package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.Deposit;
import com.solvd.bankapp.persistence.DepositDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class DepositDAOImpl implements DepositDAO {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.DepositDAOImpl.class);

    @Override
    public void create(Deposit deposit) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            DepositDAO depositDAO = sqlSession.getMapper(DepositDAO.class);
            depositDAO.create(deposit);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating deposit", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Deposit> findById(int transactionId) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Optional<Deposit> optionalDeposit = Optional.empty();
        try {
            DepositDAO depositDAO = sqlSession.getMapper(DepositDAO.class);
            optionalDeposit = depositDAO.findById(transactionId);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding deposit by transaction ID", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return optionalDeposit;
    }

    @Override
    public List<Deposit> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        List<Deposit> deposits = null;
        try {
            deposits = sqlSession.selectList("com.solvd.bankapp.persistence.DepositDAO.getAll");
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all deposits", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return deposits;
    }
}
