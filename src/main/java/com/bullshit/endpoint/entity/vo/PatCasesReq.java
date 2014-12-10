package com.bullshit.endpoint.entity.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatCasesReq {
	
	private String caseId;
	private String docId;
	private String patId;
	private String docSuggestion;
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
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
	public String getDocSuggestion() {
		return docSuggestion;
	}
	public void setDocSuggestion(String docSuggestion) {
		this.docSuggestion = docSuggestion;
	}

	
}
