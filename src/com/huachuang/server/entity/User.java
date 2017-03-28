package com.huachuang.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Asuka on 2017/3/20.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long userId;

    private String userPhoneNumber;

    private String userPassword;

    private byte userType;

    private boolean isVip;

    private String invitationCode;

    private long superiorUserId;

    private Date registerTime;

    private Date lastLoginTime;

    private boolean certificationState;

    private boolean debitCardState;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public byte getUserType() {
        return userType;
    }

    public void setUserType(byte userType) {
        this.userType = userType;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public long getSuperiorUserId() {
        return superiorUserId;
    }

    public void setSuperiorUserId(long superiorUserId) {
        this.superiorUserId = superiorUserId;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isCertificationState() {
        return certificationState;
    }

    public void setCertificationState(boolean certificationState) {
        this.certificationState = certificationState;
    }

    public boolean isDebitCardState() {
        return debitCardState;
    }

    public void setDebitCardState(boolean debitCardState) {
        this.debitCardState = debitCardState;
    }
}
