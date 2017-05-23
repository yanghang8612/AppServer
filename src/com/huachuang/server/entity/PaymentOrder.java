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
@Table(name = "payment_order")
public class PaymentOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "order_transaction", nullable = false)
    private String orderTransaction;

    @Column(name = "order_type", nullable = false)
    private byte orderType;

    @Column(name = "order_create_time")
    private Date orderCreateTime;

    @Column(name = "order_amount", nullable = false)
    private double orderAmount;

    @Column(name = "order_state")
    private byte orderState;

    @Column(name = "payment_id", nullable = false)
    private String paymentID;

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

    public String getOrderTransaction() {
        return orderTransaction;
    }

    public void setOrderTransaction(String orderTransaction) {
        this.orderTransaction = orderTransaction;
    }

    public byte getOrderType() {
        return orderType;
    }

    public void setOrderType(byte orderType) {
        this.orderType = orderType;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public byte getOrderState() {
        return orderState;
    }

    public void setOrderState(byte orderState) {
        this.orderState = orderState;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }
}
