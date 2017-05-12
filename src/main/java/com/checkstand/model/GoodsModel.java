package com.checkstand.model;

import net.sf.json.JSONObject;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by 11723 on 2017/3/14.
 */
@Entity
@Table(name = "goods")
public class GoodsModel implements Serializable {
    private String goodsId;//商品id
    private String title;//商品标题
    private String goodsDescribe;//商品描述
    private float goodsPrace;//商品价格
    private int inventory;//商品的库存
    private int soldNumber;//售出数量
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(int soldNumber) {
        this.soldNumber = soldNumber;
    }

    public String toJson(){
        return JSONObject.fromObject(this).toString();
    }
}
