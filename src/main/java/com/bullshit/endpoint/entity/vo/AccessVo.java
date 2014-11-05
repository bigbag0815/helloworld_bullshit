package com.bullshit.endpoint.entity.vo;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.ErrInfo;

public class AccessVo {

	private String rsStatus;
	private Account accountInfo;
	private ErrInfo errInfo;

	public String getRsStatus() {
		return rsStatus;
	}

	public void setRsStatus(String rsStatus) {
		this.rsStatus = rsStatus;
	}

	public Account getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(Account accountInfo) {
		this.accountInfo = accountInfo;
	}

	public ErrInfo getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(ErrInfo errInfo) {
		this.errInfo = errInfo;
	}
}
