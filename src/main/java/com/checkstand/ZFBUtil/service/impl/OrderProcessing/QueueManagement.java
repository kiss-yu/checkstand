package com.checkstand.ZFBUtil.service.impl.OrderProcessing;

import com.checkstand.ZFBUtil.model.AlipayParameterModel;
import com.checkstand.ZFBUtil.model.RefundParameterModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by 11723 on 2017/3/19.
 */
public class QueueManagement implements AlipayQueue{
    private static Log log = LogFactory.getLog(QueueManagement.class);
    public static void QrCodeayOrderIntoQueue(AlipayParameterModel order) throws Exception{
        QrCodeWaitePayQueue.pay_order.offer(order,10, TimeUnit.SECONDS);
        log.info("qrcode_queue in to success");
    }
    public static void refundOrderIntoQueue(RefundParameterModel order) throws Exception{
        RefundQueue.refund_alipay_order.offer(order,100, TimeUnit.SECONDS);
        log.info("refund in to success");
    }
    public static void queueManagementStart(){
        QrCodeWaitePayQueue queue1 = new QrCodeWaitePayQueue();
        RefundQueue queue2 = new RefundQueue();
        new Thread(queue1).start();
        new Thread(queue2).start();
        log.info("QrCodeWaitePayQueue start...");
        log.info("RefundQueue start...");
    }
}
