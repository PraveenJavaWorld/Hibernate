package com.nt.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.ServiceRegistry;

import com.nt.entity.InsurancePolicy;
import com.nt.service.UserConnectionService;

public class HibernateUtil {
	
	private static SessionFactory factory;
	
	static {
		Configuration cfg = null;
		StandardServiceRegistryBuilder builder = null;
		ServiceRegistry registry = null;
		try {
			 cfg = new Configuration();
			 cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
			//specify entity class
			cfg.addAnnotatedClass(InsurancePolicy.class);
			//build ServiceRegistryBuilder
			builder = new StandardServiceRegistryBuilder();
			//add service
			builder.addService(ConnectionProvider.class, new UserConnectionService());
			//create ServiceRegistry
			registry = builder.applySettings(cfg.getProperties()).build();
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
