package com.checkstand.util;

/**
 * Created by 11723 on 2017/3/13.
 */

import com.checkstand.controller.SystemController;
import org.springframework.stereotype.Component;

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
     private Socket socket = null;
     private InputStreamReader inputStreamReader = null;
     private InputStream inputStream = null;
     private BufferedReader bufferedReader = null;
     private OutputStream outputStream = null;
     private PrintWriter  pWriter = null;
     private ServerSocket serverSocket ;
     private int type = 0;
     void start() throws IOException {
         long time = 155;
        serverSocket = new ServerSocket(8888);
        try {
            System.out.println("socket start");
            while(true){
                if (type == 0) {
                    time = new Date().getTime();
                    showConnect();
                }else {
                    if (new Date().getTime() > (time + 3000)) {
                        type = 0;
                        continue;
                    }
                    sendMsg();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }
    public  void  showConnect() throws IOException {
        socket = serverSocket.accept();
        InetAddress address =socket.getInetAddress();
        System.out.println("IP:"+address);
        inputStream = socket.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader= new BufferedReader(inputStreamReader);
        String string = null;
        StringBuffer stringBuffer = new StringBuffer();
        while((string = bufferedReader.readLine()) != null){
            stringBuffer.append(string);
        }
        System.out.println("商品id==" + stringBuffer.toString());
        socket.shutdownInput();
        inputStream.close();
        inputStreamReader.close();
        bufferedReader.close();
        type = 1;
    }
    public void sendMsg() throws IOException {
        socket = serverSocket.accept();
        outputStream = socket.getOutputStream();
        pWriter = new PrintWriter(outputStream);
        pWriter.write("#"+ SystemController.prace+"@");
        pWriter.flush();
        pWriter.close();
        outputStream.close();
        type = 0;
        System.out.println("send succeed");
    }
}
