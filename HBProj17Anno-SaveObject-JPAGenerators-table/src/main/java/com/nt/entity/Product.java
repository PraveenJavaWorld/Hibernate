package com.nt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
public class Product {

	// bean properties
	@Id
	@TableGenerator(name = "gen1",
					pkColumnName = "ID_COL",
					valueColumnName = "ID_VAL",
					pkColumnValue = "PID",
					table = "ID_TAB",
					initialValue = 10,
					allocationSize = 10)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.TABLE) 
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

}
