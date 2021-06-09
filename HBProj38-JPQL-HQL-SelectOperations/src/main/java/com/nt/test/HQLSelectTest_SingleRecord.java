package com.nt.test;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class HQLSelectTest_SingleRecord {

	public static void main(String[] args) {

		// get Session obj
		Session session = HibernateUtil.getSession();
		//============ HQL SELECT SCALAR Query that gives single record ========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query query = session.createQuery("FROM com.nt.entity.InsurancePolicy WHERE policyId=:id");
			//set Query param values
			query.setParameter("id", 1005L);
			//execute Query
			List<InsurancePolicy> list = query.getResultList();
			System.out.println(list.size());
			//if(!list.isEmpty())
				//System.out.println(list); or
			if(!list.isEmpty()) {
				InsurancePolicy policy = list.get(0);
				System.out.println(policy);
			}
			else {
				System.out.println("Record Not Found");
			}
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//============ HQL SELECT SCALAR Query(multiple specific column values) that gives single record ========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query query = session.createQuery("SELECT policyId,company FROM com.nt.entity.InsurancePolicy WHERE policyId=:id");
			//set Query param values
			query.setParameter("id", 1002L);
			//execute Query
			List<Object[]> list = query.getResultList();
			System.out.println(list.size());
			if(!list.isEmpty()) {
				Object row[] = list.get(0);
				System.out.println(row[0]+" "+row[1]);
			}
			else {
				System.out.println("Record Not Found");
			}
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//============ HQL SELECT SCALAR Query(aggregate function) that gives single record ========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query query = session.createQuery("SELECT COUNT(*) FROM com.nt.entity.InsurancePolicy");
			//execute Query
			List<Long> list = query.getResultList();
			System.out.println(list.size());
			if(!list.isEmpty()) {
				Long count = list.get(0);
				System.out.println("Records Count is :: "+count);
			}
			else {
				System.out.println("Record Not Found");
			}
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//============ HQL SELECT SCALAR Query that gives single record using uniqueResult() ========
		/*try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query query = session.createQuery("FROM com.nt.entity.InsurancePolicy WHERE policyId=:id");
			//set Query param values
			query.setParameter("id", 1003L);
			//execute Query
			InsurancePolicy policy = (InsurancePolicy) query.uniqueResult();
			if(policy!=null) {
				System.out.println(policy);
			}
			else {
				System.out.println("Record Not Found");
			}
		} catch (HibernateException he) {
					he.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//============ HQL SELECT SCALAR Query that gives single record using uniqueResultOptional() ========
		try {
			Transaction tx = session.beginTransaction();//dummy tx because we enabled contextual session
			//prepare Query
			Query query = session.createQuery("FROM com.nt.entity.InsurancePolicy WHERE policyId=:id");
			//set Query param values
			query.setParameter("id", 1004L);
			//execute Query
			Optional<InsurancePolicy> opt = query.uniqueResultOptional();
			if(opt.isPresent()) {
				//System.out.println(opt); or
				InsurancePolicy policy = opt.get();
				System.out.println(policy);
			}
			else {
				System.out.println("Record Not Found");
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
