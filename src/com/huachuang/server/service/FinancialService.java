package com.huachuang.server.service;

import com.huachuang.server.CommonUtils;
import com.huachuang.server.dao.*;
import com.huachuang.server.entity.*;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
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
    private UserWithdrawDao userWithdrawDao;

    @Resource
    private UserWalletDao userWalletDao;

    @Resource
    private UserMobilePayDao userMobilePayDao;

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

            long[] superUserID = new long[]{0, 0, 0};
            int count = 0;
            boolean forceTakeProfit = false;
            User currentSuperUser = userManagerDao.findUserByUserID(user.getSuperiorUserId());
            while (true) {
                if (count == 3 || currentSuperUser == null) {
                    forceTakeProfit = (count == 3 && currentSuperUser != null);
                    break;
                }
                if ((currentSuperUser.getUserType() == 0 && currentSuperUser.isVip()) || currentSuperUser.getUserType() > 0) {
                    superUserID[count++] = currentSuperUser.getUserId();
                }
                currentSuperUser = userManagerDao.findUserByUserID(currentSuperUser.getSuperiorUserId());
            }
            User agent = userManagerDao.findUserByUserID(user.getAgentID());
            BigDecimal vipFee = new BigDecimal(CommonUtils.getProperty("VIP_FEE"));
            BigDecimal vipRegisterShareProfitRatio = new BigDecimal(CommonUtils.getProperty("VIP_REGISTER_SHARE_PROFIT_RATIO")).divide(new BigDecimal(100));
            BigDecimal agentCommonProfitRatio = new BigDecimal((CommonUtils.getProperty(agent.getUserType() == 1 ? "LEVEL_ONE_AGENT_COMMON_PROFIT_RATIO" : "LEVEL_TWO_AGENT_COMMON_PROFIT_RATIO"))).divide(new BigDecimal(100));
            BigDecimal profit = vipFee.multiply(vipRegisterShareProfitRatio);
            if (forceTakeProfit) {
                userWalletDao.updateBalance(agent.getUserId(), profit.multiply(agentCommonProfitRatio).doubleValue());
                WalletBalanceRecord record = new WalletBalanceRecord();
                record.setUserId(agent.getUserId());
                record.setType((byte) 4);
                record.setAmount(profit.multiply(agentCommonProfitRatio).doubleValue());
                userWalletDao.insertBalanceRecord(record);
                profit = profit.multiply(new BigDecimal(1).subtract(agentCommonProfitRatio));
            }
            BigDecimal[] profitRatio = new BigDecimal[]{new BigDecimal(CommonUtils.getProperty("VIP_LEVEL_ONE_PROFIT_RATIO")).divide(new BigDecimal(100)),
                    new BigDecimal(CommonUtils.getProperty("VIP_LEVEL_TWO_PROFIT_RATIO")).divide(new BigDecimal(100)),
                    new BigDecimal(CommonUtils.getProperty("VIP_LEVEL_THREE_PROFIT_RATIO")).divide(new BigDecimal(100))};

            for (int i = 0; i < 3 && superUserID[i] != 0; i++) {
                userWalletDao.updateBalance(superUserID[i], profit.multiply(profitRatio[i]).doubleValue());
                WalletBalanceRecord record = new WalletBalanceRecord();
                record.setUserId(superUserID[i]);
                record.setType((byte) i);
                record.setAmount(profit.multiply(profitRatio[i]).doubleValue());
                userWalletDao.insertBalanceRecord(record);
            }
        }
        Map<String, String> result = new HashMap<>();
        paymentOrderDao.create(paymentOrder);
        result.put("Status", "true");
        result.put("Info", "支付订单信息保存成功");
        return result;
    }

    public Map<String, String> updateMobilePayLoan(long userID, byte state, String mid, String key) {
        Map<String, String> result = new HashMap<>();
        User user = userManagerDao.findUserByUserID(userID);
        user.setMobilePayState(state);
        userManagerDao.update(user);
        if (state == 1) {
            UserMobilePay userMobilePay = new UserMobilePay();
            userMobilePay.setUserId(userID);
            userMobilePay.setMid(mid);
            userMobilePay.setKey(key);
            userMobilePayDao.create(userMobilePay);
        }
        result.put("Status", "true");
        result.put("Info", "用户进件状态更新成功");
        return result;
    }

    public Map<String, String> commitWithdraw(long userID, String cardNumber, String cardType, String bankName, double amount) {
        Map<String, String> result = new HashMap<>();
        UserWithdraw userWithdraw = new UserWithdraw();
        userWithdraw.setUserId(userID);
        userWithdraw.setCardNumber(cardNumber);
        userWithdraw.setCardType(cardType);
        userWithdraw.setBankName(bankName);
        userWithdraw.setAmount(amount);
        userWithdrawDao.create(userWithdraw);
        userWalletDao.updateBalance(userID, -amount);
        result.put("Status", "true");
        result.put("Info", "提现记录提交成功");
        return result;
    }

    public Map<String, String> updateWithdrawState(long id, byte state) {
        Map<String, String> result = new HashMap<>();
        UserWithdraw withdraw = userWithdrawDao.findWithdrawByID(id);
        withdraw.setState(state);
        userWithdrawDao.update(withdraw);
        if (state == 2) {
            userWalletDao.updateBalance(withdraw.getUserId(), withdraw.getAmount());
        }
        result.put("Status", "true");
        result.put("Info", "提现状态更新成功");
        return result;
    }

    public Map<String, Object> getWithdrawRecords(long userID) {
        Map<String, Object> result = new HashMap<>();
        List<UserWithdraw> records = userWithdrawDao.findWithdrawByUserID(userID);
        result.put("Status", "true");
        result.put("Info", "提现记录查询成功");
        result.put("Records", records);
        return result;
    }
}
