package com.checkstand.service;


import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
public class MessageWebSocket  implements WebSocketHandler {

    @Resource
    private WebSocketService service;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("afterConnectionEstablished");
        service.setSession(webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        sendMsg(webSocketMessage.getPayload().toString());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

        System.out.println("handleTransportError");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

        System.out.println("afterConnectionClosed");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    private void sendMsg(String type){
        switch (type){
            case "Sales_statistics":service.sendSales_statistics();break;
            case "Goods_percentage":service.sendGoods_percentage();break;
            case "Goods_sort":service.sendGoods_sort();break;
            case "Inventory_warring":service.sendInventory_warring();break;
            default:break;
        }
    }
}