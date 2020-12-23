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
		String idVal = null;
		boolean flag = false;
		//get session
		session = HibernateUtil.getSession();
		//create entity object
		prod = new Product();
		//prod.setPid(1000);
		prod.setPname("Vivo V15 pro");
		prod.setPrice(28000f);
		prod.setQty(7f);
		
		try {
			tx = session.beginTransaction();
			idVal = (String) session.save(prod);
			System.out.println("Generated ID Value :: " +idVal);
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
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
