package com.huachuang.server.dao;

import com.huachuang.server.entity.UserBankCard;

import java.util.List;

/**
 * Created by Asuka on 2017/4/19.
 */
public interface UserBankCardDao {

    long create(UserBankCard card);

    void delete(UserBankCard card);

    void update(UserBankCard card);

    List<UserBankCard> retrieve(UserBankCard card);

    List<UserBankCard> findCardsByUserID(long userID);
}
