package com.checkstand.util;

/**
 * Created by 11723 on 2017/3/13.
 */

import com.checkstand.Data.CustomerData;
import com.checkstand.service.CustomerService;
import com.checkstand.service.SocketService;
import com.checkstand.service.ZFBService;
import com.checkstand.model.GoodsModel;
import com.checkstand.service.GoodsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class GetPrace {
    @Resource
    private GoodsService service;
    @Resource
    private SocketService socketService;

    private Socket socket = null;
    private float endPrace = 0;
    private String out_trade_no;
    public  void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("========socket start==========");
        while( (
                socket = serverSocket.accept()) != null){
            try{
                byte[] bs = new byte[20];
                String content = "";
                InetAddress address =socket.getInetAddress();
                System.out.println("IP:"+address);
                InputStream inputStream = socket.getInputStream();
                int size = inputStream.read(bs);
                for(int i = 0;i < size;i ++)
                    content += (char)bs[i];
                System.out.println(content);
                if(content.matches("ok_[\\d]{1,2}")){
                    sendQr_codeOrder(Integer.valueOf(content.replaceAll("ok_","")));
                }else {
                    switch (content) {
                        case "clear":
                            clearPrace();
                            break;//重置价格
                        case "pay_type":
                            waitPay();
                            break;//返回支付状态
                        default:
                            statisticalPrace(content);
                            break;//返回目前价格总和
                    }
                }
                inputStream.close();
                socket.close();
            }
            catch (Exception e) {
                socket.close();
            }
        }
    }
    private void waitPay(){
        while (true){
            if (socketService.getMsg(out_trade_no)){
                sendData("ok");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void setTlement(){
        sendZFBPay();
        CustomerData.clearOneCustomer();
        clearPrace();
    }
    private void sendZFBPay(){
        System.out.println("开始发送支付宝支付请求···");
        out_trade_no = socketService.invoicing();
        System.out.println("获取二维码数据成功。开始解析二维码数据");
        try {
            Qr_codeUtil.qr_code(out_trade_no);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void clearPrace(){
        CustomerData.clearOneCustomer();
        endPrace = 0;
//        sendData("#" + endPrace + "@");
    }

    private void statisticalPrace(String id) {
        GoodsModel model = service.selectByGoodsId(id);
        endPrace += model.getGoodsPrace();
        CustomerData.insertGoods(model);
        sendData("#" + endPrace + "@");
    }


    private void sendQr_codeOrder(int order){
        if (order == 0){
            setTlement();
        }
        char ff = 0xff;
        char[] chars = (ff + Qr_codeUtil.getIndex(order) + ff).toCharArray();
        for (char _char:chars)
            System.out.print("0x"+Integer.toHexString(_char) + ", ");
        sendData(ff + Qr_codeUtil.getIndex(order) + ff);
    }



    private void sendData(String content) {
        System.out.println("send=" + content);
        try {
            PrintWriter pWriter = new PrintWriter(socket.getOutputStream());
            pWriter.write(content);
            pWriter.flush();
            pWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
