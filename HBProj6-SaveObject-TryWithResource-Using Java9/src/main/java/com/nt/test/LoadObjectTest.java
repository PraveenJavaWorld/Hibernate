package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		
		InsurancePolicy policy = null;
		Transaction tx = null;
		boolean flag = false;
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		//with java 9 syntax. Only works from java 9 and further
		try(factory;session){
			//load object
				policy = new InsurancePolicy();
				//save object
				policy.setPolicyName("JeevanLife");
				policy.setPolicyType("Life");
				policy.setCompany("LIC");
				policy.setTenure(30);
				
				try {
					tx = session.beginTransaction();
					session.save(policy);
					flag = true;
				} catch (HibernateException he) {
					flag = false;
					he.printStackTrace();
				}
				finally {
					if(flag) {
						tx.commit();
						System.out.println("Object is Saved");
					}
					else {
						tx.rollback();
						System.out.println("Object is not Saved");
					}
				}
				
		}//try with resource
		catch (HibernateException he) {
			he.printStackTrace();
		}

	}

}
