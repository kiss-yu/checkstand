package com.checkstand.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by 11723 on 2017/3/14.
 */
@Entity
@Table(name = "goods")
public class GoodsModel implements Serializable {
//    private Integer id;
    private String goodsId;//商品id
    private String goodsDescribe;//商品描述
    private Float goodsPrace;//商品价格
    private Integer inventory;//商品的库存
    private Integer sold_number;//商品的售出量
    private List<OneCustomerModel> customerModels;
    public GoodsModel(){

    }
    public GoodsModel(String goodsId,Float goodsPrace){
        this.goodsId = goodsId;
        this.goodsPrace = goodsPrace;
    }

    public GoodsModel(String goodsId,Float goodsPrace,String goodsDescribe,Integer inventory){
        this.goodsId = goodsId;
        this.goodsPrace = goodsPrace;
        this.goodsDescribe = goodsDescribe;
        this.inventory = inventory;
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "assigned")
    @Column(length = 30)
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

    public void setGoodsPrace(Float goodsPrace) {
        this.goodsPrace = goodsPrace;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Column(columnDefinition="INT default 0")
    public Integer getSold_number() {
        return sold_number;
    }

    public void setSold_number(Integer sold_number) {
        this.sold_number = sold_number;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="goods_to_customer",
            joinColumns={@JoinColumn(name="goodsId",columnDefinition = "varchar(30)")},
            inverseJoinColumns={@JoinColumn(name="customer_id",columnDefinition = "varchar(30)")} )
    public List<OneCustomerModel> getCustomerModels() {
        return customerModels;
    }

    public void setCustomerModels(List<OneCustomerModel> customerModels) {
        this.customerModels = customerModels;
    }
}
