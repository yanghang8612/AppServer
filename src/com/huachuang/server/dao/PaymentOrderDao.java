package com.huachuang.server.dao;

import com.huachuang.server.entity.PaymentOrder;

import java.util.List;

/**
 * Created by Asuka on 2017/4/17.
 */
public interface PaymentOrderDao {

    void create(PaymentOrder paymentOrder);

    void delete(PaymentOrder paymentOrder);

    void update(PaymentOrder paymentOrder);

    List<PaymentOrder> findAllOrders();

    List<PaymentOrder> findOrdersByID(long id);
}
