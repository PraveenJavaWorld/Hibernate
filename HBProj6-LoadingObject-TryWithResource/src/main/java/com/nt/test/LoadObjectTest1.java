package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest1 {

	public static void main(String[] args) {
		
		// with java 8 syntax
		try(SessionFactory factory = HibernateUtil.getSessionFactory();Session session = factory.openSession()){
			
			//load object
			InsurancePolicy policy = session.load(InsurancePolicy.class, 1001L);
			if(policy == null)
				System.out.println("Record not Found");
			else
				System.out.println(policy);
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}

	}

}
