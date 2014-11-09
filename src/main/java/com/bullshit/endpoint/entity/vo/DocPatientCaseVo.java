package com.bullshit.endpoint.entity.vo;

import java.util.List;

import com.bullshit.endpoint.entity.PatientCaseBean;

public class DocPatientCaseVo extends BaseVo {

	private List<PatientCaseBean> patientCaseBeanList;

	public List<PatientCaseBean> getPatientCaseBeanList() {
		return patientCaseBeanList;
	}

	public void setPatientCaseBeanList(List<PatientCaseBean> patientCaseBeanList) {
		this.patientCaseBeanList = patientCaseBeanList;
	}
}
