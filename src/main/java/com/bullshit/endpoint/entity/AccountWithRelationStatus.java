package com.bullshit.endpoint.entity;

public class AccountWithRelationStatus extends Account {
	
     private String relationStatus;

	public String getRelationStatus() {
		return relationStatus;
	}

	public void setRelationStatus(String relationStatus) {
		this.relationStatus = relationStatus;
	}
     
}