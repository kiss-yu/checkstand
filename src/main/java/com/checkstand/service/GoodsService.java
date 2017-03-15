package com.checkstand.service;

import com.checkstand.dao.GoodsDao;
import com.checkstand.model.GoodsModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 11723 on 2017/3/14.
 */
@Service
public class GoodsService {
    @Resource
    private GoodsDao dao;
    public void insert(GoodsModel model){
        dao.insert(model);
    }
    public void delete(GoodsModel model){
        dao.deleteByGoodsId(model.getGoodsId());
    }
    public void updata(GoodsModel model){
        dao.updata(model);
    }
    public GoodsModel selectByGoodsId(String goodsId){
        return dao.selectByGoodsId(goodsId);
    }
}
