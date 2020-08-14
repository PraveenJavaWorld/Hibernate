package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Membership;
import com.nt.utility.HibernateUtil;

public class SaveorUpdateTest {

	public static void main(String[] args) {
		
		Session session = null;
		Membership member = null;
		Transaction tx = null;
		boolean flag = false;
		
		//get session
		session = HibernateUtil.getSession();
		//prepare object
		member = new Membership();
		//member.setMid(1L);
		member.setName("Praveen");
		member.setAddress("Ramachandrapuram");
		member.setRewardPoints(1200L);
		
		try {
			//begin transaction
			tx = session.beginTransaction();
			session.saveOrUpdate(member);
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Record is Saved or Updated");
			}
			else {
				tx.rollback();
				System.out.println("Record is not Saved or Updated");
			}
			
			//close objects
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
