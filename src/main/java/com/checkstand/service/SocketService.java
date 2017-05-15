package com.checkstand.service;

import com.checkstand.Data.CustomerData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 11723 on 2017/3/17.
 */
@Service
public class SocketService {
    @Resource
    private ZFBService zfbService;
    @Resource
    private CustomerService customerService;

    public static boolean type = false;
    public String invoicing(){
        customerService.insert(CustomerData.one_customer);
        return zfbService.createQrCodePy();
    }

    public static void main(String[] args) {
        ZFBService zfbService = new ZFBService();
        zfbService.createQrCodePy();
    }

    public boolean getMsg(String out_trade_no){
       return zfbService.isOk(out_trade_no);
    }
}
