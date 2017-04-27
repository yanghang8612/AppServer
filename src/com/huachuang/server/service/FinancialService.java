package com.huachuang.server.service;

import com.huachuang.server.dao.ApplyLoanDao;
import com.huachuang.server.entity.ApplyLoan;
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

    public Map<String, String> createApplyLoan(ApplyLoan applyLoan) {
        Map<String, String> result = new HashMap<>();
        applyLoanDao.create(applyLoan);
        result.put("Status", "true");
        result.put("Info", "申请提交成功");
        return result;
    }

//    public Map<String, String> updateApplyLoan(long id, short state) {
//
//    }
}
