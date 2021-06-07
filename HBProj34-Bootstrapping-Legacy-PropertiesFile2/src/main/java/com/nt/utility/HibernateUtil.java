package com.nt.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nt.entity.InsurancePolicy;

public class HibernateUtil {
	
	private static SessionFactory factory;
	
	static {
		Configuration cfg = null;
		InputStream is = null;
		Properties props = null;
		try {
			//bootstrap hibernate
			cfg = new Configuration();
			//load and read Properties file
			is = new FileInputStream("src/main/java/com/nt/commons/mycfg.properties");
			// put properties file content to java.util.Properties object 
			props = new Properties();
			props.load(is);
			//specify mapping file
			//cfg.addFile("src/main/java/com/nt/entity/InsurancePolicy.hbm.xml");
			//specify entity class
			cfg.addAnnotatedClass(InsurancePolicy.class);
			cfg.setProperties(props);
			//build sessionFactory
			factory = cfg.buildSessionFactory();
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
