package com.bullshit.endpoint.entity.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DocPatRelationReq {
	private String docId;
	private String patId;
	private String hxChatGroupId;
	
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

	public String getHxChatGroupId() {
		return hxChatGroupId;
	}

	public void setHxChatGroupId(String hxChatGroupId) {
		this.hxChatGroupId = hxChatGroupId;
	}

}
