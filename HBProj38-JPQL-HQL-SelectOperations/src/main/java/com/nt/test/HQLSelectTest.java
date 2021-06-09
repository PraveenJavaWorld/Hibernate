package com.nt.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class HQLSelectTest {

	public static void main(String[] args) {

		// get Session obj
		Session session = HibernateUtil.getSession();
		//==============Executing HQL Select Entity Query using list() or getResultList()===========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query<InsurancePolicy> query = session.createQuery("from com.nt.entity.InsurancePolicy");
			//execute Query
			List<InsurancePolicy> list = query.list(); 
			//process the list
			list.forEach(policy->{   //java 8 forEach method with Lambda Expression
				System.out.println(policy);
			});
			//process the list
			list.forEach(System.out::println); // java 8 forEach method + Method Reference
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/

		// ============== Executing HQL Select Entity Query using iterate() ===========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query<InsurancePolicy> query = session.createQuery("from com.nt.entity.InsurancePolicy");
			//execute Query
			Iterator<InsurancePolicy> it = query.iterate();
			while(it.hasNext()) {
				//InsurancePolicy policy = it.next();
				//System.out.println(policy);
				System.out.println(it.next());
			}
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/

		// ============== Executing HQL Select Entity Query with JPA style positional params ===========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query<InsurancePolicy> query = session.createQuery("FROM com.nt.entity.InsurancePolicy WHERE policyId>=?1 AND policyId<=?2");
			//set Query param values
			//query.setInteger(1, 1001);
			//query.setInteger(2, 1003); // deprecated from hb 5.2
			//set Query param values
			query.setParameter(1, 1001L);
			query.setParameter(2, 1005L);
			//execute Query
			List<InsurancePolicy> list = query.getResultList();
			list.forEach(System.out::println);
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		// ============== Executing HQL Select Entity Query with Named params ===========
		try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query<InsurancePolicy> query = session.createQuery("FROM com.nt.entity.InsurancePolicy WHERE company IN(:org1,:org2)");
			//set Query param values
			query.setParameter("org1", "LIC");
			query.setParameter("org2", "Bharati AXA");
			//execute Query
			List<InsurancePolicy> list = query.getResultList();
			list.forEach(System.out::println);
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}

	}

}
