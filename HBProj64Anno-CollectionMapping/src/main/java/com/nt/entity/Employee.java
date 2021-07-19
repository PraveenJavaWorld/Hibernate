package com.nt.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ListIndexBase;
import org.hibernate.annotations.Type;

import lombok.Data;

@Data
@Entity
@Table(name = "CM_EMPLOYEE_COLLECTION")
public class Employee implements Serializable {
	
	@Id
	@GeneratedValue
	@Type(type = "int")
	private Integer eno;
	
	@Column(length = 15)
	@Type(type = "string")
	private String ename;
	
	@Column(length = 15)
	@Type(type = "string")
	private String address;
	
	@ElementCollection
	@CollectionTable(name = "CM_EMPLOYEE_FRIENDS",
					joinColumns = @JoinColumn(name = "emp_id",referencedColumnName = "eno"))
	@OrderColumn(name = "FRIEND_NUMBER")
	@ListIndexBase(value = 1)
	private List<String> friendsList;
	
	@ElementCollection
	@CollectionTable(name = "CM_EMPLOYEE_RELATIVES",
					joinColumns = @JoinColumn(name = "emp_id",referencedColumnName = "eno"))
	private List<String> relativesList;
	
	@ElementCollection
	@CollectionTable(name = "CM_EMPLOYEE_PHONES",
					joinColumns = @JoinColumn(name = "emp_id",referencedColumnName = "eno"))
	private Set<Long> phoneNumbers;
	
	@ElementCollection
	@CollectionTable(name = "CM_EMPLOYEE_BANKACCOUNTS",
					joinColumns = @JoinColumn(name = "emp_id",referencedColumnName = "eno"))
	@MapKeyColumn(name = "BANK_NAME",length = 15)
	private Map<String, Long> bankAccounts;

}
