package com.huachuang.server.service;

import com.huachuang.server.dao.*;
import com.huachuang.server.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asuka on 2017/4/17.
 */

@Service
public class FinancialService {

    @Resource
    private ApplyLoanDao applyLoanDao;

    @Resource
    private ApplyCreditCardDao applyCreditCardDao;

    @Resource
    private PaymentOrderDao paymentOrderDao;

    @Resource
    private UserManagerDao userManagerDao;

    @Resource
    private RecommendListDao recommendListDao;

    @Resource
    private UserWalletDao userWalletDao;

    public Map<String, String> createApplyLoan(ApplyLoan applyLoan) {
        Map<String, String> result = new HashMap<>();
        applyLoanDao.create(applyLoan);
        result.put("Status", "true");
        result.put("Info", "贷款申请提交成功");
        return result;
    }

    public Map<String, String> updateApplyLoan(long id, byte state) {
        Map<String, String> result = new HashMap<>();
        ApplyLoan applyLoan = applyLoanDao.findApplyRecordByID(id);
        applyLoan.setApplyState(state);
        applyLoanDao.update(applyLoan);
        result.put("Status", "true");
        result.put("Info", "贷款申请状态更新成功");
        return result;
    }

    public Map<String, String> createApplyCreditCard(ApplyCreditCard applyCreditCard) {
        Map<String, String> result = new HashMap<>();
        applyCreditCardDao.create(applyCreditCard);
        result.put("Status", "true");
        result.put("Info", "信用卡申请信息保存成功");
        return result;
    }

    public Map<String, String> createPaymentOrder(PaymentOrder paymentOrder) {
        if (paymentOrder.getOrderType() == 0 && paymentOrder.getOrderState() == 1) {
            User user = userManagerDao.findUserByUserID(paymentOrder.getUserId());
            user.setVip(true);
            userManagerDao.update(user);

            //update direct recommender wallet and insert wallet balance record
            long directUserID = recommendListDao.findRecommenderIDByUserID(user.getUserId());
            User directUser = userManagerDao.findUserByUserID(directUserID);
            if (directUser.getUserType() != 0 || directUser.isVip()) {
                userWalletDao.updateBalance(directUserID, 25);
                WalletBalanceRecord directRecord = new WalletBalanceRecord();
                directRecord.setUserId(directUserID);
                directRecord.setType((byte) 1);
                directRecord.setAmount(25);
                userWalletDao.insertBalanceRecord(directRecord);
            }

            //update derived recommender wallet
            long derivedUserID = recommendListDao.findRecommenderIDByUserID(directUserID);
            User derivedUser = userManagerDao.findUserByUserID(derivedUserID);
            if (derivedUserID != -1 && (derivedUser.getUserType() != 0 || derivedUser.isVip())) {
                userWalletDao.updateBalance(derivedUserID, 12.5);
                WalletBalanceRecord derivedRecord = new WalletBalanceRecord();
                derivedRecord.setUserId(derivedUserID);
                derivedRecord.setType((byte) 2);
                derivedRecord.setAmount(12.5);
                userWalletDao.insertBalanceRecord(derivedRecord);
            }

            //update derived recommender wallet
            long thirdUserID = recommendListDao.findRecommenderIDByUserID(derivedUserID);
            User thirdUser = userManagerDao.findUserByUserID(thirdUserID);
            if (thirdUserID != -1 && (thirdUser.getUserType() != 0 || thirdUser.isVip())) {
                userWalletDao.updateBalance(thirdUserID, 5);
                WalletBalanceRecord thirdRecord = new WalletBalanceRecord();
                thirdRecord.setUserId(thirdUserID);
                thirdRecord.setType((byte) 3);
                thirdRecord.setAmount(5);
                userWalletDao.insertBalanceRecord(thirdRecord);
            }
        }
        Map<String, String> result = new HashMap<>();
        paymentOrderDao.create(paymentOrder);
        result.put("Status", "true");
        result.put("Info", "支付订单信息保存成功");
        return result;
    }
}
