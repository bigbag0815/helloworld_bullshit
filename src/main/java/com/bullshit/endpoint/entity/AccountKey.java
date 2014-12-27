package com.bullshit.endpoint.entity;

public class AccountKey extends BaseKey {
	private String docId;
	
	private String patId;
	
	private String patStatus;
	
	private String docDepartmentName;
	
	private String keyword;
	
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : "%" + keyword.trim() + "%";
	}
}