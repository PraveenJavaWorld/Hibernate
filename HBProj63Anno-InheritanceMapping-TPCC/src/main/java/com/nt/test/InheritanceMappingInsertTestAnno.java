package com.nt.test;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.CardPayment;
import com.nt.entity.ChequePayment;
import com.nt.utility.HibernateUtil;

public class InheritanceMappingInsertTestAnno {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		boolean flag = false;
		CardPayment payment1 = null;
		ChequePayment payment2 = null;
		
		//get Session object
		session = HibernateUtil.getSession();
		
		try {
			//begin tx
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//prepare objs
			payment1 = new CardPayment();
			payment1.setAmount(2000.0F);
			payment1.setCardNumber(123456789L);
			payment1.setCardType("Credit");
			payment1.setPaymentGateway("VISA");
			
			payment2 = new ChequePayment();
			payment2.setAmount(10000.0F);
			payment2.setChequeNumber(987654L);
			payment2.setChequeType("self");
			payment2.setExpiryDate(LocalDate.of(2021, 03, 18));
			
			//save objs
			session.save(payment1);
			session.save(payment2);
			flag = true;
		} catch (HibernateException he) {
			flag = false;
			he.printStackTrace();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Records are Inserted");
			}
			else {
				tx.rollback();
				System.out.println("Records are Not Inserted");
			}
			//close objs
			HibernateUtil.closeSessionFactory();
		}//finally

	}//main

}//class
