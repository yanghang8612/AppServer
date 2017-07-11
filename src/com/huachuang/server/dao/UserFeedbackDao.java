package com.huachuang.server.dao;

import com.huachuang.server.entity.UserFeedback;

import java.util.List;

/**
 * Created by Asuka on 2017/4/17.
 */

public interface UserFeedbackDao {

    void create(UserFeedback userFeedback);

    void delete(long id);

    void update(UserFeedback userFeedback);

    List<UserFeedback> findAllFeedback();

    List<UserFeedback> findFeedbackByInterval(int interval);

    UserFeedback findFeedbackByID(long id);
}
