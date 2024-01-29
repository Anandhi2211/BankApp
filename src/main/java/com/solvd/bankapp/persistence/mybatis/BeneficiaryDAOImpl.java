package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.ConnectionPool;
import com.solvd.bankapp.domain.Beneficiary;
import com.solvd.bankapp.domain.Withdrawal;
import com.solvd.bankapp.persistence.BeneficiaryDAO;
import com.solvd.bankapp.persistence.TransactionDAO;
import com.solvd.bankapp.persistence.WithdrawalDAO;
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
import java.util.List;
import java.util.Optional;

public class BeneficiaryDAOImpl implements BeneficiaryDAO {
    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.BeneficiaryDAOImpl.class);
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Beneficiary beneficiary) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            BeneficiaryDAO beneficiaryDAO = sqlSession.getMapper(BeneficiaryDAO.class);
            beneficiaryDAO.create(beneficiary);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error creating beneficiary", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Beneficiary findByName(String beneficiaryName) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Beneficiary beneficiary = null;
        try {
            BeneficiaryDAO beneficiaryDAO = sqlSession.getMapper(BeneficiaryDAO.class);
            beneficiary = beneficiaryDAO.findByName(beneficiaryName);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding beneficiary by name", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return beneficiary;
    }

    @Override
    public ArrayList<Beneficiary> getAll(long accountNumber) {
        ArrayList<Beneficiary> beneficiaries;
        Connection connection = CONNECTION_POOL.getConnection();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("Select * from beneficiaries where account_number = "+accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            beneficiaries = displayTheResults(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return beneficiaries;
    }

    private ArrayList<Beneficiary> displayTheResults(ResultSet resultSet) {
        ArrayList<Beneficiary> beneficiaries = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Beneficiary beneficiary = new Beneficiary();
                beneficiary.setBeneficiaryName(resultSet.getString("beneficiary_name"));
                beneficiary.setBeneficiaryAccountNumber(resultSet.getLong("beneficiary_account_number"));
                beneficiary.setAccountNumber(resultSet.getLong("account_number"));
                beneficiaries.add(beneficiary);
            }
            return beneficiaries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<Beneficiary> getAll(long accountNumber) {
//        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
//        List<Beneficiary> beneficiaries = null;
//        try {
//            BeneficiaryDAO beneficiaryDAO = sqlSession.getMapper(BeneficiaryDAO.class);
//            beneficiaries = beneficiaryDAO.getAll(accountNumber);
//        } catch (PersistenceException e) {
//            LOGGER.error("Error getting all beneficiaries", e);
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//        return beneficiaries;
//    }

//    @Override
//    public Optional<Beneficiary> getAllBeneficiary(long accountNumber) {
//        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
//        Optional<Beneficiary> optionalBeneficiary =  Optional.empty();
//        try {
//            BeneficiaryDAO beneficiaryDAO = sqlSession.getMapper(BeneficiaryDAO.class);
//            optionalBeneficiary = beneficiaryDAO.getAllBeneficiary(accountNumber);
//            sqlSession.commit();
//        } catch (PersistenceException e) {
//            LOGGER.error("Error finding Beneficiary by customer_account number", e);
//            sqlSession.rollback();
//        } finally {
//            sqlSession.close();
//        }
//        return optionalBeneficiary;
//    }
}
