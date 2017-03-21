package com.checkstand.dao;

import com.checkstand.model.OneCustomerModel;
import com.checkstand.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by 11723 on 2017/3/21.
 */
@Repository
public class CustomerDao extends SessionDao{
    private static Session customerSession = null;
    public void insert(OneCustomerModel model){
        customerSession = customerSession == null ? getSession() : customerSession;
        try {
            customerSession.save(model);
            HibernateUtil.commitTranstion(customerSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
