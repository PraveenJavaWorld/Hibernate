package com.nt.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory factory;
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	 
	static {
		Configuration cfg = null;
		try {
			//bootstrap HB
			cfg = new Configuration();
			cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
			//build session factory
			factory = cfg.buildSessionFactory();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}//static
	
	public static Session getSession() {
		Session session = null;
		//get session object from ThreadLocal
		session = threadLocal.get();
		if(session == null) {
			if(factory!=null) {
				session = factory.openSession();
				threadLocal.set(session);;
			}//if
		}//if
		return session;
	}
	
	public static void closeSession() {
		Session session = null;
		session = threadLocal.get();
		if(session!=null) {
			session.close();
			threadLocal.remove();
		}
	}
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}

}
