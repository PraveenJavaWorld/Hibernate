package com.nt.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Project implements Serializable{
	
	private long projId;
	private String projName;
	private int teamSize;
	
	//getters and setters
	public long getProjId() {
		return projId;
	}
	public void setProjId(long projId) {
		this.projId = projId;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public int getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(int teamSize) {
		this.teamSize = teamSize;
	}
	
	@Override
	public String toString() {
		return "Project [projId=" + projId + ", projName=" + projName + ", teamSize=" + teamSize + "]";
	}
	
	
	

}
