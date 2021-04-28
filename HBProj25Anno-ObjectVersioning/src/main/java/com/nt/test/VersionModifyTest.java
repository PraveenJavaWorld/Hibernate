package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.MobileCustomer;
import com.nt.utility.HibernateUtil;

public class VersionModifyTest {

	public static void main(String[] args) {
		
		Session session = null;
		MobileCustomer cust = null;
		Transaction tx = null;
		boolean flag = false;
		
		//get session
		session = HibernateUtil.getSession();
		//prepare object

		try {
			//begin tx
			tx = session.beginTransaction();
			//load object
			cust = session.get(MobileCustomer.class, 4);
			if(cust!=null) {
				cust.setCallerTune("ASVR Background Music");
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
				System.out.println("Record is Updated");
				System.out.println("Record is Updated for :: "+cust.getVersionCount()+ " times");
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
