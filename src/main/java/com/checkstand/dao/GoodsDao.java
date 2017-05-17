package com.checkstand.dao;

import com.checkstand.model.GoodsModel;
import com.checkstand.model.OneCustomerModel;
import com.checkstand.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by 11723 on 2017/3/14.
 */
@Repository
public class GoodsDao extends SessionDao{
    private static Session goodsSession = null;
    public void insert(GoodsModel model){
        goodsSession = goodsSession == null ? getSession():goodsSession;
        goodsSession.save(model);
        commitAndExceptionHandling();
    }
    public GoodsModel selectByGoodsId(String goodsId){
        goodsSession = goodsSession == null ? getSession():goodsSession;
        GoodsModel model = null;
        try {
            Criteria criteria = goodsSession.createCriteria(GoodsModel.class);
            model = (GoodsModel)criteria.add(Restrictions.eq("goodsId",goodsId)).uniqueResult();
            commitAndExceptionHandling();
        }catch (Exception e){
            model = null;
        }
        return model;
    }
    public void updata(GoodsModel model){
        goodsSession = goodsSession == null ? getSession():goodsSession;
        goodsSession.update(model);
        commitAndExceptionHandling();
    }
    public void deleteByGoodsId(String id){
        goodsSession = goodsSession == null ? getSession():goodsSession;
        goodsSession.delete(selectByGoodsId(id));
        commitAndExceptionHandling();
    }

    public List<GoodsModel> select(String title,String order,String sort,int limit,int offset){
        goodsSession = goodsSession == null ? getSession():goodsSession;
        String hql = "from GoodsModel goods_model where 1 = 1 ";
        if (title != null)
            hql += "and goods_model.title = ‘" + title + "’ ";
        if (sort != null)
            hql += "order by goods_model." + order + " " + sort + " ";
        try {
            final Query query = goodsSession.createQuery(hql);
            if (offset != 0)
                query.setFirstResult(offset).setMaxResults(limit);
            return (List<GoodsModel>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public long selectSoldSum(){
        goodsSession = goodsSession == null ? getSession():goodsSession;
        String hql = "select sum(goods.soldNumber) from GoodsModel as goods where 1 = 1";
        long sum = (long) goodsSession.createQuery(hql) .iterate().next();
        try {
            HibernateUtil.commitTranstion(goodsSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    private void commitAndExceptionHandling(){
        try {
            HibernateUtil.commitTranstion(goodsSession);
        }catch (Exception e) {
            goodsSession = getSession();
            e.printStackTrace();
        }
    }
}
