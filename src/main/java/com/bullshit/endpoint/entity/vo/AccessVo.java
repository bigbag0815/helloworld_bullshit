package com.bullshit.endpoint.entity.vo;

import com.bullshit.endpoint.entity.Account;

public class AccessVo extends BaseVo {

	private Account accountInfo;

	public Account getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(Account accountInfo) {
		this.accountInfo = accountInfo;
	}
}
