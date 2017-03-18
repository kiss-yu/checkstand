package com.checkstand.Test;

import com.checkstand.ZFBUtil.model.CreateAlipayParameterModel;
import com.checkstand.ZFBUtil.model.RefundParameterModel;
import com.checkstand.ZFBUtil.service.ZFBService;
import com.checkstand.ZFBUtil.service.impl.ZFBAlipayTradeServiceImpl;

/**
 * Created by 11723 on 2017/3/18.
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        CreateAlipayParameterModel model = new CreateAlipayParameterModel()
                .setOut_trade_no("_kiss2017031803090237_z")
                .setTotal_amount(88.88)
                .setSubject("耳机");
//        ZFBService.createQrCodeAlipay(model,"E:");
//        ZFBAlipayTradeServiceImpl.undoAlipay(model.getOut_trade_no());
//        System.out.println(ZFBService.alipayQuery("_kiss2017031803090237_z").getBody());
//        System.out.println(ZFBService.alipayRefund(new RefundParameterModel().setOut_trade_no("_kiss2017031803090237_z")
//        .setRefund_amount(0.5)
//        .setRefund_reason("我想退")
//        .setOut_request_no("Kiss2")).getBody());
//        System.out.println(ZFBService.refundQuery("_kiss2017031803090237_z","kiss1").getTotalAmount());
    }
}
