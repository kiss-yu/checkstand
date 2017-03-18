package com.checkstand.service;

import com.checkstand.ZFBUtil.service.impl.ZFBAlipayTradeServiceImpl;
import com.checkstand.ZFBUtil.model.CreateAlipayParameterModel;
import org.springframework.stereotype.Service;

/**
 * Created by 11723 on 2017/3/17.
 */
@Service
public class ZFBService {
    public boolean createQrCodePy(CreateAlipayParameterModel model){
        try {
            ZFBAlipayTradeServiceImpl.createQrCodeAlipay(model,"E:");
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
