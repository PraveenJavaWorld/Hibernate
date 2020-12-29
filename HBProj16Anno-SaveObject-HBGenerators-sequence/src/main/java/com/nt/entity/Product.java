package com.nt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
@Entity
public class Product {

	// bean properties
	/*@Id
	@GenericGenerator(name = "gen1",strategy = "sequence")-->generates sequence with name "gen1"
	@GeneratedValue(generator = "gen1")*/
	
	@Id
	@GenericGenerator(name = "gen1",strategy = "sequence",
							parameters = @Parameter(name="sequence_name",value = "PRODUCT_SEQUENCE"))
	@GeneratedValue(generator = "gen1")
	private Integer pid;
	private String pname;
	private Float price;
	private Float qty;

}
