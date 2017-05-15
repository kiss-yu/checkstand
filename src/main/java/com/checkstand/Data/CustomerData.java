package com.checkstand.Data;

import com.checkstand.model.GoodsModel;
import com.checkstand.model.OneCustomerModel;
import org.springframework.stereotype.Component;

/**
 * Created by 11723 on 2017/3/19.
 */
public class CustomerData {
    public static OneCustomerModel one_customer = new OneCustomerModel();

    public static void clearOneCustomer(){
        one_customer.getGoodsModels().clear();
        one_customer.setCustomer_id("");
    }

    public static void insertGoods(GoodsModel model){
        one_customer.setCustomer_id(one_customer.getGoodsModels().size() == 0 ? "kiss" + System.currentTimeMillis() : one_customer.getCustomer_id());
        one_customer.getGoodsModels().add(model);
    }

}
