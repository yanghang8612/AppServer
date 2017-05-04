package com.huachuang.server.dao;

import com.huachuang.server.entity.ApplyCreditCard;
import com.huachuang.server.entity.ApplyLoan;

import java.util.List;

/**
 * Created by Asuka on 2017/4/17.
 */
public interface ApplyCreditCardDao {

    void create(ApplyCreditCard applyCreditCard);

    void delete(ApplyCreditCard applyCreditCard);

    void update(ApplyCreditCard applyCreditCard);

    List<ApplyCreditCard> findAllApplyRecords();

    ApplyCreditCard findApplyRecordByID(long id);
}
