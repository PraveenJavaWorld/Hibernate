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
			//Bootstrap Hibernate
			cfg = new Configuration();
			//input HB cfg file
			cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
			//create SessionFactory object
			factory = cfg.buildSessionFactory();			
			
		} catch (HibernateException he) {
			he.printStackTrace();			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//static block
	
	public static Session getSession() {
		Session session = null;
		if(factory!=null) {
			session = factory.openSession();
		}//if
		return session;
	}//session method
	
	public static void closeSession(Session session) {
		if(session!=null) {
			session.close();
		}
	}//close session
	
	public static void closeSessionFactory() {
		if(factory!=null) {
			factory.close();
		}
	}

}//class
