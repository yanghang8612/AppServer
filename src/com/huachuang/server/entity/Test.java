package com.huachuang.server.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Asuka on 2017/3/20.
 */

@Entity
@DynamicInsert
@Table(name = "test")
public class Test {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "current_time")
    private Date currentTime;

    @Column(name = "random_number")
    private long randomNumber;

    public Test(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public long getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(long randomNumber) {
        this.randomNumber = randomNumber;
    }
}
