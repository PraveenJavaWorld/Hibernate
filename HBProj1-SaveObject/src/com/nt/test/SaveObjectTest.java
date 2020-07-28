package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		Configuration cfg = null;
		SessionFactory factory = null;
		Session ses = null;
		Transaction tx = null;
		Product prod = null;
		boolean flag = false;
		
		cfg = new Configuration();
		cfg.configure("com/nt/cfgs/hibernate.cfg.xml");
		factory = cfg.buildSessionFactory();
		ses = factory.openSession();
		
		prod = new Product();
		prod.setPid(123);
		prod.setPname("table");
		prod.setPrice(60000);
		prod.setQty(2);
		
		try {
			tx = ses.beginTransaction();
			ses.save(prod);
			flag = true;
			
		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		finally {
			if(flag == true) {
				tx.commit();
				System.out.println("Object is Saved");
			}
			else {
				tx.rollback();
				System.out.println("Object is not Saved");
			}
			ses.close();
			factory.close();
		}

	}

}
