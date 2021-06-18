package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.BankAccount;
import com.nt.utility.HibernateUtil;

public class SoftDeletionTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		//get session obj
		session = HibernateUtil.getSession();
		boolean flag = false;
		try {
			//get tx or begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			BankAccount acc = new BankAccount();
			acc.setAccountNumber(5);
			session.delete(acc);
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Account Closed(Soft Deletion)");
			}
			else {
				tx.rollback();
				System.out.println("Account Not Closed");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}

	}

}
