package com.checkstand.service;

import com.checkstand.model.GoodsModel;
import com.checkstand.model.OneCustomerModel;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by 11723 on 2017/5/16.
 */
@Component
public class WebSocketService {
    private final static Log log = LogFactory.getLog(WebSocketService.class);
    private static WebSocketSession rootSession;
    @Resource
    private CustomerService service;
    @Resource
    private GoodsService goodsService;

    public WebSocketSession getSession() {
        return rootSession;
    }

    public void setSession(WebSocketSession session) {
        rootSession = session;
    }


    public void sendGoods_percentage(){
        List<GoodsModel> goodsModels = goodsService.select(null,"soldNumber","desc",5,1);
        StringBuffer string = new StringBuffer("{\"type\":\"Goods_percentage\",\"msg\":{\"goods\":[");
        for (GoodsModel model:goodsModels){
            Map<String,Object> map = new HashedMap();
            map.put("title",model.getTitle());
            map.put("soldNumber",model.getInventory());
            string.append(JSONObject.fromObject(map).toString() + ",");
        }
        string.append("]");
        string.append(",sum:" + goodsService.getSoldSum() + "}}");
        sendMsg(string.toString().replaceAll(",]","]"));
    }

    public void sendInventory_warring(){
        List<GoodsModel> goodsModels = goodsService.select(null,"inventory","asc",5,1);
        StringBuffer string = new StringBuffer("{\"type\":\"Inventory_warring\",\"msg\":{\"goods\":[");
        for (GoodsModel model:goodsModels){
            Map<String,Object> map = new HashedMap();
            map.put("title",model.getTitle());
            map.put("inventory",model.getInventory());
            string.append(JSONObject.fromObject(map).toString() + ",");
        }
        string.append("]}}");
        sendMsg(string.toString().replaceAll(",]}","]}"));
    }

    public void sendGoods_sort(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) -1);
        Date date = calendar.getTime();
        List<OneCustomerModel> customerModels = service.selectOneDay(date,new Date());
        List<GoodsModel> goodsModels = new ArrayList<>();
        for (OneCustomerModel model:customerModels)
            goodsModels.addAll(model.getGoodsModels());
        Map<GoodsModel,Integer> map = new HashedMap();
        for (GoodsModel good:goodsModels){
            if (map.get(good) == null)
                map.put(good,1);
            else {
                int count = map.get(good);
                map.remove(good);
                map.put(good,count++);
            }
        }
        int[] num = new int[5];
        for (Map.Entry<GoodsModel,Integer> entry:map.entrySet()){
            num[0] = entry.getValue() > num[0] ? entry.getValue() : num[0];
            num[1] = entry.getValue() > num[1] && entry.getValue() < num[0]
                    ? entry.getValue() : num[1];
            num[2] = entry.getValue() > num[2] && entry.getValue() < num[1]
                    ? entry.getValue() : num[2];
            num[3] = entry.getValue() > num[3] && entry.getValue() < num[2]
                    ? entry.getValue() : num[3];
            num[4] = entry.getValue() > num[4] && entry.getValue() < num[3]
                    ? entry.getValue() : num[4];
        }
        StringBuffer string = new StringBuffer("{\"type\":\"Goods_sort\",\"msg\":{\"goods\":[");
        int i = 0;
        for (Map.Entry<GoodsModel,Integer> entry:map.entrySet()){
            if (entry.getValue() == num[i]){
                Map<String,Object> goodMap = new HashedMap();
                goodMap.put("title",entry.getKey().getTitle());
                goodMap.put("soldNumber",num[i]);
                goodMap.put("inventory",entry.getKey().getInventory());
                string.append(JSONObject.fromObject(goodMap).toString() + ",");
            }
        }
        string.append("]}}");
        sendMsg(string.toString().replaceAll(",]}","]}"));
    }




    public void sendSales_statistics(){
        StringBuffer string = new StringBuffer("{\"type\":\"Sales_statistics\",\"msg\":{\"msg\":[");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
        for(int i = 7;i > 0; i --) {
            Date start = calendar.getTime();
            calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
            Date end = calendar.getTime();
            Map<String,Object> map = new HashMap<>();
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            map.put("date",month + "/" + day);
            int count = 0;
            float pace = 0;
            List<OneCustomerModel> list = service.selectOneDay(start,end);
            for (OneCustomerModel model:list){
                count += model.getBuy_number();
                pace += model.getAccount();
            }
            map.put("count",count);
            map.put("pace",pace);
            string.append(JSONObject.fromObject(map).toString() + ",");
        }
        string.append("]}}");
        sendMsg(string.toString().replaceAll(",]}","]}"));
    }

    private void sendMsg(String msg){
        try {
            log.info(msg);
            rootSession.sendMessage(new TextMessage(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
