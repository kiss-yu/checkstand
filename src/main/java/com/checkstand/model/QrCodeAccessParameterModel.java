package com.checkstand.model;

import com.alipay.api.domain.GoodsDetail;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11723 on 2017/3/17.
 */
@Component
public class QrCodeAccessParameterModel {
    //商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
    //（必填）
    // (64)
    private String out_trade_no;
    //卖家支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
    // (28)
    private String seller_id;
    //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果同时传入了【打折金额】，
    // 【不可打折金额】，【订单总金额】三者，则必须满足如下条件：【订单总金额】=【打折金额】+【不可打折金额】
    //（必填）
    // (11)
    private String total_amount;
    //可打折金额. 参与优惠计算的金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果该值未传入，
    // 但传入了【订单总金额】，【不可打折金额】则该值默认为【订单总金额】-【不可打折金额】
    // (11)
    private String discountable_amount;
    //不可打折金额. 不参与优惠计算的金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果该值未传入，
    // 但传入了【订单总金额】,【打折金额】，则该值默认为【订单总金额】-【打折金额】
    //(11)
    private String undiscountable_amount;
    //买家支付宝账号
    // (100)
    private String buyer_logon_id;
    //订单标题
    //（必填）
    // (256)
    private String subject;
    //对交易或商品的描述
    // (128)
    private String body;
}
