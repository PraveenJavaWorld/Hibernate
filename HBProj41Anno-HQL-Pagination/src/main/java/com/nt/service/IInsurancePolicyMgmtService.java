package com.nt.service;

import java.util.List;

import com.nt.dto.InsurancePolicyDTO;

public interface IInsurancePolicyMgmtService {
	
	public List<InsurancePolicyDTO> fetchPageData(int pageSize,int pageNo);
	public long fetchPagesCount(int pageSize);

}
