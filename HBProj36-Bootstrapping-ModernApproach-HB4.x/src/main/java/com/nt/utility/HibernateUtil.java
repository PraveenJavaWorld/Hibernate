package com.nt.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.nt.entity.InsurancePolicy;

public class HibernateUtil {
	
	private static SessionFactory factory;
	
	static {
		Configuration cfg = null;
		ServiceRegistryBuilder builder = null;
		ServiceRegistry registry = null;
		try {
			 cfg = new Configuration();
			 cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
			//specify entity class
			cfg.addAnnotatedClass(InsurancePolicy.class);
			//build ServiceRegistryBuilder
			builder = new ServiceRegistryBuilder();
			//build ServiceRegistry
			registry = builder.applySettings(cfg.getProperties()).buildServiceRegistry();
			//build sessionFactory
			factory = cfg.buildSessionFactory(registry);
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//static
	
	public static Session getSession() {
		Session session = null;
		if(factory!=null)
			session = factory.openSession();
		return session;
	}
	
	public static void closeSession(Session session) {
		if(session!=null)
			session.close();
	}
	
	public static void closeSessionFactory() {
		if(factory!=null) {
			factory.close();
		}
	}
 
}
