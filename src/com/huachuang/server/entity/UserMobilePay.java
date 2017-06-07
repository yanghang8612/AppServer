package com.huachuang.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Asuka on 2017/6/6.
 */

@Entity
@Table(name = "user_mobile_pay")
public class UserMobilePay {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private long userId;

    @Column(name = "mid", nullable = false)
    private String mid;

    @Column(name = "key", nullable = false)
    private String key;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
