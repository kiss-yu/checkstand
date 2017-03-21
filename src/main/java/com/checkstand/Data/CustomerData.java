package com.checkstand.Data;

import com.checkstand.model.GoodsModel;
import com.checkstand.model.OneCustomerModel;
import org.springframework.stereotype.Component;

/**
 * Created by 11723 on 2017/3/19.
 */
@Component
public class CustomerData {
    private OneCustomerModel one_customer = new OneCustomerModel();

    public  void clearOneCustomer(){
        one_customer.getGoodsModels().clear();
    }

    public  void insertGoods(GoodsModel model){
        one_customer.setCustomer_id(one_customer.getGoodsModels().size() == 0 ? "kiss" + System.currentTimeMillis() : one_customer.getCustomer_id());
        one_customer.getGoodsModels().add(model);
    }

    public OneCustomerModel getOne_customer() {
        return one_customer;
    }

    public void setOne_customer(OneCustomerModel one_customer) {
        this.one_customer = one_customer;
    }
}
