package com.checkstand.util;

/**
 * Created by 11723 on 2017/3/13.
 */

import com.checkstand.Data.CustomerData;
import com.checkstand.service.SocketService;
import com.checkstand.service.ZFBService;
import com.checkstand.model.GoodsModel;
import com.checkstand.service.GoodsService;
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
    private float endPrace;
    public  void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("========socket start==========");
        while((socket = serverSocket.accept()) != null){
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
                    case "OK":setTlement();break;
                    case "clear":clearPrace();break;
                    default:statisticalPrace(content);break;
                }
                inputStream.close();
                socket.close();
            }
            catch (Exception e) {
                socket.close();
            }
        }
    }
    private void setTlement(){
        endPrace = 0;
        sendData("#" + endPrace + "@");
        sendQrCodeString();
        CustomerData.clearOneCustomer();
        clearPrace();
    }
    private void sendQrCodeString(){
        String qr_code_string = socketService.invoicing();
        sendData("#" + qr_code_string + "@");
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
        sendData("#" + endPrace + "@");

    }

    private void sendData(String content) {
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
            PrintWriter pWriter = new PrintWriter(outputStream);
            pWriter.write(content);
            pWriter.flush();
            pWriter.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
