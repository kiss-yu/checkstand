package com.checkstand.service;

import com.checkstand.dao.CustomerDao;
import com.checkstand.model.GoodsModel;
import com.checkstand.model.OneCustomerModel;
import com.checkstand.web.util.SQLUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by 11723 on 2017/3/21.
 */
@Service
public class CustomerService {

    @Resource
    private CustomerDao customerDao;
    public void insert(OneCustomerModel model){
        model.setBuyMessage();
        model.setBuy_time(new Date());
        model.setBuy_number(model.getGoodsModels().size());
        for(GoodsModel good:model.getGoodsModels())
            model.setAccount(model.getAccount() + good.getGoodsPrace());
        customerDao.insert(model);
    }

    public OneCustomerModel find(String customer_id){
        return customerDao.selectByCustomerId(customer_id);
    }

    public void updata(OneCustomerModel model){
        customerDao.updata(model);
    }

    public List<OneCustomerModel> selectOneDay(Date start,Date end){
        return select(null,start,end,0,0,null,null);
    }

    public List<OneCustomerModel> select(String customer_id,Date query_start_time,Date query_end_time,int page,int limit,String sort,String order){
        return customerDao.select(customer_id,query_start_time,query_end_time, SQLUtil.getOffset(page,limit),limit,sort,order);
    }

}
