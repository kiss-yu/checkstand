package com.checkstand.dao;

import com.checkstand.model.GoodsModel;
import com.checkstand.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


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
        }
        catch (Exception e){
            model = new GoodsModel("12345",new Float(98.8));
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
    private void commitAndExceptionHandling(){
        try {
            HibernateUtil.commitTranstion(goodsSession);
        }catch (Exception e) {
            goodsSession = getSession();
            e.printStackTrace();
        }
    }
}
