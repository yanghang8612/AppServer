package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/3/19.
 */

@Repository
public class UserManagerDaoImpl implements UserManagerDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUserInfo(String userPhoneNumber) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query<User> query = session.createQuery("from User where userPhoneNumber = ?", User.class);
        query.setParameter(0, userPhoneNumber);
        List<User> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> retrieve(User user) {
        return null;
    }
}
