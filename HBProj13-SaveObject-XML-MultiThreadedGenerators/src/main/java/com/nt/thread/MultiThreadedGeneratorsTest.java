package com.nt.thread;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

public class MultiThreadedGeneratorsTest {

	public static void main(String[] args) throws Exception {
		Session session1 = null,session2 = null,session3 = null;
		Product prod1 = null,prod2 = null,prod3 = null;
		Transaction tx = null;
		Integer idVal = 0;
		boolean flag = false;
		//get session
		session1 = HibernateUtil.getSession();
		session2 = HibernateUtil.getSession();
		session3 = HibernateUtil.getSession();
		//create entity object
		prod1 = new Product();
		prod1.setPid(1004);
		prod1.setPname("Boat HeadPhones");
		prod1.setPrice(2000f);
		prod1.setQty(55f);
		prod2 = new Product();
		prod2.setPid(1004);
		prod2.setPname("JBL EarPhones");
		prod2.setPrice(1000f);
		prod2.setQty(85f);
		prod3 = new Product();
		prod3.setPid(1004);
		prod3.setPname("Sound Bar");
		prod3.setPrice(50000f);
		prod3.setQty(55f);
		
		
		MyRequest req1 = new MyRequest(session1, prod1);
		MyRequest req2 = new MyRequest(session2, prod2);
		MyRequest req3 = new MyRequest(session3, prod3);
		
		Thread t1 = new Thread(req1);
		Thread t2 = new Thread(req2);
		Thread t3 = new Thread(req3);
		t1.start();t2.start();t3.start();
		
		Thread.sleep(30000);
		
			//close objs
			HibernateUtil.closeSession(session1);
			HibernateUtil.closeSession(session2);
			HibernateUtil.closeSession(session3);
			HibernateUtil.closeSessionFactory();

	}//main

}//class
