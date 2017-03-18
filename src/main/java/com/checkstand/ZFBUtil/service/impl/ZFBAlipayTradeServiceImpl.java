package com.checkstand.ZFBUtil.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.request.*;
import com.alipay.api.response.*;

import com.checkstand.ZFBUtil.config.Configs;
import com.checkstand.ZFBUtil.model.CreateAlipayParameterModel;
import com.checkstand.ZFBUtil.model.RefundParameterModel;
import com.checkstand.ZFBUtil.util.ZxingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class ZFBAlipayTradeServiceImpl{
    static  AlipayClient alipayClient;
    private static Log log = LogFactory.getLog(ZFBAlipayTradeServiceImpl.class);
    static{
        Configs.init("zfbinfo.properties");//讀取配置文件
        CreateZFBServiceimpl.createClient();
    }

    /*
    * 传入参数model生成支付宝二维码  二维码保存到QrCodeFilePath路径下
    * */
    public static AlipayTradePrecreateResponse createQrCodeAlipay(CreateAlipayParameterModel parameterModel, String QrCodeFilePath) throws Exception {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent(parameterModel.toJson());
        log.info("json = " + request.getBizContent());
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                // 需要修改为运行机器上的路径
                String filePath = String.format(QrCodeFilePath + "\\%s.png", response.getOutTradeNo());
                ZxingUtil.getQRCodeImge(response.getQrCode(), 128, filePath);
                log.info("create Qr-Code succeed \n filepath:" + filePath);
                System.out.println(response.getBody());
                return response;
            }
            log.info("create Qr-Code fail\nmessage:" + response.getMsg());
            throw new Exception("create Qr-Code Fail");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    * 传入商户订单号进行订单撤销
    * */
    public static AlipayTradeCancelResponse undoAlipay(String out_trade_no) throws Exception {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"" +
                "}");
        log.info("json = " + request.getBizContent());
        try {
            AlipayTradeCancelResponse response = alipayClient.execute(request);
            if (response.isSuccess()){
                log.info("undo trading succeed");
                log.info(response.getBody());
                return response;
            }
            throw new Exception("undo alipay fail");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    * 传入商户订单号进行订单查询 返回AlipayTradeQueryResponse类
    * */
    public static AlipayTradeQueryResponse alipayQuery(String out_trade_no) throws Exception {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"" +
                "}");
        log.info("json = " + request.getBizContent());
        try {
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()){
                log.info("undo trading succeed");
                return response;
            }
            throw new Exception("query alipay fail");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    * 传入商户订单号进行退款
    * */
    public static AlipayTradeRefundResponse alipayRefund(RefundParameterModel parameterModel) throws Exception {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(parameterModel.toJson());
        log.info("json = " + request.getBizContent());
        try {
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()){
                log.info("alipay refund succeed");
                return response;
            }
            throw new Exception("refund fail");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    *
    * 退款查詢接口
    * */
    public static AlipayTradeFastpayRefundQueryResponse refundQuery(String out_trade_no,String out_request_no) throws Exception {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                "\"out_request_no\":\"" + out_request_no + "\"" +
                "}");
        log.info("json = " + request.getBizContent());
        try {
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()){
                log.info("query alipay success");
                return response;
            }
            throw new Exception("query fail");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    *
    * 关闭交易接口
    * */
    public static AlipayTradeCloseResponse closeAlipay(String ... parameters) throws Exception {
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
        try {
            AlipayTradeCloseResponse response = alipayClient.execute(request);
            if (response.isSuccess()){
                log.info("close alipay success");
                return response;
            }
            throw new Exception("close fail");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 统一交易收单结算接口
     *
     * */
}
