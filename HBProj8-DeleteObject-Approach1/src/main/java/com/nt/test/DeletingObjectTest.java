package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class DeletingObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Project proj = null;
		Transaction tx = null;
		boolean flag = false;
		
		//get session
		session = HibernateUtil.getSession();
		try {
			//begin transaction
			tx = session.beginTransaction();
			proj = new Project();
			proj.setProjId(82);
			session.delete(proj);
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object/Record Deleted");
			}
			else {
				tx.rollback();
				System.out.println("Object/Record not Deleted");
			}
			
			//close objects
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
