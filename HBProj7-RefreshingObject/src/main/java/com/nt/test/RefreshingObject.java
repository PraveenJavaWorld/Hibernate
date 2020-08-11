package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nt.entity.BankAccount;
import com.nt.utility.HibernateUtil;

public class RefreshingObject {

	public static void main(String[] args) {
		
		Session session = null;
		BankAccount account = null;
		//get session
		session = HibernateUtil.getSession();
		try {
			//load object for partial modification of the object
			account = session.get(BankAccount.class, 1001L);
			if(account!=null) {
				System.out.println(account);
				System.out.println("Modify 1001 record in DB Table from SQL Prompt/SQL Developer");
				try {
					Thread.sleep(40000);//modify db table record using SQL prompt or SQL developer
				} catch (Exception e) {
					e.printStackTrace();
				}
				session.refresh(account);//DB Table row to Entity Object Sync
				System.out.println(account);
			}
			else {
				System.out.println("Record/Object not Found");
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
