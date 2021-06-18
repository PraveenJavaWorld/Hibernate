package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name = "BANK_ACCOUNT")
@SQLDelete(sql = "UPDATE BANK_ACCOUNT SET STATUS='CLOSED' WHERE ACCOUNTNUMBER=?")
@Where(clause = "STATUS NOT IN('CLOSED','BLOCKED')")
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
