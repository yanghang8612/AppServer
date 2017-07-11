package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserWithdrawDao;
import com.huachuang.server.entity.UserWithdraw;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Asuka on 2017/7/4.
 */

@Repository
public class UserWithdrawDaoImpl implements UserWithdrawDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(UserWithdraw userWithdraw) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(userWithdraw);
        tx.commit();
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        UserWithdraw withdraw = session.get(UserWithdraw.class, id);
        session.delete(withdraw);
        tx.commit();
    }

    @Override
    public void update(UserWithdraw userWithdraw) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(userWithdraw);
        tx.commit();
    }

    @Override
    public List<UserWithdraw> findAllWithdraw() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWithdraw> query = session.createQuery("from UserWithdraw order by commitTime desc", UserWithdraw.class);
        List<UserWithdraw> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public List<UserWithdraw> findWithdrawByUserID(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWithdraw> query = session.createQuery("from UserWithdraw where userId = ? order by commitTime desc", UserWithdraw.class);
        query.setParameter(0, userID);
        List<UserWithdraw> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public List<UserWithdraw> findWithdrawByState(byte state) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWithdraw> query = session.createQuery("from UserWithdraw where state = ? order by commitTime desc", UserWithdraw.class);
        query.setParameter(0, state);
        List<UserWithdraw> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public UserWithdraw findWithdrawByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWithdraw> query = session.createQuery("from UserWithdraw where id = ?", UserWithdraw.class);
        query.setParameter(0, id);
        List<UserWithdraw> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return null;
        }
        else {
            return result.get(0);
        }
    }
}
