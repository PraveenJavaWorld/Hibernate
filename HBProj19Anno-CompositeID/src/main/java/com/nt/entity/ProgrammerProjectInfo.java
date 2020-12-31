package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "PROGRAMMERS_PROJECTS_WORK")
public class ProgrammerProjectInfo implements Serializable {
	@EmbeddedId
	private ProgrammerProjectID id;
	@Column(name = "PNAME")
	private String pname;
	@Column(name = "PROJECT_NAME")
	private String projName;
	@Column(name = "DEPARTMENT_NO")
	private Integer deptNo;

}
