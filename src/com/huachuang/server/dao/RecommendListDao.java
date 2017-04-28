package com.huachuang.server.dao;

import com.huachuang.server.entity.RecommendList;

import java.util.List;

/**
 * Created by Asuka on 2017/3/31.
 */
public interface RecommendListDao {

    void create(RecommendList recommendList);

    List<RecommendList> findRecommendRecordByUserID(long userID);

    long findRecommenderIDByUserID(long userID);
}
