package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.Payment;
import com.solvd.bankapp.persistence.PaymentDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.PaymentDAOImpl.class);

    @Override
    public void create(Payment payment) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            PaymentDAO paymentDAO = sqlSession.getMapper(PaymentDAO.class);
            paymentDAO.create(payment);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating payment", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Payment findPaymentById(long billAccountNumber) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Payment payment = null;
        try {
            PaymentDAO paymentDAO = sqlSession.getMapper(PaymentDAO.class);
            payment = paymentDAO.findPaymentById(billAccountNumber);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding Payment By bill Account Number", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return payment;
    }

    @Override
    public Optional<Payment> findById(int transactionId) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Optional<Payment> optionalPayment = Optional.empty();
        try {
            PaymentDAO paymentDAO = sqlSession.getMapper(PaymentDAO.class);
            paymentDAO.findById(transactionId);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding payment by transaction ID", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return optionalPayment;
    }

    @Override
    public List<Payment> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        List<Payment> payments = null;
        try {
            payments = sqlSession.selectList("com.solvd.bankapp.persistence.PaymentDAO.getAll");
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all payments", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return payments;
    }
}
