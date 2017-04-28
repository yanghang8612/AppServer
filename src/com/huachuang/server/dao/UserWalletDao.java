package com.huachuang.server.dao;

import com.huachuang.server.entity.UserWallet;
import com.huachuang.server.entity.WalletBalanceRecord;
import com.huachuang.server.entity.WalletPointsRecord;

import java.util.List;

/**
 * Created by Asuka on 2017/4/25.
 */
public interface UserWalletDao {

    void create(long userID);

    void delete(UserWallet wallet);

    void updateBalance(long userID, double amount);

    void updatePoints(long userID, int amount);

    double getBalance(long userID);

    int getPoints(long userID);

    List<WalletBalanceRecord> getBalanceRecord(long userID);

    List<WalletPointsRecord> getPointsRecord(long userID);

    void insertBalanceRecord(WalletBalanceRecord record);

    void insertPointsRecord(WalletPointsRecord record);
}