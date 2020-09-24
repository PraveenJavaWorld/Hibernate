package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Project;
import com.nt.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		boolean flag = true;
		Project proj = null;
		Long idVal = 0L;
		//Get Session
		session = HibernateUtil.getSession();
		//create entity object
		proj = new Project();
		proj.setProjId(9001L);
		proj.setProjName("OpenFX");
		proj.setTeamsize(10);
		proj.setCompany("TCS");
		try {
			//begin Transaction
			tx = session.beginTransaction();
			idVal = (Long) session.save(proj);
			System.out.println("Generated value:: " +idVal);
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object is Saved");
			}
			else {
				tx.rollback();
				System.out.println("Object is not Saved");
			}
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		
		}//finally

	}//main

}//class
