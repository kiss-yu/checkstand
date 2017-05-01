package com.checkstand.Test;

import com.checkstand.ZFBUtil.config.Configs;
import com.checkstand.ZFBUtil.model.AlipayParameterModel;
import com.checkstand.ZFBUtil.service.ZFBclient;
import com.checkstand.ZFBUtil.service.impl.OrderProcessing.QueueManagement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * Created by 11723 on 2017/3/18.
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        Configs.init("zfbinfo.properties");
        AlipayParameterModel model = new AlipayParameterModel()
                .setOut_trade_no("_kiss2017031803090238_z")
                .setTotal_amount(88.88)
                .setSubject("耳机");
//        ZFBclient.createQrCodeAlipay(model,"E:");
//        ZFBAlipayTradeServiceImpl.undoAlipay(model.getOut_trade_no());
//        System.out.println(ZFBclient.alipayQuery("_kiss2017031803090237_z").getBody());
//        System.out.println(ZFBclient.alipayRefund(new RefundParameterModel().setOut_trade_no("_kiss2017031803090237_z")
//        .setRefund_amount(0.5)
//        .setRefund_reason("我想退")
//        .setOut_request_no("Kiss2")).getBody());
//        System.out.println(ZFBclient.refundQuery("_kiss2017031803090237_z","kiss1").getTotalAmount());
        ZFBclient.createQrCodeAlipay(model,"E:");


    }
}
