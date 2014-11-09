package com.bullshit.endpoint.entity;

public class AccountKey extends BaseKey {
	private String docId;
	
	private String patStatus;
	
	private String docDepartmentName;
	
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getPatStatus() {
		return patStatus;
	}

	public void setPatStatus(String patStatus) {
		this.patStatus = patStatus;
	}

	public String getDocDepartmentName() {
		return docDepartmentName;
	}

	public void setDocDepartmentName(String docDepartmentName) {
		this.docDepartmentName = docDepartmentName;
	}
}