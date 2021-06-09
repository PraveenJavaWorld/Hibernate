package com.nt.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class HQLNonSelectTest {

	public static void main(String[] args) {

		// get Session obj
		Session session = null;
		//============ HQL NonSELECT UPDATE Query ============
		Transaction tx = null;
		Query query = null;
		boolean flag = false;
		int count = 0;
		//before updation
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();//dummy
			//prepare Query
			query = session.createQuery("FROM com.nt.entity.InsurancePolicy WHERE policyType LIKE :letters");
			//set Query param values
			query.setParameter("letters", "Life%");
			//execute Query
			List<InsurancePolicy> list = query.getResultList();
			System.out.println("List Size is :: "+list.size());
			System.out.println("Before Updation");
			if(!list.isEmpty()) {
				list.forEach(System.out::println);;
			}
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		try {
			session = HibernateUtil.getSession();
			tx = session.getTransaction();//mandatory
			//prepare Query
			query = session.createQuery("UPDATE com.nt.entity.InsurancePolicy SET tenure=tenure+:number WHERE policyType LIKE :letters");
			//set Query param values
			query.setParameter("number", 10);
			query.setParameter("letters", "Life%");
			//execute Query
			count = query.executeUpdate();
			flag = true;
		} catch (HibernateException he) {
					he.printStackTrace();
					flag = false;
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Number of Rows Effected is :: "+count);	
			}
			else {
				tx.rollback();
			}
		}
		//after updation
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();//dummy
			//prepare Query
			query = session.createQuery("FROM com.nt.entity.InsurancePolicy WHERE policyType LIKE :letters");
			//set Query param values
			query.setParameter("letters", "Life%");
			//execute Query
			List<InsurancePolicy> list = query.getResultList();
			System.out.println("List Size is :: "+list.size());
			System.out.println("After Updation");
			if(!list.isEmpty()) {
				list.forEach(System.out::println);;
			}
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}
		

	}

}
