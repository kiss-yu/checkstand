package com.checkstand.service;


import com.checkstand.Data.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/WebSocket/message")
public class MessageWebSocket {

    @Autowired
    private WebSocketService service;

    @OnOpen
    public void onOpen(Session session) {
        service.setSession(session);
    }
    @OnClose
    public void onClose(Session session) {

    }

    @OnMessage
    public void onMessage(String message,Session session) {
    }

    @OnError
    public void onError(Session session, Throwable error) {
    }
}