package com.huachuang.server.dao.impl;

import com.huachuang.server.dao.UserTokenDao;
import com.huachuang.server.entity.UserToken;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Asuka on 2017/3/29.
 */

@Repository
public class UserTokenDaoImpl implements UserTokenDao {

    @Override
    public boolean create(UserToken userToken) {
        return true;
    }

    @Override
    public boolean delete(UserToken userToken) {
        return true;
    }

    @Override
    public boolean update(UserToken userToken) {
        return true;
    }

    @Override
    public List<UserToken> retrieve(UserToken userToken) {
        return null;
    }
}
