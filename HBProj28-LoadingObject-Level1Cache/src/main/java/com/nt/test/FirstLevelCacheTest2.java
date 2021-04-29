package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class FirstLevelCacheTest2 {

	public static void main(String[] args) {
		
		Session session = null;
		InsurancePolicy policy = null,policy1=null;
		//get Session object
		session = HibernateUtil.getSession();
		
		try {
			//load object
			long start1 = System.currentTimeMillis();
			policy = session.get(InsurancePolicy.class,1001L); //gets from DB and puts in L1 cache
			System.out.println("1 from DB "+policy);
			long end1 = System.currentTimeMillis();
			System.out.println("Time taken to fetch record from db is :: "+(end1-start1)+" ms");
			System.out.println("=========================");
			
			long start2 = System.currentTimeMillis();
			policy1 = session.get(InsurancePolicy.class, 1001L); //gets from L1 cache
			System.out.println("2 from cache "+policy1);
			long end2 = System.currentTimeMillis();
			System.out.println("Time taken to fetch record from cache is :: "+(end2-start2)+" ms");
			session.evict(policy); //removes from L1 cache
			System.out.println("=========================");
			
			long start3 = System.currentTimeMillis();
			policy1 = session.get(InsurancePolicy.class,1001L); //gets from DB and puts in L1 cache
			System.out.println("3 from db "+policy1);
			long end3 = System.currentTimeMillis();
			System.out.println("Time taken to fetch record from db is :: "+(end3-start3)+" ms");
			System.out.println("=========================");
			
			long start4 = System.currentTimeMillis();
			policy1 = session.get(InsurancePolicy.class, 1001L); //gets from L1 cache
			System.out.println("4 from cache "+policy1);
			long end4 = System.currentTimeMillis();
			System.out.println("Time taken to fetch record from cache is :: "+(end4-start4)+" ms");
			session.clear(); //removes all objects from L1 cache
			System.out.println("=========================");
			
			long start5 = System.currentTimeMillis();
			policy1 = session.get(InsurancePolicy.class,1001L); //gets from DB and puts in L1 cache
			System.out.println("5 from db "+policy1);
			long end5 = System.currentTimeMillis();
			System.out.println("Time taken to fetch record from db is :: "+(end5-start5)+" ms");
			
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
