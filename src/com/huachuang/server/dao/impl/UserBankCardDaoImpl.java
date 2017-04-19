package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserBankCardDao;
import com.huachuang.server.entity.UserBankCard;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/4/19.
 */

@Repository
public class UserBankCardDaoImpl implements UserBankCardDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public long create(UserBankCard card) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        long id = (long) session.save(card);
        tx.commit();
        if (id < 1) {
            LogFactory.getLog("Yang").error("Create new UserBankCardDao error");
        }
        return id;
    }

    @Override
    public void delete(UserBankCard card) {

    }

    @Override
    public void update(UserBankCard card) {

    }

    @Override
    public List<UserBankCard> retrieve(UserBankCard card) {
        return null;
    }

    @Override
    public List<UserBankCard> findCardsByUserID(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserBankCard> query = session.createQuery("from UserBankCard where userId = ?", UserBankCard.class);
        query.setParameter(0, userID);
        List<UserBankCard> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return null;
        }
        else {
            return result;
        }
    }
}
