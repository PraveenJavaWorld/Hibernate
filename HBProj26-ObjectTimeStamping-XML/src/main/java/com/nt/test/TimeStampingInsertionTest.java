package com.nt.test;

import java.time.LocalDateTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.BankAccount;
import com.nt.utility.HibernateUtil;

public class TimeStampingInsertionTest {

	public static void main(String[] args) {
		
		Session session = null;
		BankAccount acc = null;
		Transaction tx = null;
		boolean flag = false;
		Long idVal = null;
		//get session
		session = HibernateUtil.getSession();
		//prepare object
		acc = new BankAccount();
		acc.setHolder("Rocky");
		acc.setBalance(20000.0);
		acc.setType("Current");
		acc.setOpeningDate(LocalDateTime.now());
		try {
			//begin tx
			tx = session.beginTransaction();
			idVal = (Long)session.save(acc);
			System.out.println("Generated ID Value :: "+idVal);
			flag = true;
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
