package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.PaymentOrderDao;
import com.huachuang.server.entity.ApplyCreditCard;
import com.huachuang.server.entity.PaymentOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/5/23.
 */

@Repository
public class PaymentOrderDaoImpl implements PaymentOrderDao{

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(PaymentOrder paymentOrder) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(paymentOrder);
        tx.commit();
    }

    @Override
    public void delete(PaymentOrder paymentOrder) {

    }

    @Override
    public void update(PaymentOrder paymentOrder) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(paymentOrder);
        tx.commit();
    }

    @Override
    public List<PaymentOrder> findAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<PaymentOrder> query = session.createQuery("from PaymentOrder", PaymentOrder.class);
        List<PaymentOrder> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public List<PaymentOrder> findOrdersByID(long id) {
        return null;
    }
}
