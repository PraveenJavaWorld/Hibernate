package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.nt.utility.HibernateUtil;

public class NativeSQLNonSelectTest {

	public static void main(String[] args) {
		
		//get session 
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		int count = 0;
		boolean flag = false;
		//====== Inserting Record with Direct values ======
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//create NativeQuery object having Native SQL query
			NativeQuery query = session.createSQLQuery("INSERT INTO INSURANCEPOLICY VALUES(?,?,?,?,?)");
			//set parameters
			query.setParameter(1, 1006);
			query.setParameter(2, "Jeevan Anand ++");
			query.setParameter(3, "Life Insurance");
			query.setParameter(4, "LIC");
			query.setParameter(5, 25);
			//execute query;
			count = query.executeUpdate();
			flag = true;
		}catch (HibernateException he) {
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
				System.out.println("Record Inserted Successfully " +count);
			}
			else {
				tx.rollback();
				System.out.println("Record Not Inserted");
			}
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
