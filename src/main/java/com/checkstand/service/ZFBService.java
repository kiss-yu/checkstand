package com.checkstand.service;

import com.alipay.api.AlipayApiException;
import com.checkstand.Data.CustomerData;
import com.checkstand.ZFBUtil.config.Configs;
import com.checkstand.ZFBUtil.model.AlipayParameterModel;
import com.checkstand.ZFBUtil.service.ZFBclient;
import com.checkstand.model.GoodsModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 11723 on 2017/3/17.
 */
@Service
public class ZFBService {
    @Resource
    private GoodsService goodsService;


    public String createQrCodePy(){
        AlipayParameterModel alipay_parameter_model = new AlipayParameterModel();
        for (GoodsModel good:CustomerData.one_customer.getGoodsModels()){
            alipay_parameter_model.setTotal_amount(
                    Float.valueOf(alipay_parameter_model.getTotal_amount() == null ? "0" : alipay_parameter_model.getTotal_amount())
                    + good.getGoodsPrace());
            goodsService.soldGoods(good);
        }
        alipay_parameter_model.setSubject(CustomerData.one_customer.getGoodsModels().get(0).getGoodsDescribe() == null
                ? "kiss" + System.currentTimeMillis()
                : CustomerData.one_customer.getGoodsModels().get(0).getGoodsDescribe());
        String out_trade_no = "kiss_" + System.currentTimeMillis();
        alipay_parameter_model.setOut_trade_no(out_trade_no);
        try {
            ZFBclient.createQrCodeAlipay(alipay_parameter_model,Configs.getQr_code_filepath());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return out_trade_no;
    }
    public String getQrCodeFilePath(String out_trade_no){
        return Configs.getQr_code_filepath() + out_trade_no;
    }

}
