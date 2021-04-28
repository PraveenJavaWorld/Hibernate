package com.nt.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "BANKACCOUNT1")
public class BankAccount implements Serializable {
	
	@Id
	@SequenceGenerator(name = "gen1",sequenceName = "ACNO_SEQ1",initialValue = 100001,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	@Type(type = "long")
	@Column(name = "ACCOUNTNUMBER")
	private Long acno;
	
	@Type(type = "string")
	@Column(name = "ACCOUNTHOLDER",length = 40)
	private String holder;
	
	@Type(type = "double")
	@Column(name = "BALANCE")
	private Double balance;
	
	@Type(type = "string")
	@Column(name = "TYPE",length = 20)
	private String type;
	
	@Column(name = "OPENINGDATE")
	@CreationTimestamp
	private LocalDateTime openingDate;//to just hold record creation date and time 
	
	@Column(name = "lastUpdated")
	@UpdateTimestamp
	private Timestamp lastUpdated;//to hold record date and time when lastly updated 
	

	@Type(type = "int")
	@Column(name = "VERSIONCOUNT")
	@Version
	private Integer versionCount;

}
