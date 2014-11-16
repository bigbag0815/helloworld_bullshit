package com.bullshit.endpoint.entity;

import java.io.Serializable;
import java.util.List;

public class PatientCaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private HXAccount account;

	private List<Cases> caseList;

	/**
	 * 
	 */
	public PatientCaseBean() {
		super();
	}

	/**
	 * @return the account
	 */
	public HXAccount getAccount() {
		return this.account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(HXAccount account) {
		this.account = account;
	}

	/**
	 * @return the caseList
	 */
	public List<Cases> getCaseList() {
		return this.caseList;
	}

	/**
	 * @param caseList
	 *            the caseList to set
	 */
	public void setCaseList(List<Cases> caseList) {
		this.caseList = caseList;
	}

}
