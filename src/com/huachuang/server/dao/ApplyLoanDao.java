package com.huachuang.server.dao;

import com.huachuang.server.entity.ApplyLoan;
import com.huachuang.server.entity.UserToken;

import java.util.List;

/**
 * Created by Asuka on 2017/4/17.
 */
public interface ApplyLoanDao {

    long create(ApplyLoan applyLoan);

    void delete(ApplyLoan applyLoan);

    void update(ApplyLoan applyLoan);

    List<ApplyLoan> findAllApplyRecords();

    List<ApplyLoan> findApplyRecordsByState(byte state);

    ApplyLoan findApplyRecordByID(long id);
}
