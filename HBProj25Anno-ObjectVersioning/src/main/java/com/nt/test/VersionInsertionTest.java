package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.MobileCustomer;
import com.nt.utility.HibernateUtil;

public class VersionInsertionTest {

	public static void main(String[] args) {
		
		Session session = null;
		MobileCustomer cust = null;
		Transaction tx = null;
		boolean flag = false;
		Integer idVal = null;
		//get session
		session = HibernateUtil.getSession();
		//prepare object
		cust = new MobileCustomer();
		cust.setCname("Rocky");
		cust.setMobileNumber(9876543210L);
		cust.setCallerTune("Baahubali Title Song");
		try {
			//begin tx
			tx = session.beginTransaction();
			idVal = (Integer)session.save(cust);
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
