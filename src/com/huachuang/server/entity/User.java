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
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long userId;

    @Column(name = "user_phone_number", length = 11, nullable = false, unique = true)
    private String userPhoneNumber;

    @Column(name = "user_password", length = 16, nullable = false)
    private String userPassword;

    @Column(name = "user_type")
    private byte userType;

    @Column(name = "is_vip")
    private boolean isVip;

    @Column(name = "invitation_code", length = 6, unique = true)
    private String invitationCode;

    @Column(name = "superior_user_id")
    private long superiorUserId;

    @Column(name = "register_time")
    private Date registerTime;

    @Column(name = "last_login_time")
    private Date lastLoginTime;

    @Column(name = "certification_state")
    private boolean certificationState;

    @Column(name = "debit_card_state")
    private boolean debitCardState;

    @Column(name = "header_state")
    private boolean headerState;

    public User(){}

    public User(String userPhoneNumber, String userPassword, String invitationCode, long superiorUserId) {
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.invitationCode = invitationCode;
        this.superiorUserId = superiorUserId;
    }

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

    public boolean isHeaderState() {
        return headerState;
    }

    public void setHeaderState(boolean headerState) {
        this.headerState = headerState;
    }
}
