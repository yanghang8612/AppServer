package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserMobilePayDao;
import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserMobilePay;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/6/6.
 */

@Repository
public class UserMobilePayDaoImpl implements UserMobilePayDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(UserMobilePay mobilePay) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(mobilePay);
        tx.commit();
    }

    @Override
    public void delete(UserMobilePay mobilePay) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.remove(mobilePay);
        tx.commit();
    }

    @Override
    public void update(UserMobilePay mobilePay) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(mobilePay);
        tx.commit();
    }

    @Override
    public UserMobilePay findMobilePayByUserID(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserMobilePay> query = session.createQuery("from UserMobilePay where userId = ?", UserMobilePay.class);
        query.setParameter(0, userID);
        List<UserMobilePay> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return null;
        }
        else if (result.size() > 1) {
            LogFactory.getLog("Yang").error("Duplicate mobile pay");
            return null;
        }
        else {
            return result.get(0);
        }
    }
}
