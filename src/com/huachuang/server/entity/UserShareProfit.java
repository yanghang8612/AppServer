package com.huachuang.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Asuka on 2017/3/29.
 */

@Entity
@Table(name = "user_share_profit")
public class UserShareProfit {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "share_cycle")
    private String shareCycle;

    @Column(name = "recommend_profit")
    private String recommendProfit;

    @Column(name = "credit_card_profit")
    private String creditCardProfit;

    @Column(name = "loan_profit")
    private String loanProfit;

    @Column(name = "mall_profit")
    private String mallProfit;

}
