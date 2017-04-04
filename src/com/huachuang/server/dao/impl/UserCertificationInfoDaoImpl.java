package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserCertificationInfoDao;
import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserCertificationInfo;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/4/4.
 */

@Repository
public class UserCertificationInfoDaoImpl implements UserCertificationInfoDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(UserCertificationInfo certificationInfo) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        long id = (long) session.save(certificationInfo);
        certificationInfo.setId(id);
        tx.commit();
    }

    @Override
    public void delete(UserCertificationInfo certificationInfo) {
    }

    @Override
    public void update(UserCertificationInfo certificationInfo) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.update(certificationInfo);
        tx.commit();
    }

    @Override
    public List<User> retrieve(UserCertificationInfo certificationInfo) {
        return null;
    }

    @Override
    public UserCertificationInfo findCertificationInfoByUserID(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserCertificationInfo> query = session.createQuery("from UserCertificationInfo where userId = ?", UserCertificationInfo.class);
        query.setParameter(0, userID);
        List<UserCertificationInfo> result = query.getResultList();
        tx.commit();
        if (result == null || result.size() == 0) {
            return null;
        }
        else if (result.size() > 1) {
            LogFactory.getLog("Yang").error("Duplicate certification info");
            return null;
        }
        else {
            return result.get(0);
        }
    }
}
