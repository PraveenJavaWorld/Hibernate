package com.nt.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySQLHibernateUtil {
	
	private static SessionFactory factory;
	 
	static {
		Configuration cfg = null;
		try {
			//bootstrap HB
			cfg = new Configuration();
			cfg.configure("com/nt/cfgs/mysql-hibernate.cfg.xml");
			//build session factory
			factory = cfg.buildSessionFactory();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
	}//static
	
	public static Session getSession() {
		Session session = null;
		//get session object from 
		if(session == null) {
			if(factory!=null) {
				session = factory.getCurrentSession();
			}//if
		}//if
		return session;
	}
	
	
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}

}
