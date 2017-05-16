package com.checkstand.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 11723 on 2017/3/19.
 */
@Entity
@Table(name = "customer")
public class OneCustomerModel implements Serializable {
    private String customer_id;//买家id
    private Date buy_time;//购买时间
    private int buy_number;//购买商品数量
    private float account;//消费金额
    private List<GoodsModel> goodsModels = new ArrayList<>();//购买了哪些商品

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "assigned")
    @Column(length = 30)
    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    @ManyToMany
    @JoinTable(name = "customer_goods",joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "goodsId")})
    public List<GoodsModel> getGoodsModels() {
        return goodsModels;
    }

    public void setGoodsModels(List<GoodsModel> goodsModels) {
        this.goodsModels = goodsModels;
    }

    public Date getBuy_time() {
        return buy_time;
    }

    public void setBuy_time(Date buy_time) {
        this.buy_time = buy_time;
    }

    public int getBuy_number() {
        return buy_number;
    }

    public void setBuy_number(int buy_number) {
        this.buy_number = buy_number;
    }

    public float getAccount() {
        return account;
    }

    public void setAccount(float account) {
        this.account = account;
    }

    public void setBuyMessage(){
        this.buy_time = new Date();
        this.buy_number = getGoodsModels().size();
    }
}