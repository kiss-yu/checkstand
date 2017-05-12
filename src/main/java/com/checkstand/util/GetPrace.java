package com.checkstand.util;

/**
 * Created by 11723 on 2017/3/13.
 */

import com.checkstand.Data.CustomerData;
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
                switch (content){
                    case "ok":setTlement();break;//返回二维码信息
                    case "clear":clearPrace();break;//重置价格
                    case "pay_type" :waitPay();break;//返回支付状态
                    default:statisticalPrace(content);break;//返回目前价格总和
                }
                inputStream.close();
                socket.close();
            }
            catch (Exception e) {
                socket.close();
            }
        }
    }
//    	if(OR_NOT_RECEIVE == 1)
//    {
//        if(strlen(RECEIVE_STRING) != 0)
//            RECEIVE_STRING[strlen(RECEIVE_STRING)] = RECEIVE_CHAR;
//        if(RECEIVE_CHAR == 0x23)
//            RECEIVE_STRING[strlen(RECEIVE_STRING)] = RECEIVE_CHAR;
//        if(RECEIVE_CHAR == 0x40)
//        {
//            RECEIVE_STRING[strlen(RECEIVE_STRING)] = 0x00;
//            OR_NOT_RECEIVE = 0;
//        }
//    }
    private void waitPay(){
        while (true){
            if (socketService.getMsg()){
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
        sendQrCodeString();
//        CustomerData.clearOneCustomer();
//        clearPrace();
    }
    private void sendQrCodeString(){
        System.out.println("开始发送支付宝支付请求···");
        String qr_code_string = socketService.invoicing();
        System.out.println("获取二维码数据成功。开始解析二维码数据");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("解析成功");
        try {
            System.out.println(Qr_codeUtil.qr_code());
        } catch (IOException e) {
//            e.printStackTrace();
        }
//        sendData("#" + qr_code_string + "@");
    }
    private void clearPrace(){
        CustomerData.clearOneCustomer();
        endPrace = 0;
        sendData("#" + endPrace + "@");
    }

    private void statisticalPrace(String id) {
        GoodsModel model = service.selectByGoodsId(id);
        endPrace += model.getGoodsPrace();
        CustomerData.insertGoods(model);
        System.out.println("商品id==" + id);
        System.out.println("商品名：" + model.getGoodsDescribe());
        System.out.println("商品价格：" + model.getGoodsPrace());
        sendData("#" + endPrace + "@");
        setTlement();
    }

    private void sendData(String content) {
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
