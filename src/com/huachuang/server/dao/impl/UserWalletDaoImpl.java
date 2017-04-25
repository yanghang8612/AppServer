package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserWalletDao;
import com.huachuang.server.entity.UserWallet;
import com.huachuang.server.entity.WalletBalanceRecord;
import com.huachuang.server.entity.WalletPointsRecord;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Asuka on 2017/4/25.
 */

@Repository
public class UserWalletDaoImpl implements UserWalletDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void create(long userID) {
        UserWallet wallet = new UserWallet();
        wallet.setUserId(userID);
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(wallet);
        tx.commit();
    }

    @Override
    public void delete(UserWallet wallet) {

    }

    @Override
    public void updateBalance(long userID, double amount) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWallet> query = session.createQuery("from UserWallet where userId = ?", UserWallet.class);
        query.setParameter(0, userID);
        List<UserWallet> result = query.getResultList();
        UserWallet wallet = result.get(0);
        wallet.setWalletBalance(amount);
        session.update(wallet);
        tx.commit();
    }

    @Override
    public void updatePoints(long userID, int amount) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWallet> query = session.createQuery("from UserWallet where userId = ?", UserWallet.class);
        query.setParameter(0, userID);
        List<UserWallet> result = query.getResultList();
        UserWallet wallet = result.get(0);
        wallet.setWalletPoints(amount);
        session.update(wallet);
        tx.commit();
    }

    @Override
    public double getBalance(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWallet> query = session.createQuery("from UserWallet where userId = ?", UserWallet.class);
        query.setParameter(0, userID);
        List<UserWallet> result = query.getResultList();
        tx.commit();
        return result.get(0).getWalletBalance();
    }

    @Override
    public int getPoints(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWallet> query = session.createQuery("from UserWallet where userId = ?", UserWallet.class);
        query.setParameter(0, userID);
        List<UserWallet> result = query.getResultList();
        tx.commit();
        return result.get(0).getWalletPoints();
    }

    @Override
    public List<WalletBalanceRecord> getBalanceRecord(long userID) {
        return null;
    }

    @Override
    public List<WalletPointsRecord> getPointsRecord(long userID) {
        return null;
    }

    @Override
    public void insertBalanceRecord(WalletBalanceRecord record) {

    }

    @Override
    public void insertPointsRecord(WalletPointsRecord record) {

    }


}
