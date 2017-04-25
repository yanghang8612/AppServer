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

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "share_cycle", nullable = false)
    private String shareCycle;

    @Column(name = "recommend_profit")
    private String recommendProfit;

    @Column(name = "credit_card_profit")
    private String creditCardProfit;

    @Column(name = "loan_profit")
    private String loanProfit;

    @Column(name = "mall_profit")
    private String mallProfit;

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

    public String getShareCycle() {
        return shareCycle;
    }

    public void setShareCycle(String shareCycle) {
        this.shareCycle = shareCycle;
    }

    public String getRecommendProfit() {
        return recommendProfit;
    }

    public void setRecommendProfit(String recommendProfit) {
        this.recommendProfit = recommendProfit;
    }

    public String getCreditCardProfit() {
        return creditCardProfit;
    }

    public void setCreditCardProfit(String creditCardProfit) {
        this.creditCardProfit = creditCardProfit;
    }

    public String getLoanProfit() {
        return loanProfit;
    }

    public void setLoanProfit(String loanProfit) {
        this.loanProfit = loanProfit;
    }

    public String getMallProfit() {
        return mallProfit;
    }

    public void setMallProfit(String mallProfit) {
        this.mallProfit = mallProfit;
    }
}
