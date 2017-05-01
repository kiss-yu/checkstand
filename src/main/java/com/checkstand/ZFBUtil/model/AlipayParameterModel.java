package com.checkstand.ZFBUtil.model;

import com.alipay.api.domain.ExtendParams;
import com.alipay.api.domain.GoodsDetail;
import com.alipay.api.domain.SubMerchant;
import com.checkstand.ZFBUtil.config.Configs;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 11723 on 2017/3/17.
 */

/*
*
* 支付宝接口接入参数集成类
* 暂未支持花呗分期
*
* */
public class AlipayParameterModel implements Order{

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
    //订单包含的商品列表信息.Json格式. 其它说明详见：“商品明细说明”
    private List<GoodsDetail> goods_detail;
    //商户操作员编号
    //(28)
    private String operator_id;
    //商户门店编号
    //(32)
    private String store_id;
    //商户机具终端编号
    //(32)
    private String terminal_id;
    //业务扩展参数
    private ExtendParams extend_params;
    //该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天
    // （1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点
    private String timeout_express = String.valueOf(Configs.getAlipay_out_time()) + "d";
    //二级商户信息,当前只对特殊银行机构特定场景下使用此字段
    private SubMerchant sub_merchant;
    //支付宝店铺的门店ID
    private String alipay_store_id;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public AlipayParameterModel setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
        return this;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public AlipayParameterModel setSeller_id(String seller_id) {
        this.seller_id = seller_id;
        return this;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public AlipayParameterModel setTotal_amount(double total_amount) {
        this.total_amount = String.valueOf(total_amount);
        return this;
    }

    public String getDiscountable_amount() {
        return discountable_amount;
    }

    public AlipayParameterModel setDiscountable_amount(double discountable_amount) {
        this.discountable_amount = String.valueOf(discountable_amount);
        this.undiscountable_amount = String.valueOf(new Float(this.total_amount) - new Float(this.discountable_amount));
        return this;
    }

    public String getUndiscountable_amount() {
        return undiscountable_amount;
    }

    public AlipayParameterModel setUndiscountable_amount(double undiscountable_amount) {
        this.undiscountable_amount = String.valueOf(undiscountable_amount);
        this.discountable_amount = String.valueOf(new Float(this.total_amount) - new Float(this.undiscountable_amount));
        return this;
    }

    public String getBuyer_logon_id() {
        return buyer_logon_id;
    }

    public AlipayParameterModel setBuyer_logon_id(String buyer_logon_id) {
        this.buyer_logon_id = buyer_logon_id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public AlipayParameterModel setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getBody() {
        return body;
    }

    public AlipayParameterModel setBody(String body) {
        this.body = body;
        return this;
    }

    public List<GoodsDetail> getGoods_detail() {
        return goods_detail;
    }

    public AlipayParameterModel setGoods_detail(List<GoodsDetail> goods_detail) {
        this.goods_detail = goods_detail;
        double price = 0;
        for (GoodsDetail detail:goods_detail)
            price += Double.valueOf(detail.getPrice()) * detail.getQuantity();
        this.total_amount = String.valueOf(price);
        return this;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public AlipayParameterModel setOperator_id(String operator_id) {
        this.operator_id = operator_id;
        return this;
    }

    public String getStore_id() {
        return store_id;
    }

    public AlipayParameterModel setStore_id(String store_id) {
        this.store_id = store_id;
        return this;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public AlipayParameterModel setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
        return this;
    }

    public ExtendParams getExtend_params() {
        return extend_params;
    }

    public AlipayParameterModel setExtend_params(ExtendParams extend_params) {
        this.extend_params = extend_params;
        return this;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public AlipayParameterModel setTimeout_express(int timeout_express) {
        this.timeout_express = String.valueOf(timeout_express) + "m";
        return this;
    }

    public SubMerchant getSub_merchant() {
        return sub_merchant;
    }

    public AlipayParameterModel setSub_merchant(SubMerchant sub_merchant) {
        this.sub_merchant = sub_merchant;
        return this;
    }

    public String getAlipay_store_id() {
        return alipay_store_id;
    }

    public AlipayParameterModel setAlipay_store_id(String alipay_store_id) {
        this.alipay_store_id = alipay_store_id;
        return this;
    }
    @Override
    public String toJson(){
//        String json = "{";
//        json += getOut_trade_no() != null ? "out_trade_no:\"" + getOut_trade_no() +"\"," :"";
//        json += getSeller_id() != null ? "seller_id:\"" + getSeller_id() + "\"," : "";
//        json += getTotal_amount() != null ? "total_amount:\"" + getTotal_amount() + "\"," : "";
//        json += getDiscountable_amount() != null ? "discountable_amount:\"" + getDiscountable_amount() + "\"," : "";
//        json += getUndiscountable_amount() != null ? "undiscountable_amou:\"" + getUndiscountable_amount() + "\"," : "";
//        json += getBuyer_logon_id() != null ? "buyer_logon_id:\"" + getBuyer_logon_id() + "\"," : "";
//        json += getSubject() != null ? "body:\"" + getBody() + "\"," : "";
//        json += goodsToJson(getGoods_detail());
//
//        json +="}";
//        return json.replaceFirst(",[\\s]*}","}");
//        String json = "";
//        String[] jsons = JSONObject.fromObject(this).toString().split(",");
//        for (String str:jsons){
//            str = str.replaceFirst("[}|{]","");
//            if (str.split(":")[1].equals("\"\"") || str.split(":")[1].equals("null") || str.split(":")[1].equals("[]"))
//                continue;
//            json += str + ",";
//        }
        return JSONObject.fromObject(this).toString();
    }
//    private String goodsToJson(List<GoodsDetail> list){
//        if (list == null)
//            return "";
//        String json = "[";
//        for (GoodsDetail goods:list){
//            json += "{";
//            json += "goods_id:\"" + goods.getGoodsId() + "\",";
//            json += goods.getAlipayGoodsId() != null ? "alipay_goods_id:\"" + goods.getAlipayGoodsId() + "\"," : "";
//            json += goods.getGoodsName();
//            json += goods.getQuantity().toString();
//            json += goods.getPrice();
//            json += json.replaceFirst(",[\\s]*}","}");
//        }
//        return json;
//    }
}
