package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		
		Session session = null;
		InsurancePolicy policy = null;
		//get Session object
		session = HibernateUtil.getSession();
		
		try {
			//load object
			policy = session.get(InsurancePolicy.class,1001L);
			//verify
			if(policy==null)
				System.out.println("Record Not Found");
			else {
				System.out.println("Record is Found and Displayed Below");
				System.out.println(policy);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
