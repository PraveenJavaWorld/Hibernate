package com.nt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		//with java 9 syntax. Only works from java 9 and further
		try(factory;session){
			//load object
				InsurancePolicy policy = new InsurancePolicy();
				policy = session.load(InsurancePolicy.class, 1001L);
				if(policy == null)
					System.out.println("Record not Found");
				else
					System.out.println(policy);
		}//try

	}

}
