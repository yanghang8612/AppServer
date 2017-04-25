package com.huachuang.server.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Asuka on 2017/3/29.
 */

@Entity
@DynamicInsert
@Table(name = "recommend_list")
public class RecommendList {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "type", nullable = false)
    private byte type;

    @Column(name = "recommender_id", nullable = false)
    private long recommenderId;

    @Column(name = "recommended_id", nullable = false)
    private long recommendedId;

    @Column(name = "recommend_time")
    private Date recommendTime;

    public RecommendList() {}

    public RecommendList(long recommenderId, long recommendedId) {
        this.recommenderId = recommenderId;
        this.recommendedId = recommendedId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
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
