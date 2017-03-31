package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.entity.User;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Asuka on 2017/3/19.
 */

@Repository
public class UserManagerDaoImpl implements UserManagerDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public boolean verifyPhoneNumber(String phoneNumber) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("from User where userPhoneNumber = ?", User.class);
        query.setParameter(0, phoneNumber);
        List<User> result = query.getResultList();
        tx.commit();
        if (result != null && result.size() > 1) {
            LogFactory.getLog("Yang").error("Duplicate Phone number");
        }
        return (result != null && result.size() == 0);
    }

    @Override
    public boolean verifyInvitationCode(String invitationCode) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("from User where invitationCode = ?", User.class);
        query.setParameter(0, invitationCode);
        List<User> result = query.getResultList();
        tx.commit();
        if (result != null && result.size() > 1) {
            LogFactory.getLog("Yang").error("Duplicate invitation code");
        }
        return (result != null && result.size() == 1);
    }

    @Override
    public boolean verifyRecommenderID(String recommenderID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("from User where userPhoneNumber = ?", User.class);
        query.setParameter(0, recommenderID);
        List<User> result = query.getResultList();
        tx.commit();
        if (result != null && result.size() > 1) {
            LogFactory.getLog("Yang").error("Duplicate invitation code");
        }
        return (result != null && result.size() == 1);
    }

    @Override
    public long create(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        long id = (long) session.save(user);
        tx.commit();
        return id;
    }

    @Override
    public boolean delete(User user) {
        return true;
    }

    @Override
    public boolean update(User user) {
        return true;
    }

    @Override
    public List<User> retrieve(User user) {
        return null;
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("from User where userPhoneNumber = ?", User.class);
        query.setParameter(0, phoneNumber);
        List<User> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return null;
        }
        else if (result.size() > 1) {
            LogFactory.getLog("Yang").error("Duplicate phone number");
            return null;
        }
        else {
            return result.get(0);
        }
    }

    @Override
    public User findUserByInvitationCode(String invitationNumber) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("from User where invitationCode = ?", User.class);
        query.setParameter(0, invitationNumber);
        List<User> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return null;
        }
        else if (result.size() > 1) {
            LogFactory.getLog("Yang").error("Duplicate invitation code");
            return null;
        }
        else {
            return result.get(0);
        }
    }
}
