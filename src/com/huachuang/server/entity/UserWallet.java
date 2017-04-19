package com.huachuang.server.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Asuka on 2017/4/19.
 */

@Entity
@DynamicInsert
@Table(name = "user_wallet")
public class UserWallet {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "wallet_balance")
    private String walletBalance;

    @Column(name = "wallet_coins")
    private String walletCoins;
}
