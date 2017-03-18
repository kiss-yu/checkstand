package com.checkstand.controller;

import com.checkstand.ZFBUtil.service.impl.ZFBAlipayTradeServiceImpl;
import com.checkstand.model.GoodsModel;
import com.checkstand.service.GoodsService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Created by 11723 on 2017/3/13.
 */
@Controller
@RequestMapping("/system")
public class SystemController {
    @Resource
    private GoodsService service;
    @Resource
    private ZFBAlipayTradeServiceImpl entrance;
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public void save(
            @RequestParam(value = "goodsId",defaultValue = "666")String goodsId,
            @RequestParam(value = "goodsPrace",defaultValue = "0")Float goodsPrace
    ){
        GoodsModel model = new GoodsModel(goodsId,goodsPrace);
        service.insert(model);
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void delete(
            @RequestParam(value = "goodsId",defaultValue = "666")String goodsId
    ){
                service.delete(service.selectByGoodsId(goodsId));
    }

    @ResponseBody
    @RequestMapping(value = "/updata",method = RequestMethod.PUT)
    public void updata(
            @RequestParam(value = "goodsId",defaultValue = "666")String goodsId,
            @RequestParam(value = "doodsPrace",defaultValue = "0")Float goodsPrace
    ){
        GoodsModel model = new GoodsModel(goodsId,goodsPrace);
        service.updata(model);
    }

    @ResponseBody
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public Map<Object,Object> find(
            @RequestParam(value = "goodsId",defaultValue = "666")String goodsId
    ){
        Map<Object,Object> map = new HashedMap();
        map.put("model",service.selectByGoodsId(goodsId));
        return map;
    }
}
