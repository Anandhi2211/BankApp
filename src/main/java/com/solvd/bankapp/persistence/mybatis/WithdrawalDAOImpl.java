package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.Withdrawal;
import com.solvd.bankapp.persistence.WithdrawalDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class WithdrawalDAOImpl implements WithdrawalDAO {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.WithdrawalDAOImpl.class);

    @Override
    public void create(Withdrawal withdrawal) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            WithdrawalDAO withdrawalDAO = sqlSession.getMapper(WithdrawalDAO.class);
            withdrawalDAO.create(withdrawal);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating withdrawal", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Withdrawal> findById(int transactionId) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Optional<Withdrawal> optionalWithdrawal = Optional.empty();
        try {
            WithdrawalDAO withdrawalDAO = sqlSession.getMapper(WithdrawalDAO.class);
            withdrawalDAO.findById(transactionId);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding withdrawal by transaction ID", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return optionalWithdrawal;
    }

    @Override
    public List<Withdrawal> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        List<Withdrawal> withdrawals = null;
        try {
            withdrawals = sqlSession.selectList("com.solvd.bankapp.persistence.WithdrawalDAO.getAll");
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all withdrawals", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return withdrawals;
    }
}
