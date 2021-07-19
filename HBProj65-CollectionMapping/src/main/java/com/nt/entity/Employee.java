package com.nt.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class Employee implements Serializable {
	
	private Integer eno;
	
	private String ename;
	
	private String address;
	
	private List<String> friendsList;
	
	private List<String> relativesList;
	
	private Set<Long> phoneNumbers;
	
	private Map<String, Long> bankAccounts;

}
