package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.ApplyCreditCardDao;
import com.huachuang.server.entity.ApplyCreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/5/4.
 */

@Repository
public class ApplyCreditCardDaoImpl implements ApplyCreditCardDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(ApplyCreditCard applyCreditCard) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(applyCreditCard);
        tx.commit();
    }

    @Override
    public void delete(ApplyCreditCard applyCreditCard) {

    }

    @Override
    public void update(ApplyCreditCard applyCreditCard) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(applyCreditCard);
        tx.commit();
    }

    @Override
    public List<ApplyCreditCard> findAllApplyRecords() {
        return null;
    }

    @Override
    public ApplyCreditCard findApplyRecordByID(long id) {
        return null;
    }
}
