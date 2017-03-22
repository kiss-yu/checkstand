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

    public String invoicing(){
        customerService.insert(CustomerData.one_customer);
        return zfbService.getQrCodeFilePath(zfbService.createQrCodePy());
    }
}
