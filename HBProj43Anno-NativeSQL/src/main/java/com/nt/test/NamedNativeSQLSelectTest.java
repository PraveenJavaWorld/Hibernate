package com.nt.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class NamedNativeSQLSelectTest {

	public static void main(String[] args) {
		//get session object
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		boolean flag = false;
		int count = 0;
		//========= Select test by Entity class =====
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get Access to NamedNativeSQL Query
			NativeQuery<InsurancePolicy> nquery = session.getNamedNativeQuery("GET_ALL_POLICIES");
			//execute query
			List<InsurancePolicy> list = nquery.getResultList();
			//process the result
			list.forEach(System.out::println);
			
			System.out.println("===============================================================");
			
			//get Access to NamedNativeSQL Query
			NativeQuery<Object[]> query = session.getNamedNativeQuery("GET_SPECIFIC_POLICIES_BY_COMPANY");
			//set parameters
			query.setParameter("org1", "LIC");
			query.setParameter("org2", "SBI");
			//execute query
			List<Object[]> list1 = query.getResultList();
			//process the result
			list1.forEach(row->{
				for(Object o:row) {
					System.out.print(o+"  ");
				}
				System.out.println();
			});

			System.out.println("===============================================================");
			
			//get Access to NamedNativeSQL Query
			NativeQuery<InsurancePolicy> squery = session.getNamedNativeQuery("DELETE_POLICY");
			//set parameters
			squery.setParameter(1, 1006);
			//execute query
			count = squery.executeUpdate();
			flag = true;
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
				System.out.println("Number of Records Deleted is "+count);
			}
			else {
				tx.rollback();
				System.out.println("No Records are Deleted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}

	}

}
