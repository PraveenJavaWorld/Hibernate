package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.BankAccount;
import com.nt.utility.HibernateUtil;

public class UpdateObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		BankAccount account = null;
		Transaction tx = null;
		boolean flag = false;
		
		//get session object
		session  = HibernateUtil.getSession();
			
		try {
			//begin transaction
			tx = session.beginTransaction();
			//load object for partial modifcation of the object	
			account = session.get(BankAccount.class, 1001L);
			
			if(account!=null) {
				//modify object
				account.setBalance(400000);
				flag = true;
			}
			else {
				System.out.println("Record/Object not found");
				return;
			}
			
		}//try 
		catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object Updated");
			}
			else {
				tx.rollback();
				System.out.println("Object Not Updated");
			}
			
			//close objects
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
