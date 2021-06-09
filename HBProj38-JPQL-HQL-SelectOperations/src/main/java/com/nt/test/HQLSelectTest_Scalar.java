package com.nt.test;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nt.utility.HibernateUtil;

public class HQLSelectTest_Scalar {

	public static void main(String[] args) {

		// get Session obj
		Session session = HibernateUtil.getSession();
		//============ HQL SELECT SCALAR Query that gives  multiple specific column values ========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query query = session.createQuery("SELECT policyId,policyName,tenure FROM com.nt.entity.InsurancePolicy WHERE policyType LIKE :letters");
			//set Query param values
			query.setParameter("letters", "%Ins%");
			//execute Query
			List<Object[]> list = query.getResultList();
			list.forEach(row->{
				for(Object obj:row) {
					System.out.print(obj+" ");//for each of column value there should be space
				}
				System.out.println();//after record next record should display in next line
			});
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//============ HQL SELECT SCALAR Query that gives single specific column value using list() or getResultList() ========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query query = session.createQuery("SELECT policyName FROM com.nt.entity.InsurancePolicy WHERE tenure>=:max");
			//set Query params values
			query.setParameter("max", 10);
			//execute Query
			List<String> list = query.getResultList();
			list.forEach(System.out::println);
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//============ HQL SELECT SCALAR Query that gives single specific column value using iterate() ========
		try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query query = session.createQuery("SELECT policyName FROM com.nt.entity.InsurancePolicy WHERE tenure>=:max");
			//set Query params values
			query.setParameter("max", 10);
			//execute Query
			Iterator<String> it = query.iterate();
			while(it.hasNext()) {
				System.out.println(it.next());
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
