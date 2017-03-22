package com.checkstand.ZFBUtil.service.impl.OrderProcessing;

import com.alipay.api.AlipayApiException;
import com.checkstand.ZFBUtil.config.Configs;
import com.checkstand.ZFBUtil.model.Order;
import com.checkstand.ZFBUtil.service.ZFBclient;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by 11723 on 2017/3/19.
 */
public class RefundQueue implements AlipayQueue ,Runnable{
    static final int QUEUE_SIZE = Configs.getRefund_order_max();
    static final BlockingQueue<Order> refund_alipay_order = new ArrayBlockingQueue<Order>(QUEUE_SIZE);
    private Order model = null;
    @Override
    public void run() {
        try {
            model = refund_alipay_order.take();
            System.out.println(ZFBclient.alipayRefund(model));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AlipayApiException e1) {
//            refund_alipay_order.offer(model);
            e1.printStackTrace();
        }
    }
}
