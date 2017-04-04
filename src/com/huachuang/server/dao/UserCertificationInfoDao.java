package com.huachuang.server.dao;

import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserCertificationInfo;

import java.util.List;

/**
 * Created by Asuka on 2017/4/4.
 */
public interface UserCertificationInfoDao {

    void create(UserCertificationInfo certificationInfo);

    void delete(UserCertificationInfo certificationInfo);

    void update(UserCertificationInfo certificationInfo);

    List<User> retrieve(UserCertificationInfo certificationInfo);

    UserCertificationInfo findCertificationInfoByUserID(long userID);
}
