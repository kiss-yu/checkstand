package com.checkstand.service;

import com.checkstand.dao.CustomerDao;
import com.checkstand.model.OneCustomerModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by 11723 on 2017/3/21.
 */
@Service
public class CustomerService {

    @Resource
    private CustomerDao customerDao;
    public void insert(OneCustomerModel model){
        model.setBuyMessage();
        customerDao.insert(model);
    }

}
