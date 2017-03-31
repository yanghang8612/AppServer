package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.RecommendListDao;
import com.huachuang.server.entity.RecommendList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Asuka on 2017/3/31.
 */

@Repository
public class RecommendListDaoImpl implements RecommendListDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(RecommendList recommendList) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(recommendList);
        tx.commit();
    }
}
