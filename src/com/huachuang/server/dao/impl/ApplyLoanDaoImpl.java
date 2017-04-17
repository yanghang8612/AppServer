package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.ApplyLoanDao;
import com.huachuang.server.entity.ApplyLoan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
    public List<ApplyLoan> retrieve(ApplyLoan applyLoan) {
        return null;
    }
}
