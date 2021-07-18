package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("card")
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
