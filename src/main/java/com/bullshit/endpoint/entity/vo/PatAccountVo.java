package com.bullshit.endpoint.entity.vo;

import java.util.List;

import com.bullshit.endpoint.entity.Account;

public class PatAccountVo extends BaseVo {

	private List<Account> accountList;

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

}
