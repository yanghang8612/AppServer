package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserFeedbackDao;
import com.huachuang.server.entity.UserFeedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Asuka on 2017/6/12.
 */

@Repository
public class UserFeedbackDaoImpl implements UserFeedbackDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(UserFeedback userFeedback) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(userFeedback);
        tx.commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        UserFeedback feedback = session.get(UserFeedback.class, id);
        session.delete(feedback);
        tx.commit();
    }

    @Override
    public void update(UserFeedback userFeedback) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(userFeedback);
        tx.commit();
    }

    @Override
    public List<UserFeedback> findAllFeedback() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserFeedback> query = session.createQuery("from UserFeedback order by commitTime desc", UserFeedback.class);
        List<UserFeedback> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public List<UserFeedback> findFeedbackByInterval(int interval) {
        if (interval == 0) {
            return findAllFeedback();
        }
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserFeedback> query = session.createQuery("from UserFeedback where commitTime > ?", UserFeedback.class);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -interval);
        query.setParameter(0, calendar.getTime());
        List<UserFeedback> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public UserFeedback findFeedbackByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserFeedback> query = session.createQuery("from UserFeedback where id = ?", UserFeedback.class);
        query.setParameter(0, id);
        List<UserFeedback> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return null;
        }
        else {
            return result.get(0);
        }
    }
}
