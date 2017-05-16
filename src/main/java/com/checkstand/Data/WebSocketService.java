package com.checkstand.Data;

import com.checkstand.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by 11723 on 2017/5/16.
 */
@Component
public class WebSocketService {
    private static Session rootSession;
    @Autowired
    private CustomerService service;





    public Session getSession() {
        return rootSession;
    }

    public void setSession(Session session) {
        rootSession = session;

    }




    private void sendSales_statistics(){
        Calendar calendar = Calendar.getInstance();
        for(int i = 6;i > 0; i --) {
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
            Date today = calendar.getTime();

        }
    }

    private void sendMsg(String msg){
        try {
            rootSession.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
