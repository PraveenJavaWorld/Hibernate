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
			policy = session.load(InsurancePolicy.class , 1001L);
			
			// to know proxy class and super class
			System.out.println(policy.getClass());
			System.out.println(policy.getClass().getSuperclass());
			
			//load() didn't hit db bcoz policyId is mentioned by enduser
			System.out.println(policy.getPolicyId());
			
			//hit the db bcoz policyName is not mentioned in load()
			System.out.println(policy.getPolicyName());
			
			//to view all db data
			System.out.println(policy);
			
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
