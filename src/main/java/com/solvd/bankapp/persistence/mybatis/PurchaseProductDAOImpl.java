package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.ConnectionPool;
import com.solvd.bankapp.domain.PurchaseProduct;
import com.solvd.bankapp.domain.Transaction;
import com.solvd.bankapp.persistence.PurchaseProductDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PurchaseProductDAOImpl implements PurchaseProductDAO {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.PurchaseProductDAOImpl.class);

    @Override
    public void create(PurchaseProduct purchase) {
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
            purchaseProductDAO.findById(transactionId);
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
    public ArrayList<PurchaseProduct> getAll() {
        ArrayList<PurchaseProduct> purchaseProducts;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("Select * from purchase_products");
            ResultSet resultSet = preparedStatement.executeQuery();
            purchaseProducts = displayResultsProducts(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return purchaseProducts;
    }

    private ArrayList<PurchaseProduct> displayResultsProducts(ResultSet resultSet) {
        ArrayList<PurchaseProduct> purchaseProducts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                PurchaseProduct purchaseProduct = new PurchaseProduct();
                purchaseProduct.setCardNumber(resultSet.getLong("debitcards_card_number"));
                purchaseProduct.setPurchaseDescription(resultSet.getString("purchase_description"));
                purchaseProduct.setAmount(resultSet.getBigDecimal("amount"));
                purchaseProduct.setTransactionId(resultSet.getInt("transactions_transaction_id"));
                purchaseProduct.setSsn(resultSet.getLong("customers_ssn"));
                purchaseProduct.setPurchaseTimestamp(Timestamp.valueOf(resultSet.getString("purchase_timestamp")));
                purchaseProducts.add(purchaseProduct);
            }
            return purchaseProducts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
