package com.huachuang.server.dao;

import com.huachuang.server.entity.UserFeedback;
import com.huachuang.server.entity.UserWithdraw;

import java.util.List;

/**
 * Created by Asuka on 2017/4/17.
 */

public interface UserWithdrawDao {

    void create(UserWithdraw userWithdraw);

    void delete(long id);

    void update(UserWithdraw userWithdraw);

    List<UserWithdraw> findAllWithdraw();

    List<UserWithdraw> findWithdrawByUserID(long userID);

    List<UserWithdraw> findWithdrawByState(byte state);

    UserWithdraw findWithdrawByID(long id);
}
