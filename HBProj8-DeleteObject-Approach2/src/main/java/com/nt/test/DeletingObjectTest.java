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
			//load object
			proj = session.get(Project.class, 82L);
			if(proj!=null) {
				//delete object
				session.delete(proj);
				flag = true;
			}
			else {
				System.out.println("Record not found to Delete");
				flag = false;
				return;
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object Deleted");
			}
			else {
				tx.rollback();
				System.out.println("Object not Deleted");
			}
			//close objects
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
