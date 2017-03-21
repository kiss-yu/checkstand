package com.checkstand.ZFBUtil.service.impl;

import com.alipay.api.DefaultAlipayClient;
import com.checkstand.ZFBUtil.config.Configs;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Created by 11723 on 2017/3/18.
 */
class CreateZFBServiceimpl extends ZFBAlipayTradeServiceImpl{

    public static class ClientBuilder {
        private String gatewayUrl;
        private String appid;
        private String privateKey;
        private String format;
        private String charset;
        private String alipayPublicKey;
        private String signType;

        ClientBuilder build() {
            if (StringUtils.isEmpty(gatewayUrl)) {
                gatewayUrl = Configs.getOpenApiDomain(); // 与mcloudmonitor网关地址不同
            }
            if (StringUtils.isEmpty(appid)) {
                appid = Configs.getAppid();
            }
            if (StringUtils.isEmpty(privateKey)) {
                privateKey = Configs.getPrivateKey();
            }
            if (StringUtils.isEmpty(format)) {
                format = "json";
            }
            if (StringUtils.isEmpty(charset)) {
                charset = "utf-8";
            }
            if (StringUtils.isEmpty(alipayPublicKey)) {
                alipayPublicKey = Configs.getAlipayPublicKey();
            }
            if (StringUtils.isEmpty(signType)) {
                signType = Configs.getSignType();
            }
            return this;
        }

        public CreateZFBServiceimpl.ClientBuilder setAlipayPublicKey(String alipayPublicKey) {
            this.alipayPublicKey = alipayPublicKey;
            return this;
        }

        public CreateZFBServiceimpl.ClientBuilder setAppid(String appid) {
            this.appid = appid;
            return this;
        }

        public CreateZFBServiceimpl.ClientBuilder setCharset(String charset) {
            this.charset = charset;
            return this;
        }

        public CreateZFBServiceimpl.ClientBuilder setFormat(String format) {
            this.format = format;
            return this;
        }

        public CreateZFBServiceimpl.ClientBuilder setGatewayUrl(String gatewayUrl) {
            this.gatewayUrl = gatewayUrl;
            return this;
        }

        public CreateZFBServiceimpl.ClientBuilder setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public CreateZFBServiceimpl.ClientBuilder setSignType(String signType) {
            this.signType = signType;
            return this;
        }

        public String getAlipayPublicKey() {
            return alipayPublicKey;
        }

        public String getSignType() {
            return signType;
        }

        public String getAppid() {
            return appid;
        }

        public String getCharset() {
            return charset;
        }

        public String getFormat() {
            return format;
        }

        public String getGatewayUrl() {
            return gatewayUrl;
        }

        public String getPrivateKey() {
            return privateKey;
        }
    }
    public CreateZFBServiceimpl() {

    }
    static void createClient(){
        ClientBuilder builder = new CreateZFBServiceimpl.ClientBuilder().build();
        if (StringUtils.isEmpty(builder.getGatewayUrl())) {
            throw new NullPointerException("gatewayUrl should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getAppid())) {
            throw new NullPointerException("appid should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getPrivateKey())) {
            throw new NullPointerException("privateKey should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getFormat())) {
            throw new NullPointerException("format should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getCharset())) {
            throw new NullPointerException("charset should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getAlipayPublicKey())) {
            throw new NullPointerException("alipayPublicKey should not be NULL!");
        }
        if (StringUtils.isEmpty(builder.getSignType())) {
            throw new NullPointerException("signType should not be NULL!");
        }
        alipayClient = new DefaultAlipayClient(builder.getGatewayUrl(), builder.getAppid(), builder.getPrivateKey(),
                builder.getFormat(), builder.getCharset(), builder.getAlipayPublicKey(), builder.getSignType());
    }
}
