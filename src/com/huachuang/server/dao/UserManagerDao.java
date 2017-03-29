package com.huachuang.server.dao;

import com.huachuang.server.entity.User;

import java.util.List;

/**
 * Created by Asuka on 2017/3/19.
 */

public interface UserManagerDao {

    boolean verifyPhoneNumber(String phoneNumber);

    boolean verifyMessageCode(String phoneNumber, String messageCode);

    boolean verifyInvitationCode(String invitationCode);

    List<User> getUserInfo(String phoneNumber);

    void create(User user);

    void delete(User user);

    void update(User user);

    List<User> retrieve(User user);
}
