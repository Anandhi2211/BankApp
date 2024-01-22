package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.Beneficiary;
import com.solvd.bankapp.persistence.BeneficiaryDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BeneficiaryDAOImpl implements BeneficiaryDAO {

    private static final Logger LOGGER = LogManager.getLogger(com.solvd.bankapp.persistence.mybatis.BeneficiaryDAOImpl.class);

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
    public Optional<Beneficiary> findByName(String beneficiaryName) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        Optional<Beneficiary> optionalBeneficiary = Optional.empty();
        try {
            BeneficiaryDAO beneficiaryDAO = sqlSession.getMapper(BeneficiaryDAO.class);
            beneficiaryDAO.findByName(beneficiaryName);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error finding beneficiary by name", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return optionalBeneficiary;
    }

    @Override
    public List<Beneficiary> getAll() {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        List<Beneficiary> beneficiaries = null;
        try {
            beneficiaries = sqlSession.selectList("com.solvd.bankapp.persistence.BeneficiaryDAO.getAll");
        } catch (PersistenceException e) {
            LOGGER.error("Error getting all beneficiaries", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return beneficiaries;
    }
}
