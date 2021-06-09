package com.nt.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class PremiumInsurancePolicy implements Serializable{
	
	private Long policyId;
	private String policyName;
	private String policyType;
	private String company;
	private Integer tenure;
	
}
