package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Product prod = null;
		Transaction tx = null;
		boolean flag = false;
		
		session = HibernateUtil.getSession();
		prod = new Product();
		prod.setPid(456);
		prod.setPname("Computer");
		prod.setPrice(80000);
		prod.setQty(5);
		
		try {
			tx = session.beginTransaction();
			session.save(prod);
			flag = true;
		}catch (HibernateException he) {
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
				System.out.println("Object is Not Saved");
			}
		}//finally

	}//main

}//class
