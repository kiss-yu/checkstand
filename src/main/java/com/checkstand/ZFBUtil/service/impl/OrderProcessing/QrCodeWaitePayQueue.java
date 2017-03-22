package com.checkstand.ZFBUtil.service.impl.OrderProcessing;

import com.checkstand.ZFBUtil.config.Configs;
import com.checkstand.ZFBUtil.model.Order;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 11723 on 2017/3/19.
 */
public class QrCodeWaitePayQueue implements AlipayQueue , Runnable{
    static final int QUEUE_SIZE = Configs.getPay_order_max();
    static final BlockingQueue<Order> pay_order = new ArrayBlockingQueue<Order>(QUEUE_SIZE);
    static final String qr_code_filepath = Configs.getQr_code_filepath();
    private Order model = null;
    @Override
    public void run() {

    }
}
