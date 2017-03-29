package com.huachuang.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Asuka on 2017/3/29.
 */

@Entity
@Table(name = "recommend_list")
public class RecommendList {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "recommender_id")
    private long recommenderId;

    @Column(name = "recommended_id")
    private long recommendedId;

    @Column(name = "recommend_time")
    private Date recommendTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRecommenderId() {
        return recommenderId;
    }

    public void setRecommenderId(long recommenderId) {
        this.recommenderId = recommenderId;
    }

    public long getRecommendedId() {
        return recommendedId;
    }

    public void setRecommendedId(long recommendedId) {
        this.recommendedId = recommendedId;
    }

    public Date getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(Date recommendTime) {
        this.recommendTime = recommendTime;
    }
}
