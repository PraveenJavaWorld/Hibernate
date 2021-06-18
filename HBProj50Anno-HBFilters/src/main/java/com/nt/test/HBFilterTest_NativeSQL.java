package com.nt.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.nt.entity.BankAccount;
import com.nt.utility.HibernateUtil;

public class HBFilterTest_NativeSQL {

	//In NativeSQL Filters will not Work
	public static void main(String[] args) {
		
		Session session = null;
		Transaction tx = null;
		//get session obj
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//Enable Filter
			Filter filter = session.enableFilter("BANKACCOUNT_STATUS_FILTER");
			//set filter parameters
			filter.setParameter("accountType1", "CLOSED");
			filter.setParameter("accountType2", "BLOCKED");
			//Prepare and Execute NativeSQL
			NativeQuery query = session.createNativeQuery("SELECT * FROM BANK_ACCOUNT");
			query.addEntity(BankAccount.class);
			//process result
			List<BankAccount> list = query.getResultList();
			list.forEach(System.out::println);
			//Disable Filter
			session.disableFilter("BANKACCOUNT_STATUS_FILTER");
			//process result
			List<BankAccount> list1 = query.getResultList();
			list1.forEach(System.out::println);
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
