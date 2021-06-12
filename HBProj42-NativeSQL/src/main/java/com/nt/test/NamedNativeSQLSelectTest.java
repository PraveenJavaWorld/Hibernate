package com.nt.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class NamedNativeSQLSelectTest {

	public static void main(String[] args) {
		
		//get session 
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		//====== Executing Named Native SQL Entity Query without mapping it to entity class ======
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get Access to NamedNativeSQL Query
			NativeQuery<Object[]> query = session.getNamedNativeQuery("GET_ALL_POICIES");
			//set query parameters
			query.setParameter(1, 10);
			query.setParameter(2, 30);
			//execute query
			List<Object[]> list = query.getResultList();
			//process the result
			list.forEach(row->{
				for(Object o:row) {
					System.out.print(o+"   ");
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
		

	}

}
