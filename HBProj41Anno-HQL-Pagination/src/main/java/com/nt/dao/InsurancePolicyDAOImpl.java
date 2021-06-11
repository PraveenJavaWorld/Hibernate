package com.nt.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class InsurancePolicyDAOImpl implements IInsurancePolicyDAO {

	@Override
	public List<InsurancePolicy> getPageDate(int pageSize, int startPosition) {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		List<InsurancePolicy> list = null;
		long count = 0;
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get access to NamedQuery
			query = session.getNamedQuery("GET_ALL_POLICIES");
			//perform Pagination
			query.setFirstResult(startPosition);
			query.setMaxResults(pageSize);
			//execute query
			list = query.getResultList();
		} catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}//method 1

	@Override
	public long getTotalRecordsCount() {
		Session session = null;
		Transaction tx = null;
		Query query = null;
		List<Long> list = null;
		long count = 0;
		//get Session
		session = HibernateUtil.getSession();
		try {
			if(!session.getTransaction().isActive())
				tx = session.beginTransaction();
			//get access to NamedQuery
			query = session.getNamedQuery("GET_POLICIES_COUNT");
			//execute query
			list = query.getResultList();
			count = list.get(0);
		} catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}//method 2

}//class
