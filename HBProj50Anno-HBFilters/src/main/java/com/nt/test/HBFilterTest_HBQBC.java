package com.nt.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.BankAccount;
import com.nt.utility.HibernateUtil;

public class HBFilterTest_HBQBC {

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
			//Prepare and Execute HB-QBC
			Criteria criteria = session.createCriteria(BankAccount.class);
			//process result
			List<BankAccount> list = criteria.list();
			list.forEach(System.out::println);
			//Disable Filter
			session.disableFilter("BANKACCOUNT_STATUS_FILTER");
			//process result
			List<BankAccount> list1 = criteria.list();
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
