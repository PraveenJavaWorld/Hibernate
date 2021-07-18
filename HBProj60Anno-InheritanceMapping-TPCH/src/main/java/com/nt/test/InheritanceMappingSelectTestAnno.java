package com.nt.test;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.CardPayment;
import com.nt.entity.ChequePayment;
import com.nt.entity.Payment;
import com.nt.utility.HibernateUtil;

public class InheritanceMappingSelectTestAnno {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		
		//get Session object
		session = HibernateUtil.getSession();
		
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get object
			System.out.println("From Super Class (Payment)");
			Query query1 = session.createQuery("FROM com.nt.entity.Payment");
			List<Payment> list1 = query1.getResultList();
			list1.forEach(System.out::println);
			
			System.out.println("From Sub Class (CardPayment)");
			Query query2 = session.createQuery("FROM com.nt.entity.CardPayment");
			List<CardPayment> list2 = query2.getResultList();
			list2.forEach(System.out::println);
			
			System.out.println("From Sub Class (ChequePayment)");
			Query query3 = session.createQuery("FROM com.nt.entity.ChequePayment");
			List<ChequePayment> list3 = query3.getResultList();
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
