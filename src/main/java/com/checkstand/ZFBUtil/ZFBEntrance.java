package com.checkstand.ZFBUtil;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.MonitorHeartbeatSynResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.SocketPermission;
import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Component
public class ZFBEntrance {
    private static String public_key;
    private static String private_key;
    private static String appid;
    private static String openapi_admin;
    static{
        Properties properties=new Properties();
        try {
            InputStream inputStream=ZFBEntrance.class.getClassLoader().getResourceAsStream("zfbinfo.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            System.out.println("读取配置文件错误");
        }
        openapi_admin = properties.getProperty("open_api_domain");
        public_key = properties.getProperty("alipay_public_key");
        private_key = properties.getProperty("private_key");
        appid = properties.getProperty("appid");
    }

    /*
    * 根据价格生成二维码
    * */
    public  void start() {
        AlipayClient alipayClient = new DefaultAlipayClient(openapi_admin,appid,private_key,"json","UTF-8",public_key,"RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010501001\"," +
                "    \"total_amount\":88.88," +
                "    \"subject\":\"Iphone6 16G\"" +
                "  }");
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println(response.getQrCode());

            // 需要修改为运行机器上的路径
            String filePath = String.format("E:\\qr-%s.png",
                    response.getOutTradeNo());
            ZxingUtil.getQRCodeImge(response.getQrCode(), 128, filePath);
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
