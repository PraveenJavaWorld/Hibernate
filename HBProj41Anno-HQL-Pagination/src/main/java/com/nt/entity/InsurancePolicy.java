package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import lombok.Data;

@Data
@Entity
@NamedQuery(name = "GET_POLICIES_COUNT",query = "SELECT COUNT(*) FROM com.nt.entity.InsurancePolicy")
@NamedQuery(name = "GET_ALL_POLICIES",query = "FROM com.nt.entity.InsurancePolicy")
public class InsurancePolicy implements Serializable{
	
	@Id
	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;

}
