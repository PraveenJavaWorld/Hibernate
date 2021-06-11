package com.nt.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class HQLPaginationTest {

	public static void main(String[] args) {
		//get Session Object
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try {
			//begin tx
			tx = session.beginTransaction();//dummy
			//create Query
			Query query = session.createQuery("from com.nt.entity.InsurancePolicy");
			//pagination Settings
			query.setFirstResult(0);//page start position
			query.setMaxResults(2);//page size
			//execute Query
			List<InsurancePolicy> list = query.getResultList();
			//process the results
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
