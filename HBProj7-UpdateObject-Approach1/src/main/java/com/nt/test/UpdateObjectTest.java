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
		
		//prepare full object for modifcation with exiting id
		account = new BankAccount();
		account.setAcno(1001L);
		account.setHolderName("Praveen Chandana");
		account.setBalance(100000);
		
		try {
			tx = session.beginTransaction();
			session.update(account);
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Record is Updated");
			}
			else {
				tx.rollback();
				System.out.println("Record is Not Updated");
			}
			
			//close objects
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
