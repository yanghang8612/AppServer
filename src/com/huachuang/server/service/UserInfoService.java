package com.huachuang.server.service;

import com.huachuang.server.dao.UserCertificationInfoDao;
import com.huachuang.server.dao.UserDebitCardDao;
import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.dao.UserMobilePayDao;
import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserCertificationInfo;
import com.huachuang.server.entity.UserDebitCard;
import com.huachuang.server.entity.UserMobilePay;
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

    @Resource
    private UserMobilePayDao mobilePayDao;

    public Map<String, Object> createUserCertificationInfo(UserCertificationInfo certificationInfo) {
        Map<String, Object> result = new HashMap<>();
        certificationInfoDao.create(certificationInfo);
        User user = userManagerDao.findUserByUserID(certificationInfo.getUserId());
        user.setCertificationState(true);
        userManagerDao.update(user);
        result.put("Status", "true");
        result.put("Info", "实名认证信息添加成功");
        result.put("CertificationInfo", certificationInfo);
        return result;
    }

    public Map<String, Object> updateUserCertificationInfo(UserCertificationInfo certificationInfo) {
        Map<String, Object> result = new HashMap<>();
        certificationInfoDao.update(certificationInfo);
        result.put("Status", "true");
        result.put("Info", "实名认证信息更新成功");
        result.put("CertificationInfo", certificationInfo);
        return result;
    }

    public Map<String, Object> getUserCertificationInfo(long userID) {
        Map<String, Object> result = new HashMap<>();
        UserCertificationInfo certificationInfo = certificationInfoDao.findCertificationInfoByUserID(userID);
        if (certificationInfo != null) {
            result.put("Status", "true");
            result.put("Info", "实名认证信息查询成功");
            result.put("CertificationInfo", certificationInfo);
        }
        else {
            result.put("Status", "false");
            result.put("Info", "用户认证信息查询失败");
        }
        return result;
    }

    public Map<String, Object> createUserDebitCard(UserDebitCard debitCard) {
        Map<String, Object> result = new HashMap<>();
        debitCardDao.create(debitCard);
        User user = userManagerDao.findUserByUserID(debitCard.getUserId());
        user.setDebitCardState(true);
        userManagerDao.update(user);
        result.put("Status", "true");
        result.put("Info", "结算卡添加成功");
        result.put("DebitCard", debitCard);
        return result;
    }

    public Map<String, Object> updateUserDebitCard(UserDebitCard debitCard) {
        Map<String, Object> result = new HashMap<>();
        debitCardDao.update(debitCard);
        result.put("Status", "true");
        result.put("Info", "结算卡信息更新成功");
        result.put("DebitCard", debitCard);
        return result;
    }

    public Map<String, Object> getUserDebitCard(long userID) {
        Map<String, Object> result = new HashMap<>();
        UserDebitCard debitCard = debitCardDao.findDebitCardByUserID(userID);
        if (debitCard != null) {
            result.put("Status", "true");
            result.put("Info", "结算卡信息查询成功");
            result.put("DebitCard", debitCard);
        }
        else {
            result.put("Status", "false");
            result.put("Info", "结算卡信息查询失败");
        }
        return result;
    }

    public Map<String, Object> createUserMobilePayInfo(UserMobilePay mobilePay) {
        Map<String, Object> result = new HashMap<>();
        mobilePayDao.create(mobilePay);
        User user = userManagerDao.findUserByUserID(mobilePay.getUserId());
        user.setMobilePayState((byte) 1);
        userManagerDao.update(user);
        result.put("Status", "true");
        result.put("Info", "移动支付信息添加成功");
        result.put("MobilePayInfo", mobilePay);
        return result;
    }

    public Map<String, Object> getUserMobilePayInfo(long userID) {
        Map<String, Object> result = new HashMap<>();
        UserMobilePay mobilePayInfo = mobilePayDao.findMobilePayByUserID(userID);
        if (mobilePayInfo != null) {
            result.put("Status", "true");
            result.put("Info", "移动支付信息查询成功");
            result.put("MobilePayInfo", mobilePayInfo);
        }
        else {
            result.put("Status", "false");
            result.put("Info", "移动支付信息查询失败");
        }
        return result;
    }
}
