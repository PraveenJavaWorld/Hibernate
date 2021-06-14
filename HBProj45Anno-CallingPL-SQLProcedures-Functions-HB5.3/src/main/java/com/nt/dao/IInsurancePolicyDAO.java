package com.nt.dao;

import java.util.List;

import com.nt.entity.InsurancePolicy;

public interface IInsurancePolicyDAO {
	
	public List<InsurancePolicy> getPoliciesByTenure(int min,int max);
	public String[] getPolicyByID(int id);

}
