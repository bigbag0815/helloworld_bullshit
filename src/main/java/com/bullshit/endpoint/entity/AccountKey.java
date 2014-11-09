package com.bullshit.endpoint.entity;

public class AccountKey {
	private String docId;
	
	private String patStatus;
	
	private String docDepartmentName;
	
	private int limit;
	
	private int offset;

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

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}