package com.huachuang.server.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Asuka on 2017/5/4.
 */

@Entity
@DynamicInsert
@Table(name = "apply_credit_card")
public class ApplyCreditCard {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "apply_bank", nullable = false)
    private byte applyBank;

    @Column(name = "apply_user_name", nullable = false)
    private String applyUserName;

    @Column(name = "apply_user_phone_number", nullable = false)
    private String applyUserPhoneNumber;

    @Column(name = "apply_company", nullable = false)
    private String applyUserCompany;

    @Column(name = "apply_time")
    private Date applyTime;

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

    public byte getApplyBank() {
        return applyBank;
    }

    public void setApplyBank(byte applyBank) {
        this.applyBank = applyBank;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getApplyUserPhoneNumber() {
        return applyUserPhoneNumber;
    }

    public void setApplyUserPhoneNumber(String applyUserPhoneNumber) {
        this.applyUserPhoneNumber = applyUserPhoneNumber;
    }

    public String getApplyUserCompany() {
        return applyUserCompany;
    }

    public void setApplyUserCompany(String applyUserCompany) {
        this.applyUserCompany = applyUserCompany;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
}
