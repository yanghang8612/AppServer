package com.huachuang.server.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Asuka on 2017/3/20.
 */

@Entity
@DynamicInsert
@Table(name = "user_abandoned")
public class UserAbandoned {

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_phone_number", length = 11, nullable = false)
    private String userPhoneNumber;

    @Column(name = "user_password", length = 16, nullable = false)
    private String userPassword;

    @Column(name = "user_type")
    private byte userType;

    @Column(name = "is_vip")
    private boolean isVip;

    @Column(name = "invitation_code", length = 6)
    private String invitationCode;

    @Column(name = "superior_user_id")
    private long superiorUserId;

    @Column(name = "agent_id")
    private long agentID;

    @Column(name = "user_level")
    private int userLevel;

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

    @Column(name = "mobile_pay_state")
    private byte mobilePayState;

    public UserAbandoned(){}

    public UserAbandoned(User user){
        this.userId = user.getUserId();
        this.userPhoneNumber = user.getUserPhoneNumber();
        this.userPassword = user.getUserPassword();
        this.userType = user.getUserType();
        this.isVip = user.isVip();
        this.invitationCode = user.getInvitationCode();
        this.superiorUserId = user.getSuperiorUserId();
        this.registerTime = user.getRegisterTime();
        this.lastLoginTime = user.getLastLoginTime();
        this.certificationState = user.isCertificationState();
        this.debitCardState = user.isDebitCardState();
        this.headerState = user.isHeaderState();
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

    public long getAgentID() {
        return agentID;
    }

    public void setAgentID(long agentID) {
        this.agentID = agentID;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
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

    public byte getMobilePayState() {
        return mobilePayState;
    }

    public void setMobilePayState(byte mobilePayState) {
        this.mobilePayState = mobilePayState;
    }
}
