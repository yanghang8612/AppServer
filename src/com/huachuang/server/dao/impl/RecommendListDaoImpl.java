package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.RecommendListDao;
import com.huachuang.server.entity.RecommendList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<RecommendList> findRecommendRecordByUserID(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<RecommendList> query = session.createQuery("from RecommendList where recommenderId = ? order by recommendTime desc", RecommendList.class);
        query.setParameter(0, userID);
        List<RecommendList> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public long findRecommenderIDByUserID(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<RecommendList> query = session.createQuery("from RecommendList where recommendedId = ?", RecommendList.class);
        query.setParameter(0, userID);
        List<RecommendList> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return -1;
        }
        return result.get(0).getRecommenderId();
    }
}
