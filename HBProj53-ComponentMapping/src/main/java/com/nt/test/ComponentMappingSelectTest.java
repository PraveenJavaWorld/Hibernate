package com.nt.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.StudentInfo;
import com.nt.utility.HibernateUtil;

public class ComponentMappingSelectTest {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		//get Session
		session = HibernateUtil.getSession();
				
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Load objs
			Query query = session.createQuery("FROM com.nt.entity.StudentInfo");
			List<StudentInfo> list = query.getResultList();
			list.forEach(System.out::println);
			
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
