package com.bullshit.endpoint.entity.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatReq extends BaseReq {
	private String patAllergyDrug;
	private String patEmergPerson;
	private String patEmergPhone;
	private String patPastHistory;
	private String patStatusFlg;
	
	public String getPatAllergyDrug() {
		return patAllergyDrug;
	}
	public void setPatAllergyDrug(String patAllergyDrug) {
		this.patAllergyDrug = patAllergyDrug;
	}
	public String getPatEmergPerson() {
		return patEmergPerson;
	}
	public void setPatEmergPerson(String patEmergPerson) {
		this.patEmergPerson = patEmergPerson;
	}
	public String getPatEmergPhone() {
		return patEmergPhone;
	}
	public void setPatEmergPhone(String patEmergPhone) {
		this.patEmergPhone = patEmergPhone;
	}
	public String getPatPastHistory() {
		return patPastHistory;
	}
	public void setPatPastHistory(String patPastHistory) {
		this.patPastHistory = patPastHistory;
	}
	public String getPatStatusFlg() {
		return patStatusFlg;
	}
	public void setPatStatusFlg(String patStatusFlg) {
		this.patStatusFlg = patStatusFlg;
	}
	
	
}
