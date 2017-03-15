package com.checkstand.util;

/**
 * Created by 11723 on 2017/3/13.
 */

import com.checkstand.controller.SystemController;
import com.checkstand.service.GoodsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

@Component
public class GetPrace {
    @Resource
    private GoodsService service;
    private Socket socket = null;
    public  void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("socket start");
        while((socket = serverSocket.accept()) != null){
            try{
                byte[] bs = new byte[20];
                String id = "";
                InetAddress address =socket.getInetAddress();
                System.out.println("IP:"+address);
                InputStream inputStream = socket.getInputStream();
                int size = inputStream.read(bs);
                for(int i = 0;i < size;i ++)
                    id += (char)bs[i];
                System.out.println("商品id==" + id);
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter pWriter = new PrintWriter(outputStream);
                pWriter.write("#" + service.selectByGoodsId(id).getGoodsPrace() + "@");
                pWriter.flush();
                pWriter.close();
                outputStream.close();
                inputStream.close();
                socket.close();
            }
            catch (Exception e) {
                socket.close();
            }
        }
    }
}
