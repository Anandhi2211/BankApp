package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.ConcreteTransaction;
import com.solvd.bankapp.domain.PurchaseProduct;
import com.solvd.bankapp.persistence.PurchaseProductDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class PurchaseProductDAOImpl implements PurchaseProductDAO {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.PurchaseProductDAOImpl.class);

    @Override
    public void create(ConcreteTransaction purchase) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            PurchaseProductDAO purchaseProductDAO = sqlSession.getMapper(PurchaseProductDAO.class);
            purchaseProductDAO.create(purchase);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating purchase", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<PurchaseProduct> findById(int transactionId) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Optional<PurchaseProduct> optionalPurchase = Optional.empty();
        try {
            PurchaseProductDAO purchaseProductDAO = sqlSession.getMapper(PurchaseProductDAO.class);
            optionalPurchase = purchaseProductDAO.findById(transactionId);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding purchase by transaction ID", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return optionalPurchase;
    }

    @Override
    public List<PurchaseProduct> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        List<PurchaseProduct> purchases = null;
        try {
            purchases = sqlSession.selectList("com.solvd.bankapp.persistence.PurchaseProductDAO.getAll");
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all purchases", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return purchases;
    }
}
