package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nt.entity.Membership;
import com.nt.utility.HibernateUtil;

public class FlushTest {

	public static void main(String[] args) {
		
		Session session = null;
		Membership member = null;
		long idVal = 0;
		//load session object
		session = HibernateUtil.getSession();
		//prepare object
		member = new Membership();
		member.setMid(56L);
		member.setName("Praveen");
		member.setAddress("Hyderabad");
		member.setRewardPoints(1200L);
		
		try {
			idVal = (long) session.save(member);
			System.out.println("Generated ID Value::" +idVal);
			session.flush();
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close objects
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
