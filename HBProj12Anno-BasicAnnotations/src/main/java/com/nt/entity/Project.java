package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class Project implements Serializable {
	
	private Long projId;
	private String projName;
	private Integer teamsize;
	private String company;
	
	@Id
	@Column(name = "PROJID")
	public Long getProjId() {
		return projId;
	}
	public void setProjId(Long projId) {
		this.projId = projId;
	}
	@Column (name = "PROJNAME")
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	@Column(name = "TEAMSIZE")
	public Integer getTeamsize() {
		return teamsize;
	}
	public void setTeamsize(Integer teamsize) {
		this.teamsize = teamsize;
	}
	@Column(name = "COMPANY")
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
	@Override
	public String toString() {
		return "Project [company=" + company + ", projId=" + projId + ", projName=" + projName + ", teamsize="
				+ teamsize + "]";
	}
	

}
