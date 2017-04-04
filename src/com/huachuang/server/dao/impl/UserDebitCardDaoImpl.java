package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserDebitCardDao;
import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserCertificationInfo;
import com.huachuang.server.entity.UserDebitCard;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/4/4.
 */

@Repository
public class UserDebitCardDaoImpl implements UserDebitCardDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(UserDebitCard debitCard) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(debitCard);
        tx.commit();
    }

    @Override
    public void delete(UserDebitCard debitCard) {
    }

    @Override
    public void update(UserDebitCard debitCard) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(debitCard);
        tx.commit();
    }

    @Override
    public List<User> retrieve(UserDebitCard debitCard) {
        return null;
    }

    @Override
    public UserDebitCard findDebitCardByUserID(String userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserDebitCard> query = session.createQuery("from UserDebitCard where userId = ?", UserDebitCard.class);
        query.setParameter(0, userID);
        List<UserDebitCard> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return null;
        }
        else if (result.size() > 1) {
            LogFactory.getLog("Yang").error("Duplicate debit card");
            return null;
        }
        else {
            return result.get(0);
        }
    }
}
