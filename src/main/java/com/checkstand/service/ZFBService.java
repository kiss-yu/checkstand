package com.checkstand.service;

import com.checkstand.ZFBUtil.ZFBSart;
import com.checkstand.model.QrCodeAccessParameterModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 11723 on 2017/3/17.
 */
@Service
public class ZFBService {
    @Resource
    private ZFBSart zfbSart;
    public boolean createQrCodePy(QrCodeAccessParameterModel model){
        try {
            zfbSart.sart(model);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
