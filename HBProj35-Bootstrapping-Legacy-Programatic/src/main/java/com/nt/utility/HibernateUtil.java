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
		try {
			//bootstrap hibernate
			cfg = new Configuration();
			cfg.setProperty("hibernate.connection.driver_class", "oracle.jdbc.driver.OracleDriver");
			cfg.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:xe");
			cfg.setProperty("hibernate.connection.username", "system");
			cfg.setProperty("hibernate.connection.password", "1234");
			cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			cfg.setProperty("hibernate.show_sql", "true");
			cfg.setProperty("hibernate.format_sql", "true");
			cfg.setProperty("hibernate.hbm2ddl.auto", "update");
			//specify mapping file
			//cfg.addFile("src/main/java/com/nt/entity/InsurancePolicy.hbm.xml");
			//specify entity class
			cfg.addAnnotatedClass(InsurancePolicy.class);
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
