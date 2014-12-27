package com.bullshit.endpoint.entity.vo;

import java.util.List;

import com.bullshit.endpoint.entity.AccountWithRelationStatus;

public class PatAccountWithRelationStatusVo extends BaseVo {

	private List<AccountWithRelationStatus> accountList;

	public List<AccountWithRelationStatus> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<AccountWithRelationStatus> accountList) {
		this.accountList = accountList;
	}

}
