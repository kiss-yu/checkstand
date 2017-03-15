package com.checkstand.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by 11723 on 2017/1/17.
 */
@Repository
public class SessionDao {
    @Resource
    private   SessionFactory sessionFactory;
    public   Session getSession(){
        return sessionFactory.openSession();
    }
}