package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

import lombok.Data;

@Data
@Entity
@NamedNativeQuery(name = "GET_ALL_POLICIES",query = "SELECT * FROM INSURANCEPOLICY",resultClass = InsurancePolicy.class)
@NamedNativeQuery(name = "GET_SPECIFIC_POLICIES_BY_COMPANY",query = "SELECT POLICYID,POLICYTYPE,POLICYNAME FROM INSURANCEPOLICY WHERE COMPANY IN(:org1,:org2)")
@NamedNativeQuery(name = "DELETE_POLICY",query = "DELETE FROM INSURANCEPOLICY WHERE POLICYID=?")
public class InsurancePolicy implements Serializable{
	
	@Id
	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
}
