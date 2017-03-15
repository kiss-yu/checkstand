package com.checkstand.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateUtil {

	public static void commitTranstion(Session  session) throws Exception{
		Transaction tx=session.beginTransaction();
		try {
			tx.commit();
//			session.close();
		}catch (Exception e){
			tx.rollback();
			session.close();
			throw e;
		}
	}
}
