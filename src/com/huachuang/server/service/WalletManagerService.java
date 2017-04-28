package com.huachuang.server.service;

import com.huachuang.server.dao.UserWalletDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asuka on 2017/4/19.
 */

@Service
public class WalletManagerService {

    @Resource
    private UserWalletDao userWalletDao;

    public double getUserWalletBalance(long userID) {
        return userWalletDao.getBalance(userID);
    }

    public int getUserWalletPoints(long userID) {
        return userWalletDao.getPoints(userID);
    }

    public Map<String, String> getUserWallet(long userID) {
        Map<String, String> result = new HashMap<>();
        result.put("Status", "true");
        result.put("Info", "查询成功");
        result.put("Balance", String.valueOf(userWalletDao.getBalance(userID)));
        result.put("Points", String.valueOf(userWalletDao.getPoints(userID)));
        return result;
    }

    public Map<String, Object> getWalletBalanceRecords(long userID) {
        Map<String, Object> result = new HashMap<>();
        result.put("Status", "true");
        result.put("Info", "查询成功");
        result.put("Records", userWalletDao.getBalanceRecords(userID));
        return result;
    }

    public Map<String, Object> getWalletPointsRecords(long userID) {
        Map<String, Object> result = new HashMap<>();
        result.put("Status", "true");
        result.put("Info", "查询成功");
        result.put("Records", userWalletDao.getPointsRecords(userID));
        return result;
    }
}
