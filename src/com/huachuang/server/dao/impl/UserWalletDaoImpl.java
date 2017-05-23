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
    public void delete(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("delete UserWallet where userId = ?");
        query.setParameter(0, userID);
        query.executeUpdate();
        tx.commit();
    }

    @Override
    public void updateBalance(long userID, double amount) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<UserWallet> query = session.createQuery("from UserWallet where userId = ?", UserWallet.class);
        query.setParameter(0, userID);
        List<UserWallet> result = query.getResultList();
        UserWallet wallet = result.get(0);
        wallet.setWalletBalance(wallet.getWalletBalance() + amount);
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
        wallet.setWalletPoints(wallet.getWalletPoints() + amount);
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
    public List<WalletBalanceRecord> getBalanceRecords(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<WalletBalanceRecord> query = session.createQuery("from WalletBalanceRecord where userId = ?", WalletBalanceRecord.class);
        query.setParameter(0, userID);
        List<WalletBalanceRecord> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public List<WalletPointsRecord> getPointsRecords(long userID) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Query<WalletPointsRecord> query = session.createQuery("from WalletPointsRecord where userId = ?", WalletPointsRecord.class);
        query.setParameter(0, userID);
        List<WalletPointsRecord> result = query.getResultList();
        tx.commit();
        return result;
    }

    @Override
    public void insertBalanceRecord(WalletBalanceRecord record) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(record);
        tx.commit();
    }

    @Override
    public void insertPointsRecord(WalletPointsRecord record) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(record);
        tx.commit();
    }
}
