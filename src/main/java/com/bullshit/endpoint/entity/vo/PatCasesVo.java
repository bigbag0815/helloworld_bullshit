package com.bullshit.endpoint.entity.vo;

import java.util.List;

import com.bullshit.endpoint.entity.Cases;

public class PatCasesVo extends BaseVo {

	private List<Cases> casesList;

	public List<Cases> getCasesList() {
		return casesList;
	}

	public void setCasesList(List<Cases> casesList) {
		this.casesList = casesList;
	}
}
