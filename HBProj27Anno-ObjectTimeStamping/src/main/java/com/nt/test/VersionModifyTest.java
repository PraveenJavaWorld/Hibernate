package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.BankAccount;
import com.nt.utility.HibernateUtil;

public class VersionModifyTest {

	public static void main(String[] args) {
		
		Session session = null;
		BankAccount acc = null;
		Transaction tx = null;
		boolean flag = false;
		
		//get session
		session = HibernateUtil.getSession();
		//prepare object

		try {
			//begin tx
			tx = session.beginTransaction();
			//load object
			acc = session.get(BankAccount.class, 100002L);
			if(acc!=null) {
				acc.setBalance(acc.getBalance()+5000);
				flag = true;
			}
			else {
				System.out.println("Record Not Found");
			}
			
		} 
		catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Account is Updated");
				System.out.println("Account is Opened on :: "+acc.getOpeningDate());
				System.out.println("Account is Updated on :: "+acc.getLastUpdated());
				System.out.println("Account is Updated for :: "+acc.getVersionCount()+ " times");
			}
			else {
				tx.rollback();
				System.out.println("Record is Not Updated");
			}
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
