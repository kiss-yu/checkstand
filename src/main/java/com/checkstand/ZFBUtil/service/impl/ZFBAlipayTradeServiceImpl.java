package com.checkstand.ZFBUtil.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;

import com.checkstand.ZFBUtil.config.Configs;
import com.checkstand.ZFBUtil.model.AlipayParameterModel;
import com.checkstand.ZFBUtil.model.Order;
import com.checkstand.ZFBUtil.model.RefundParameterModel;
import com.checkstand.ZFBUtil.service.ZFBclient;
import com.checkstand.ZFBUtil.util.ZxingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

public class ZFBAlipayTradeServiceImpl{
    static  AlipayClient alipayClient;
    private static Log log = LogFactory.getLog(ZFBAlipayTradeServiceImpl.class);
    static{
//        Configs.init("zfbinfo.properties");//讀取配置文件
        CreateZFBServiceimpl.createClient();
    }

    /*
    * 传入参数model生成支付宝二维码  二维码保存到QrCodeFilePath路径下
    * */
    public static AlipayTradePrecreateResponse createQrCodeAlipay(Order parameterModel, String QrCodeFilePath) throws AlipayApiException {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(parameterModel.toJson());
//        request.setNotifyUrl(Configs.getNotify_url());
        log.info("json = " + request.getBizContent());
        AlipayTradePrecreateResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            // 需要修改为运行机器上的路径
            String filePath = String.format(QrCodeFilePath + "\\%s.png", response.getOutTradeNo());
            ZxingUtil.getQRCodeImge(response.getQrCode(), 200, filePath);
            log.info("create Qr-Code succeed \n filepath:" + filePath);
            log.info(response.getBody());
            return response;
        }
        log.info("create Qr-Code fail\nmessage:" + response.getMsg());
        return null;
    }
    /*
    * 传入商户订单号进行订单撤销
    * */
    public static AlipayTradeCancelResponse undoAlipay(String out_trade_no) throws AlipayApiException {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"" +
                "}");
        log.info("json = " + request.getBizContent());
        AlipayTradeCancelResponse response = alipayClient.execute(request);
        if (response.isSuccess()){
            log.info("undo trading succeed");
            log.info(response.getBody());
            return response;
        }
        return null;
    }
    /*
    * 传入商户订单号进行订单查询 返回AlipayTradeQueryResponse类
    * */
    public static AlipayTradeQueryResponse alipayQuery(String out_trade_no) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"" +
                "}");
        log.info("json = " + request.getBizContent());
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()){
            log.info("undo trading succeed");
            return response;
        }
        return null;
    }
    /*
    * 传入商户订单号进行退款
    * */
    public static AlipayTradeRefundResponse alipayRefund(Order parameterModel) throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(parameterModel.toJson());
        log.info("json = " + request.getBizContent());
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if (response.isSuccess()){
            log.info("alipay refund succeed");
            return response;
        }
        return null;
    }
    /*
    *
    * 退款查詢接口
    * */
    public static AlipayTradeFastpayRefundQueryResponse refundQuery(String out_trade_no,String out_request_no) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"out_request_no\":\"" + out_request_no + "\"" +
                "}");
        log.info("json = " + request.getBizContent());
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        if (response.isSuccess()){
            log.info("query alipay success");
            return response;
        }
        return null;
    }
    /*
    *
    * 关闭交易接口
    * */
    public static AlipayTradeCloseResponse closeAlipay(String ... parameters) throws AlipayApiException {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        if (parameters.length == 1)
            request.setBizContent("{" +
                    "\"out_trade_no\":\"" + parameters[0] + "\"" +
                    "}");
        else request.setBizContent("{" +
                "\"out_trade_no\":\"" + parameters[0] + "\"," +
                "\"operator_id\":\"" + parameters[1] + "\"" +
                        "}");
        log.info("json = " + request.getBizContent());
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        if (response.isSuccess()){
            log.info("close alipay success");
            return response;
        }
        return null;
    }

    /*
     * 统一交易收单结算接口
     *
     * */
}
