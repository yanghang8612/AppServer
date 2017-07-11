package com.huachuang.server.dao.impl;

import com.huachuang.server.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by Asuka on 2017/6/1.
 */

@Repository
public class TestDaoImpl {

    @Resource
    private SessionFactory sessionFactory;

    public void createRows() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        long preTimeStamp = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            Test test = new Test();
            test.setRandomNumber(new Random().nextInt());
            session.save(test);
        }
        System.out.println(System.nanoTime() - preTimeStamp);
        tx.commit();
    }
}
