package com.huachuang.server.dao;

import com.huachuang.server.entity.UserToken;

import java.util.List;

/**
 * Created by Asuka on 2017/3/29.
 */

public interface UserTokenDao {

    boolean create(UserToken userToken);

    boolean delete(UserToken userToken);

    boolean update(UserToken userToken);

    List<UserToken> retrieve(UserToken userToken);
}
