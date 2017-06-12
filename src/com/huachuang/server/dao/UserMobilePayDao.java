package com.huachuang.server.dao;

import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserDebitCard;
import com.huachuang.server.entity.UserMobilePay;

import java.util.List;

/**
 * Created by Asuka on 2017/4/4.
 */

public interface UserMobilePayDao {

    void create(UserMobilePay mobilePay);

    void delete(UserMobilePay mobilePay);

    void update(UserMobilePay mobilePay);

    UserMobilePay findMobilePayByUserID(long userID);
}
