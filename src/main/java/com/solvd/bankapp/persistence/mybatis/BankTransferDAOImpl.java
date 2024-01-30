package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.BankTransfer;
import com.solvd.bankapp.persistence.BankTransferDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BankTransferDAOImpl implements BankTransferDAO {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.BankTransferDAOImpl.class);

    @Override
    public void create(BankTransfer transfer) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            BankTransferDAO bankTransferDAO = sqlSession.getMapper(BankTransferDAO.class);
            bankTransferDAO.create(transfer);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating bank transfer", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<BankTransfer> findById(int transactionId) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Optional<BankTransfer> optionalTransfer = Optional.empty();
        try {
            BankTransferDAO bankTransferDAO = sqlSession.getMapper(BankTransferDAO.class);
            bankTransferDAO.findById(transactionId);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding bank transfer by transaction ID", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return optionalTransfer;
    }

    @Override
    public List<BankTransfer> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        List<BankTransfer> transfers = null;
        try {
            transfers = sqlSession.selectList("com.solvd.bankapp.persistence.BankTransferDAO.getAll");
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all bank transfers", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return transfers;
    }
}
