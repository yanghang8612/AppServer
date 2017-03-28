package com.huachuang.server.service;

import com.huachuang.server.dao.UserManagerDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Asuka on 2017/3/28.
 */

@Service
public class UserManagerService {

    @Resource
    private UserManagerDao userManagerDao;

    public int getUserCountByPhoneNumber(String phoneNumber) {
        return userManagerDao.getUserInfo(phoneNumber).size();
    }
}
