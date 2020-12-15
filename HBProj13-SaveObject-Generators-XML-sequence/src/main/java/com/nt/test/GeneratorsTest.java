package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

public class GeneratorsTest {

	public static void main(String[] args) {
		
		Session session = null;
		Product prod = null;
		Transaction tx = null;
		Integer idVal = 0;
		boolean flag = false;
		
		//get Session object
		session = HibernateUtil.getSession();
		//create entity object
		prod = new Product();
		prod.setPname("Vivo V15 pro");
		prod.setPrice(28000.0f);
		prod.setQty(2f);
		
		try {
			//begin tx
			tx = session.beginTransaction();
			idVal = (Integer) session.save(prod);
			System.out.println("Generated value is :: "+idVal);
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Record is Saved");
			}
			else {
				tx.rollback();
				System.out.println("Record is Not Saved");
			}
			
			//close session and session factory
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
			
		}//finally
		
		
	}//main

}//class
