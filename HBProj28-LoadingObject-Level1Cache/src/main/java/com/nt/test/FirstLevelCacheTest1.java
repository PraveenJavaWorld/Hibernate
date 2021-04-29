package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class FirstLevelCacheTest1 {

	public static void main(String[] args) {
		
		Session session = null;
		InsurancePolicy policy = null;
		Transaction tx = null;
		boolean flag = false;
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
				tx = session.beginTransaction();
				policy.setTenure(15);
				session.update(policy);
				policy.setTenure(20);
				session.update(policy);
				flag = true;
			}
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		}
		catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			
			if(flag) {
				tx.commit();
				System.out.println("Record Updated");
			}
			else {
				tx.rollback();
				System.out.println("Record Not Updated");
			}
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
