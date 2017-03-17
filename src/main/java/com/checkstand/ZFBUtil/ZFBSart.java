package com.checkstand.ZFBUtil;

import com.checkstand.model.QrCodeAccessParameterModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 11723 on 2017/3/17.
 */
@Component
public class ZFBSart {
    @Resource
    private ZFBEntrance entrance;
    public void sart(QrCodeAccessParameterModel model) throws Exception{
        entrance.start();
    }
}
