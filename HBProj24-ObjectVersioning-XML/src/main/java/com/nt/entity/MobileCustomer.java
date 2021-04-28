package com.nt.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class MobileCustomer implements Serializable {
	
	private Integer cno;
	private String cname;
	private long mobileNumber;
	private String callerTune;
	private Integer versionCount;

}
