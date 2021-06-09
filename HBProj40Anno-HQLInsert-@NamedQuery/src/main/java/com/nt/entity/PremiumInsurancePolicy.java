package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "Premium_InsurancePolicy")
@NamedQuery(name = "HQL_TRANSFER_INSURANCE_POLICIES",query = "INSERT INTO com.nt.entity.PremiumInsurancePolicy(policyId,policyName,policyType,company,tenure) SELECT i.policyId,i.policyName,i.policyType,i.company,i.tenure FROM com.nt.entity.InsurancePolicy as i WHERE i.tenure>=:min")
public class PremiumInsurancePolicy implements Serializable{
	@Id
	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
}
