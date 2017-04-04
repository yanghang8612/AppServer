package com.huachuang.server.service;

import com.huachuang.server.dao.UserCertificationInfoDao;
import com.huachuang.server.dao.UserDebitCardDao;
import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserCertificationInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asuka on 2017/4/4.
 */

@Service
public class UserInfoService {

    @Resource
    private UserManagerDao userManagerDao;

    @Resource
    private UserCertificationInfoDao certificationInfoDao;

    @Resource
    private UserDebitCardDao debitCardDao;

    public Map<String, Object> createUserCertificationInfo(UserCertificationInfo certificationInfo) {
        Map<String, Object> result = new HashMap<>();
        certificationInfoDao.create(certificationInfo);
        User user = userManagerDao.findUserByUserID(certificationInfo.getUserId());
        user.setCertificationState(true);
        userManagerDao.update(user);
        result.put("Status", "true");
        result.put("Info", "添加成功");
        result.put("CertificationInfo", certificationInfo);
        return result;
    }

    public Map<String, Object> updateUserCertificationInfo(UserCertificationInfo certificationInfo) {
        Map<String, Object> result = new HashMap<>();
        certificationInfoDao.update(certificationInfo);
        result.put("Status", "true");
        result.put("Info", "更新成功");
        result.put("CertificationInfo", certificationInfo);
        return result;
    }

    public Map<String, Object> getUserCertificationInfo(long userID) {
        Map<String, Object> result = new HashMap<>();
        UserCertificationInfo certificationInfo = certificationInfoDao.findCertificationInfoByUserID(userID);
        if (certificationInfo != null) {
            result.put("Status", "true");
            result.put("CertificationInfo", certificationInfo);
        }
        else {
            result.put("Status", "false");
            result.put("Info", "用户认证信息查询失败");
        }
        return result;
    }
}
