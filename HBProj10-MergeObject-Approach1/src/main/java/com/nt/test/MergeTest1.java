package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Membership;
import com.nt.utility.HibernateUtil;

public class MergeTest1 {

	public static void main(String[] args) {
		
		Session session = null;
		Membership member = null , m1 = null;
		Transaction tx = null;
		boolean flag = false;
		//load session object
		session = HibernateUtil.getSession();
		//prepare object
		member = new Membership();
		member.setMid(2L);
		member.setName("Rocky");
		member.setAddress("Ramachandrapuram");
		member.setRewardPoints(150L);
		
		try {
			tx = session.beginTransaction();
			m1 = (Membership) session.merge(member);
			System.out.println(m1);
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object is Saved or Updated");
			}
			else {
				tx.rollback();
				System.out.println("Object is not Saved or Updated");
			}
			//close objects
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
