package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import com.nt.dao.IInsurancePolicyDAO;
import com.nt.dao.InsurancePolicyDAOImpl;
import com.nt.dto.InsurancePolicyDTO;
import com.nt.entity.InsurancePolicy;

public class InsurancePolicyMgmtServiceImpl implements IInsurancePolicyMgmtService {
	
	private IInsurancePolicyDAO dao;
	
	public InsurancePolicyMgmtServiceImpl() {
		dao = new InsurancePolicyDAOImpl();
	}

	@Override
	public List<InsurancePolicyDTO> fetchPageData(int pageSize, int pageNo) {
		int startPosition = 0;
		List<InsurancePolicy> listEntities = null;
		List<InsurancePolicyDTO> listDTO = new ArrayList<>();
		//get StartPostion of record in db table for every page (based on given pageNo)
		startPosition = (pageNo*pageSize)-pageSize;
		//use DAO
		listEntities = dao.getPageDate(pageSize, startPosition);
		//convert listEntities into listDTOs
		listEntities.forEach(entity->{
			InsurancePolicyDTO dto = new InsurancePolicyDTO();
			dto.setPolicyId(entity.getPolicyId());
			dto.setPolicyName(entity.getPolicyName());
			dto.setPolicyType(entity.getPolicyType());
			dto.setCompany(entity.getCompany());
			dto.setTenure(entity.getTenure());
			dto.setSerialNo(listDTO.size()+1);
			listDTO.add(dto);
		});
		return listDTO;
	}

	@Override
	public long fetchPagesCount(int pageSize) {
		long recordCount = 0;
		long pagesCount = 0;
		recordCount = dao.getTotalRecordsCount();
		pagesCount = recordCount/pageSize;
		if(recordCount%pageSize!=0)
			pagesCount++;
		return pagesCount;
	}

}
