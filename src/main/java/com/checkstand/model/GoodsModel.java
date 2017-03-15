package com.checkstand.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 11723 on 2017/3/14.
 */
@Entity
@Table(name = "goods")
public class GoodsModel {
    private Integer id;
    private String goodsId;//商品id
    private String goodsDescribe;//商品描述
    private Float goodsPrace;//商品价格
    public GoodsModel(){

    }
    public GoodsModel(String goodsId,Float goodsPrace){
        this.goodsId = goodsId;
        this.goodsPrace = goodsPrace;
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "increment")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(unique = true,length = 30)
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    @Column(length = 500)
    public String getGoodsDescribe() {
        return goodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        this.goodsDescribe = goodsDescribe;
    }

    public float getGoodsPrace() {
        return goodsPrace;
    }

    public void setGoodsPrace(float goodsPrace) {
        this.goodsPrace = goodsPrace;
    }
}
