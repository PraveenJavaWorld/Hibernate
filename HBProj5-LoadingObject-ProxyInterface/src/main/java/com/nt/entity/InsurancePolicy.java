  package com.nt.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class InsurancePolicy implements Serializable,IInsurancePolicy {
	
	//bean properties
	public Long policyId;
	public String policyName;
	public String policyType;
	public String company;
	public Integer tenure;
	
	//getters & setters
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getTenure() {
		return tenure;
	}
	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}
	
	
	@Override
	public String toString() {
		return "InsurancePolicy Details : \npolicyId=" + policyId + ", \npolicyName=" + policyName + ", \npolicyType=" + policyType
				+ ", \ncompany=" + company + ", \ntenure=" + tenure;
	}
	
	
	

}
