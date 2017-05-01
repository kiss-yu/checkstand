package com.checkstand.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

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
//    private int id;
    private String customer_id;
    private Date buy_time;
    private int buy_number;
    private List<GoodsModel> goodsModels = new ArrayList<>();


//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
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

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name="goods_to_customer",
            joinColumns={@JoinColumn(name="customer_id",columnDefinition = "varchar(30)")},
            inverseJoinColumns={@JoinColumn(name="goodsId",columnDefinition = "varchar(30)")} )
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
    public void setBuyMessage(){
        this.buy_time = new Date();
        this.buy_number = getGoodsModels().size();
    }
}