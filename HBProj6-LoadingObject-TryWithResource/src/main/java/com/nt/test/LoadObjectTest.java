package com.nt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		
		try(SessionFactory factory = HibernateUtil.getSessionFactory()){
			try(Session session = HibernateUtil.getSession()){ // or u can use ["try(Session session = factory.openSession())"]
				//load object
				InsurancePolicy policy = new InsurancePolicy();
				policy = session.load(InsurancePolicy.class, 1001L);
				if(policy == null)
					System.out.println("Record not Found");
				else
					System.out.println(policy);
			}//try
		}//try

	}

}
