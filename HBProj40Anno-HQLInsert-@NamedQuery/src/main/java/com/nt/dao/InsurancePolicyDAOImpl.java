package com.nt.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nt.utility.HibernateUtil;

public class InsurancePolicyDAOImpl implements IInsurancePolicyDAO {
	
	//private static final String HQL_TRANSFER_INSURANCE_POLICIES = "INSERT INTO com.nt.entity.PremiumInsurancePolicy(policyId,policyName,policyType,company,tenure) SELECT i.policyId,i.policyName,i.policyType,i.company,i.tenure FROM com.nt.entity.InsurancePolicy as i WHERE i.tenure>=:min";

	Session session = null;
	Transaction tx = null;
	boolean flag = false;
	Query query = null;
	int count = 0;
	String msg = null;
	
	/*@Override
	public String transferPolicies(int tenure) {
		//get session
		session = HibernateUtil.getSession();
		try {
			//begin tx
			tx = session.beginTransaction();
			//prepate Query 
			query = session.createQuery(HQL_TRANSFER_INSURANCE_POLICIES);
			//set query param values
			query.setParameter("min", tenure);
			//execute the query
			count = query.executeUpdate();
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		finally {
			if(flag) {
				tx.commit();
				msg = count+ " Records are Copied";
			}
			else {
				tx.rollback();
				msg = "Records are not Copied";
			}
		}
		return msg;
	}//method
	*/
	
	@Override
	public String transferPolicies(int tenure) {
		//get session
		session = HibernateUtil.getSession();
		try {
			//begin tx
			tx = session.beginTransaction();
			//prepate Query 
			query = session.createNamedQuery("HQL_TRANSFER_INSURANCE_POLICIES");
			//set query param values
			query.setParameter("min", tenure);
			//execute the query
			count = query.executeUpdate();
			flag = true;
		} catch (HibernateException he) {
			he.printStackTrace();
			flag = false;
		}
		finally {
			if(flag) {
				tx.commit();
				msg = count+ " Records are Copied";
			}
			else {
				tx.rollback();
				msg = "Records are not Copied";
			}
		}
		return msg;
	}//method
}//class
