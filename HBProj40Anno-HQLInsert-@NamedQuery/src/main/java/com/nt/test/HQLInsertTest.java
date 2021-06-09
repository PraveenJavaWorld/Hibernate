package com.nt.test;

import com.nt.dao.IInsurancePolicyDAO;
import com.nt.dao.InsurancePolicyDAOImpl;
import com.nt.utility.HibernateUtil;

public class HQLInsertTest {

	public static void main(String[] args) {
		
		//get DAO
		IInsurancePolicyDAO dao = null;
		//create DAO class object
		dao = new InsurancePolicyDAOImpl();
		System.out.println(dao.transferPolicies(1));
		
		//close objs
		HibernateUtil.closeSessionFactory();

	}

}
