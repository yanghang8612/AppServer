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

    void delete(User user);

    void update(User user);

    List<User> retrieve(User user);

    User findUserByUserID(long userID);

    User findUserByPhoneNumber(String phoneNumber);

    User findUserByInvitationCode(String invitationNumber);

    List<User> findSubUsers(long userID);

    List<User> findSubDirectUsers(long userID);

    List<User> findAllAgents();

    List<User> findAllUsers();
}
