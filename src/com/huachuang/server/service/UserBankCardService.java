package com.huachuang.server.service;

import com.huachuang.server.dao.UserBankCardDao;
import com.huachuang.server.entity.UserBankCard;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Asuka on 2017/4/19.
 */

@Service
public class UserBankCardService {

    @Resource
    private UserBankCardDao bankCardDao;

    public Map<String, String> addBankCard(UserBankCard card) {
        Map<String, String> result = new HashMap<>();
        if (bankCardDao.create(card) > 0) {
            result.put("Status", "true");
            result.put("Info", "保存银行卡信息成功");
        }
        else {
            result.put("Status", "false");
            result.put("Info", "保存银行卡信息失败");
        }
        return result;
    }

    public Map<String, Object> getAllBankCards(long userID) {
        Map<String, Object> result = new HashMap<>();
        List<UserBankCard> cards = bankCardDao.findCardsByUserID(userID);
        if (cards != null) {
            result.put("Status", "true");
            result.put("Info", "查询用户银行卡列表成功");
            result.put("Cards", cards);
        }
        else {
            result.put("Status", "false");
            result.put("Info", "查询用户银行卡列表失败");
        }
        return result;
    }
}
