package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.Customer;
import com.solvd.bankapp.domain.LoginCredential;
import com.solvd.bankapp.persistence.CustomerDAO;
import com.solvd.bankapp.persistence.LoginCredentialDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerDAOImpl implements CustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.CustomerDAOImpl.class);

    @Override
    public void create(Customer customer) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            System.out.println("im here");
            CustomerDAO customerDAO = sqlSession.getMapper(CustomerDAO.class);
            customerDAO.create(customer);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating customer", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }



    @Override
    public void update(Customer customer) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            CustomerDAO customerDAO = sqlSession.getMapper(CustomerDAO.class);
            customerDAO.update(customer);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error updating customer", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void delete(Customer customer) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            CustomerDAO customerDAO = sqlSession.getMapper(CustomerDAO.class);
            customerDAO.delete(customer);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error deleting customer", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Customer findBySsn(long ssn) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Customer customer = null;
        try {
            CustomerDAO customerDAO = sqlSession.getMapper(CustomerDAO.class);
            customer = customerDAO.findBySsn(ssn);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding Customer details by ssn", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return customer;    }

    @Override
    public Customer display(String username) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Customer customer = null;
        try {
            CustomerDAO customerDAO = sqlSession.getMapper(CustomerDAO.class);
            customer = customerDAO.display(username);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding Customer details by username", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return customer;
    }
}
