package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.IUserManager;
import com.huachuang.server.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Asuka on 2017/3/19.
 */
public class UserManagerImpl implements IUserManager {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getUserInfo(String userPhoneNumber) {
        return null;
    }

    @Override
    public void register(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> retrieve(User user) {
        return null;
    }
}
