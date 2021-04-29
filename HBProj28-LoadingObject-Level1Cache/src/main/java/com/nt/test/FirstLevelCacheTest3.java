package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class FirstLevelCacheTest3 {

	public static void main(String[] args) {
		
		Session session = null;
		InsurancePolicy policy = null,policy1=null;
		//get Session object
		session = HibernateUtil.getSession();
		
		try {
			//load object
			long start1 = System.currentTimeMillis();
			policy = session.load(InsurancePolicy.class,1001L); // gets from db and put in L1 cache 
			System.out.println(policy);
			long end1 = System.currentTimeMillis();
			System.out.println("Time taken to fetch record from db is :: "+(end1-start1)+" ms");
			System.out.println("=========================");
			
			long start2 = System.currentTimeMillis();
			policy1 = session.load(InsurancePolicy.class,1001L); //from cache
			System.out.println(policy1);
			long end2 = System.currentTimeMillis();
			System.out.println("Time taken to fetch record from cache is :: "+(end2-start2)+" ms");
			
			
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			//close objs
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}

	}

}
