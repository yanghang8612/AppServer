package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.ApplyLoanDao;
import com.huachuang.server.entity.ApplyLoan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/4/17.
 */

@Repository
public class ApplyLoanDaoImpl implements ApplyLoanDao{

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public long create(ApplyLoan applyLoan) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        long id = (long) session.save(applyLoan);
        tx.commit();
        return id;
    }

    @Override
    public void delete(ApplyLoan applyLoan) {

    }

    @Override
    public void update(ApplyLoan applyLoan) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(applyLoan);
        tx.commit();
    }

    @Override
    public List<ApplyLoan> findAllApplyRecords() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<ApplyLoan> query = session.createQuery("from ApplyLoan", ApplyLoan.class);
        List<ApplyLoan> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public List<ApplyLoan> findApplyRecordsByState(byte state) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<ApplyLoan> query = session.createQuery("from ApplyLoan where applyState = ?", ApplyLoan.class);
        query.setParameter(0, state);
        List<ApplyLoan> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public ApplyLoan findApplyRecordByID(long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<ApplyLoan> query = session.createQuery("from ApplyLoan where id = ?", ApplyLoan.class);
        query.setParameter(0, id);
        List<ApplyLoan> result = query.getResultList();
        tx.commit();
        return (result == null || result.size() == 0) ? null : result.get(0);
    }
}
