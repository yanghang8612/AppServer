package com.huachuang.server.dao;

import com.huachuang.server.entity.User;

import java.util.List;

/**
 * Created by Asuka on 2017/3/19.
 */

public interface UserManagerDao {

    boolean verifyPhoneNumber(String phoneNumber);

    boolean verifyInvitationCode(String invitationCode);

    boolean verifyRecommenderID(String recommenderID);

    long create(User user);

    boolean delete(User user);

    boolean update(User user);

    List<User> retrieve(User user);

    User findUserByPhoneNumber(String phoneNumber);

    User findUserByInvitationCode(String invitationNumber);
}
