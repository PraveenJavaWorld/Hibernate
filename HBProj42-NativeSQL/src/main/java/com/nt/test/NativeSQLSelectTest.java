package com.nt.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class NativeSQLSelectTest {

	public static void main(String[] args) {
		
		//get session 
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		//====== Executing Native SQL Entity Query without mapping it to entity class ======
		/*try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create query
			NativeQuery<Object[]> query = session.createSQLQuery("SELECT * FROM INSURANCEPOLICY WHERE TENURE>=? AND TENURE<=?");
			//set query parameters
			query.setParameter(1, 10);
			query.setParameter(2, 30);
			//execute query
			List<Object[]> list = query.getResultList();
			//process the result
			list.forEach(row->{
				for(Object o:row) {
					System.out.print(o+" ");
				}
				System.out.println();
			});
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//====== Executing Native SQL Entity Query by mapping it to entity class ======
		/*try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create query
			NativeQuery<InsurancePolicy> query = session.createSQLQuery("SELECT * FROM INSURANCEPOLICY WHERE TENURE>=?1 AND TENURE<=?2");
			//set query parameters
			query.setParameter(1, 1);
			query.setParameter(2, 30);
			//map results with entity class
			query.addEntity(InsurancePolicy.class);//mandatory for this approach
			//execute query
			List<InsurancePolicy> list = query.getResultList();
			//process the result
			list.forEach(System.out::println);
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/
		
		//====== Executing Native SQL Scalar Query by selecting specific multiple column values ======
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create query
			NativeQuery<Object[]> query = session.createSQLQuery("SELECT POLICYID,POLICYTYPE,POLICYNAME FROM INSURANCEPOLICY WHERE COMPANY IN(:org1,:org2)");
			//set query parameters
			query.setParameter("org1", "LIC");
			query.setParameter("org2", "SBI");
			//map results with HB datatypes
			query.addScalar("POLICYID", StandardBasicTypes.INTEGER);
			query.addScalar("POLICYTYPE", StandardBasicTypes.STRING);
			query.addScalar("POLICYNAME", StandardBasicTypes.STRING);
			//execute query
			List<Object[]> list = query.getResultList();
			//process the result
			list.forEach(row->{
				for(Object o:row) {
					System.out.print(o+"   "+o.getClass());
				}
				System.out.println();
			});
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}
		
		//====== Executing Native SQL Scalar Query by selecting specific single column values ======
		/*try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create query
			NativeQuery<String> query = session.createSQLQuery("SELECT POLICYNAME FROM INSURANCEPOLICY WHERE COMPANY IN(:org1,:org2)");
			//set query parameters
			query.setParameter("org1", "LIC");
			query.setParameter("org2", "Bharati AXA");
			//execute query
			List<String> list = query.getResultList();
			//process the result
			list.forEach(System.out::println);
		}catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}*/

	}

}
