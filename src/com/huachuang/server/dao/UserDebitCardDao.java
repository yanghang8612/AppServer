package com.huachuang.server.dao;

import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserDebitCard;

import java.util.List;

/**
 * Created by Asuka on 2017/4/4.
 */
public interface UserDebitCardDao {

    void create(UserDebitCard debitCard);

    void delete(UserDebitCard debitCard);

    void update(UserDebitCard debitCard);

    List<User> retrieve(UserDebitCard debitCard);

    UserDebitCard findDebitCardByUserID(String userID);
}
