package com.nt.thread;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Product;

public class MyRequest implements Runnable {
	private Session session;
	private Product p;
	public MyRequest(Session session,Product p) {
		this.session = session;
		this.p = p;
	}
	@Override
	public void run() {
		
		Transaction tx = null;
		boolean flag = false;
		int idVal = 0;
		try {
			tx = session.beginTransaction();
			idVal = (Integer) session.save(p);
			flag = true;
			System.out.println("Generated IdValue :" +idVal);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		finally {
			if(flag)
				tx.commit();
			else
				tx.rollback();
		}

	}

}
