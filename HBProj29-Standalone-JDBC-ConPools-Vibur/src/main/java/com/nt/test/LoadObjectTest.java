package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		
				Session session = null;
				InsurancePolicy policy = null;
				//get session object
				session = HibernateUtil.getSession();
				try {
					//load object
					policy = session.load(InsurancePolicy.class, 1001L);
					if(policy == null)
						System.out.println("Record not Found");
					else {
						System.out.println("Record found and displayed");
						System.out.println(policy);
					} 
				} catch (HibernateException he) {
					he.printStackTrace();
				}
				finally {
					HibernateUtil.closeSession(session);
					HibernateUtil.closeSessionFactory();
				}
		
	}

}
