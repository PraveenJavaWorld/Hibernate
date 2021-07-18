package com.nt.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Person;
import com.nt.utility.HibernateUtil;

public class InheritanceMappingSelectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		
		//get Session obj
		session = HibernateUtil.getSession();
		
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Getting Records using Super Class(Person)
			System.out.println("Using Super Class(Person)");
			Query query1 = session.createQuery("FROM com.nt.entity.Person");
			List<Person> list1 = query1.getResultList();
			list1.forEach(System.out::println);
			//Getting Records using subclass1 (Student)
			System.out.println("Using subclass1 (Student)");
			Query query2 = session.createQuery("FROM com.nt.entity.Student");
			List<Person> list2 = query2.getResultList();
			list2.forEach(System.out::println);
			//Getting Records using subclass2 (Employee)
			System.out.println("Using subclass2 (Employee)");
			Query query3 = session.createQuery("FROM com.nt.entity.Employee");
			List<Person> list3 = query3.getResultList();
			list3.forEach(System.out::println);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
