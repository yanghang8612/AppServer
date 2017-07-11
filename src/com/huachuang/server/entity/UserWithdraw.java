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
@Table(name = "user_withdraw")
public class UserWithdraw {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "bank_name", nullable = false)
    private String bankName;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "commit_time")
    private Date commitTime;

    @Column(name = "state")
    private byte state;

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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}
