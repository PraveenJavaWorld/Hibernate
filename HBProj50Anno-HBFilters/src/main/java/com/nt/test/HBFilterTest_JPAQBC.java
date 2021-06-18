package com.nt.test;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.BankAccount;
import com.nt.utility.HibernateUtil;

public class HBFilterTest_JPAQBC {

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
			//Prepare and Execute JPA-QBC
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<BankAccount> ctQuery = builder.createQuery(BankAccount.class);
			Root<BankAccount> root = ctQuery.from(BankAccount.class);
			ctQuery.select(root);
			//Prepare Query obj
			Query query = session.createQuery(ctQuery);
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
