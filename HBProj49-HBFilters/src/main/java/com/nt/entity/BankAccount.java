package com.nt.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BankAccount implements Serializable {
	
	private Integer accountNumber;
	private String holderName;
	private Float balance;
	private String status;

}
