/**
 * 
 */
package com.bullshit.endpoint.entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 *
 *@author  uu
 *@createtime 2014-11-2
 *@vision:V1.0
 **/
public class PatientCaseBean implements Serializable {
	
	private Account account;
	
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
	public Account getAccount() {
		return this.account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return the caseList
	 */
	public List<Cases> getCaseList() {
		return this.caseList;
	}

	/**
	 * @param caseList the caseList to set
	 */
	public void setCaseList(List<Cases> caseList) {
		this.caseList = caseList;
	}

}
