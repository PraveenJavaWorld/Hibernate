package com.nt.test;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.utility.HibernateUtil;

public class SoftDeletionTest_HQL {

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
			//Prepare and Execute Query
			Query query = session.createQuery("UPDATE com.nt.entity.BankAccount SET STATUS='ACTIVE' WHERE accountNumber=:acc");
			//set parameter
			query.setParameter("acc", 5);
			//execute query
			int count = query.executeUpdate();
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
