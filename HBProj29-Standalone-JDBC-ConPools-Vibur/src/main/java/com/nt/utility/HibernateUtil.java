package com.nt.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static SessionFactory factory;
	
	static {
		Configuration cfg = null;
		try {
			//bootstrap HB
			cfg = new Configuration();
			//input cfg file
			cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
			//build sessionfactory
			factory = cfg.buildSessionFactory();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}// static
	
	//Required only while working with Try With Resource
	public static SessionFactory getSessionFactory() {
		if(factory!=null)
			return factory;
		else
			throw new HibernateException("Problem in SessionFactory Object Creation");
	}
	
	
	public static Session getSession() {
		Session session = null;
		if(factory!=null) {
			session = factory.openSession();
		}
		return session;
	}
	
	
	//optional while working with Try With Resource
	public static void closeSession(Session session) {
		if(session!=null)
			session.close();
	}
	
	
	//optional while working with Try With Resource
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}

}
