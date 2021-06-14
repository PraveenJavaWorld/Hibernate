package com.nt.test;

import java.util.List;

import com.nt.dao.IInsurancePolicyDAO;
import com.nt.dao.InsurancePolicyDAOImpl;
import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

public class ProcedureCallTest {

	public static void main(String[] args) {
		
		IInsurancePolicyDAO dao = null;
		List<InsurancePolicy> list = null;
		String results[] = null;
		//get dao
		dao = new InsurancePolicyDAOImpl();
		list = dao.getPoliciesByTenure(10, 35);
		list.forEach(System.out::println);
		System.out.println("===============================");
		results = dao.getPolicyByID(1005);
		System.out.println(results[0]+"  "+results[1]+"  "+results[2]+"  ");
		//close objs
		HibernateUtil.closeSessionFactory();

	}

}
