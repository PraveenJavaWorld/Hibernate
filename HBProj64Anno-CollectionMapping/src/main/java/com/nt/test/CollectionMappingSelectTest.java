package com.nt.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Employee;
import com.nt.utility.HibernateUtil;

public class CollectionMappingSelectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		
		//get Session obj
		session = HibernateUtil.getSession();
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//prepare Query
			Query query = session.createQuery("FROM com.nt.entity.Employee");
			List<Employee> list = query.getResultList();
			list.forEach(System.out::println);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}

	}

}
