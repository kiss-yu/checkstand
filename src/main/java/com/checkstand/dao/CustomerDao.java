package com.checkstand.dao;

import com.checkstand.model.OneCustomerModel;
import com.checkstand.util.HibernateUtil;
import org.apache.ibatis.annotations.Param;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    public void delete(OneCustomerModel model){
        customerSession = customerSession == null ? getSession() : customerSession;
        try {
            customerSession.delete(model);
            HibernateUtil.commitTranstion(customerSession);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updata(OneCustomerModel model){
        customerSession = customerSession == null ? getSession() : customerSession;
        try {
            customerSession.update(model);
            HibernateUtil.commitTranstion(customerSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public OneCustomerModel selectByCustomerId(String customer_id){
        customerSession = customerSession == null ? getSession() : customerSession;
        try {
            OneCustomerModel oneCustomerModel = customerSession.get(OneCustomerModel.class,customer_id);
            HibernateUtil.commitTranstion(customerSession);
            return oneCustomerModel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OneCustomerModel> select(
            @Param("customer_id") String customer_id,
            @Param("query_start_day") Date query_start_day,
            @Param("quert_end_day") Date quert_end_day,
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("sort") String sort,
            @Param("order") String order,
            @Param("keyword") String keyword){
        customerSession = customerSession == null ? getSession() : customerSession;
        String hql = "select customer_model  from OneCustomerModel customer_model where 1 = 1 ";
        if (customer_id != null)
            hql += "and customer_model.customer_id = ‘" + customer_id + "’ ";
        if (quert_end_day != null)
            hql += "and customer_model.pay_time between " + query_start_day +" and " + quert_end_day + " ";
        if (keyword != null)
            hql += "and customer_model.customer_id like '" + keyword + "' ";
        if (offset != 0)
            hql += "limit " + offset + "," + limit + " ";
        if (sort != null)
            hql += "order by " + order + " " + sort + " ";
        try {
            List lists = customerSession.createQuery(hql).list();
            HibernateUtil.commitTranstion(customerSession);
            return (List<OneCustomerModel>) lists;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
