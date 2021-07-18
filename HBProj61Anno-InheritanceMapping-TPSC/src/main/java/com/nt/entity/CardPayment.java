package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Entity
@Table(name = "INH_CARDPAYMENT_TPSC")
@PrimaryKeyJoinColumn(name = "PAYMENT_ID",referencedColumnName = "PID")
public class CardPayment extends Payment {
	@Type(type = "long")
	private Long cardNumber;
	@Column(length = 10)
	@Type(type = "string")
	private String cardType;
	@Column(length = 10)
	@Type(type = "string")
	private String paymentGateway;

}
