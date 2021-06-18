package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import lombok.Data;

@Data
@Entity
@Table(name = "BANK_ACCOUNT")
@FilterDef(name = "BANKACCOUNT_STATUS_FILTER",
			parameters = { @ParamDef(name = "accountType1",type = "string"),
							@ParamDef(name = "accountType2",type = "string")})
@Filter(name = "BANKACCOUNT_STATUS_FILTER",condition = "STATUS NOT IN(:accountType1,:accountType2)")
public class BankAccount implements Serializable {
	
	@Id
	@Column(name = "ACCOUNTNUMBER")
	private Integer accountNumber;
	@Column(name = "HOLDERNAME")
	private String holderName;
	@Column(name = "BALANCE")
	private Float balance;
	@Column(name = "STATUS")
	private String status;

}
