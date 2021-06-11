package com.nt.dao;

import java.util.List;

import com.nt.entity.InsurancePolicy;

public interface IInsurancePolicyDAO {
	
	public List<InsurancePolicy> getPageDate(int pageSize,int startPosition);
	public long getTotalRecordsCount();

}
