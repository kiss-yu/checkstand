package com.checkstand.service;

import com.checkstand.dao.GoodsDao;
import com.checkstand.model.GoodsModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.print.MultiDoc;

/**
 * Created by 11723 on 2017/3/14.
 */
@Service
public class GoodsService {
    @Resource
    private GoodsDao dao;

    public void insert(GoodsModel model,int ... add_number){
        GoodsModel ago_model = selectByGoodsId(model.getGoodsId());
        if (ago_model == null || add_number.length > 1){
            model.setInventory(model.getInventory());
            dao.insert(model);
        }else {
            settingInventory(ago_model,ago_model.getInventory() + add_number[0]);
        }
    }

    public void delete(GoodsModel model,int ... delete_number){
        GoodsModel ago_model = selectByGoodsId(model.getGoodsId());
        if (ago_model == null || delete_number.length > 1)
            return ;
        settingInventory(ago_model,delete_number.length == 0 ? ago_model.getInventory() - 1 : ago_model.getInventory() - delete_number[0]);
    }


    public void deleteALl(GoodsModel model){
        dao.deleteByGoodsId(model.getGoodsId());
    }

    /*
    *
    * 更新商品时  如果库存为0时不删除该商品
    * */
    public void updata(GoodsModel model){
//        if (model.getInventory() == 0)
//            delete(model);
//        else
            dao.updata(model);
    }

    public void soldGoods(GoodsModel model,int ... sold_number){
        GoodsModel ago_model = selectByGoodsId(model.getGoodsId());
        if (ago_model == null || sold_number.length > 1 ||(sold_number.length == 1 && sold_number[0] > ago_model.getInventory()))
            return ;
        settingSoldNumber(ago_model,sold_number.length == 0 ? ago_model.getSold_number() + 1:ago_model.getSold_number() - sold_number[0]);
        settingInventory(ago_model,sold_number.length == 0 ? ago_model.getInventory() - 1:ago_model.getInventory() - sold_number[0]);
    }

    private void settingInventory(GoodsModel model,int inventory){
        inventory = inventory < 0 ? 0 :inventory;
        model.setInventory(inventory);
        updata(model);
    }

    private void settingSoldNumber(GoodsModel model,int sold_number){
        sold_number = sold_number > model.getInventory() ? model.getInventory() : sold_number;
        model.setSold_number(sold_number);
        updata(model);
    }

    public GoodsModel selectByGoodsId(String goodsId){
        return dao.selectByGoodsId(goodsId);
    }

}
