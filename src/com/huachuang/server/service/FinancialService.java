package com.huachuang.server.service;

import com.huachuang.server.dao.ApplyCreditCardDao;
import com.huachuang.server.dao.ApplyLoanDao;
import com.huachuang.server.dao.PaymentOrderDao;
import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.entity.ApplyCreditCard;
import com.huachuang.server.entity.ApplyLoan;
import com.huachuang.server.entity.PaymentOrder;
import com.huachuang.server.entity.User;
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
        }
        Map<String, String> result = new HashMap<>();
        paymentOrderDao.create(paymentOrder);
        result.put("Status", "true");
        result.put("Info", "支付订单信息保存成功");
        return result;
    }
}
