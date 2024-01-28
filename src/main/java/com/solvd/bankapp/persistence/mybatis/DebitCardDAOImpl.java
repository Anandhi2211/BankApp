package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.ConnectionPool;
import com.solvd.bankapp.domain.Account;
import com.solvd.bankapp.domain.DebitCard;
import com.solvd.bankapp.domain.Transaction;
import com.solvd.bankapp.persistence.DebitCardDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DebitCardDAOImpl implements DebitCardDAO {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.DebitCardDAOImpl.class);

    @Override
    public void create(DebitCard card) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            DebitCardDAO debitCardDAO = sqlSession.getMapper(DebitCardDAO.class);
            debitCardDAO.create(card);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating debit card", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delete(Long debitCardNumber) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            DebitCardDAO debitCardDAO = sqlSession.getMapper(DebitCardDAO.class);
            debitCardDAO.delete(debitCardNumber);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error deleting debit card", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public DebitCard findByCardNumber(long cardNumber) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        DebitCard debitCard = null;
        try {
            DebitCardDAO debitCardDAO = sqlSession.getMapper(DebitCardDAO.class);
            debitCard = debitCardDAO.findByCardNumber(cardNumber);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding Debit card by card number", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return debitCard;
    }

    @Override
    public ArrayList<Account> findAccountBySsn(long ssn) {
        ArrayList<Account> accounts = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select a.account_number, a.total_balance, a.logincredentials_username From customers c left join login_credentials l on l.customers_ssn = c.ssn left join accounts a on a.logincredentials_username = l.username where l.customers_ssn = " + ssn);
            ResultSet resultSet = preparedStatement.executeQuery();
            accounts = getAccounts(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return accounts;
    }

    private ArrayList<Account> getAccounts(ResultSet resultSet) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Account account = new Account();
                account.setAccountNumber(resultSet.getLong("account_number"));
                account.setTotalBalance(resultSet.getBigDecimal("total_balance"));
                account.setUsername(resultSet.getString("logincredentials_username"));
                accounts.add(account);
            }
            return accounts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DebitCard findBySsn(long ssn) {
        DebitCard debitCard = null;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from debit_cards where customers_ssn = " + ssn);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                debitCard = new DebitCard();
                debitCard.setCardNumber(resultSet.getLong("card_number"));
                debitCard.setSsn(resultSet.getLong("customers_ssn"));
                debitCard.setExpirationDate(resultSet.getTimestamp("expiration_date"));
                debitCard.setCvvNumber(resultSet.getInt("cvv_number"));
                debitCard.setCustomerFullName(resultSet.getString("customer_full_name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return debitCard;
    }

//    private ArrayList<DebitCard> getDebitCards(ResultSet resultSet) {
//        ArrayList<DebitCard> debitCards = new ArrayList<>();
//        try {
//            while (resultSet.next()) {
//                DebitCard debitCard = new DebitCard();
//                debitCard.setCardNumber(resultSet.getLong("card_number"));
//                debitCard.setExpirationDate(resultSet.getTimestamp("expiration_date"));
//                debitCard.setCvvNumber(resultSet.getInt("cvv_number"));
//                debitCard.setCustomerFullName(resultSet.getString("customer_full_name"));
//                debitCard.setSsn(resultSet.getLong("customers_ssn"));
//                debitCards.add(debitCard);
//            }
//            return debitCards;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
