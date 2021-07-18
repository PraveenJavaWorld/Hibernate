package com.nt.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Entity
@Table(name = "INH_CHEQUEPAYMENT_TPCC")
public class ChequePayment extends Payment {
	
	@Type(type = "long")
	private Long chequeNumber;
	@Column(length = 10)
	@Type(type = "string")
	private String chequeType;
	private LocalDate expiryDate;

}
