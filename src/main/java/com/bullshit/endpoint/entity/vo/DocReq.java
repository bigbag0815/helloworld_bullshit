package com.bullshit.endpoint.entity.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class DocReq extends BaseReq {
	private String docTitle;
	private String docProfessional;
	private String docDepartmentName;
	private String docDescription;
	private String docHospital;
	private Date mtime;
	public String getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	public String getDocProfessional() {
		return docProfessional;
	}
	public void setDocProfessional(String docProfessional) {
		this.docProfessional = docProfessional;
	}
	public String getDocDepartmentName() {
		return docDepartmentName;
	}
	public void setDocDepartmentName(String docDepartmentName) {
		this.docDepartmentName = docDepartmentName;
	}
	public String getDocDescription() {
		return docDescription;
	}
	public void setDocDescription(String docDescription) {
		this.docDescription = docDescription;
	}
	public String getDocHospital() {
		return docHospital;
	}
	public void setDocHospital(String docHospital) {
		this.docHospital = docHospital;
	}
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	
}
