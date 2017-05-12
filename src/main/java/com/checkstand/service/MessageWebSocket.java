package com.checkstand.service;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/WebSocket/message")
public class MessageWebSocket {

    @OnOpen
    public void onOpen(Session session) {
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