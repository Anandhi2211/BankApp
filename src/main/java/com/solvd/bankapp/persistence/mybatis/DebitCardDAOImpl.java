package com.solvd.bankapp.persistence.mybatis;

import com.solvd.bankapp.domain.DebitCard;
import com.solvd.bankapp.persistence.DebitCardDAO;
import com.solvd.bankapp.util.Config;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebitCardDAOImpl implements DebitCardDAO {

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
    public void delete(DebitCard card) {
        SqlSession sqlSession = Config.getSessionFactory().openSession(false);
        try {
            DebitCardDAO debitCardDAO = sqlSession.getMapper(DebitCardDAO.class);
            debitCardDAO.delete(card);
            sqlSession.commit();
        } catch (PersistenceException e) {
            LOGGER.error("Error deleting debit card", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
