package com.huachuang.server.dao;

import com.huachuang.server.entity.User;

import java.util.List;

/**
 * Created by Asuka on 2017/3/19.
 */

public interface UserManagerDao {
    // 获取用户信息
    List<User> getUserInfo(String userPhoneNumber);

    // 注册
    void register(User user);

    // 增
    void create(User user);

    // 删
    void delete(User user);

    // 改
    void update(User user);

    // 查
    List<User> retrieve(User user);
}
