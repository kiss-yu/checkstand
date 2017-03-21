package com.checkstand.service;

import com.checkstand.Data.CustomerData;
import com.checkstand.ZFBUtil.config.Configs;
import com.checkstand.ZFBUtil.model.AlipayParameterModel;
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

    @Resource
    private CustomerData customerData;

    public String createQrCodePy(){
        AlipayParameterModel alipay_parameter_model = new AlipayParameterModel();
        for (GoodsModel good:customerData.getOne_customer().getGoodsModels()){
            alipay_parameter_model.setTotal_amount(Double.valueOf(alipay_parameter_model.getTotal_amount()) + good.getGoodsPrace());
            goodsService.soldGoods(good);
        }
        alipay_parameter_model.setSubject(customerData.getOne_customer().getGoodsModels().get(0).getGoodsDescribe() == null
                ? "kiss" + System.currentTimeMillis()
                : customerData.getOne_customer().getGoodsModels().get(0).getGoodsDescribe());
        String out_trade_no = "kiss_" + System.currentTimeMillis();
        alipay_parameter_model.setOut_trade_no(out_trade_no);
        return out_trade_no;
    }
    public String getQrCodeFilePath(String out_trade_no){
        return Configs.getQr_code_filepath() + out_trade_no;
    }

}
