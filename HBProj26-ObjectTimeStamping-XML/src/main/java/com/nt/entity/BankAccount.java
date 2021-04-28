package com.nt.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BankAccount implements Serializable {
	
	private Long acno;
	private String holder;
	private Double balance;
	private String type;
	private LocalDateTime openingDate;
	private Timestamp lastUpdated;//for timestamping

}
