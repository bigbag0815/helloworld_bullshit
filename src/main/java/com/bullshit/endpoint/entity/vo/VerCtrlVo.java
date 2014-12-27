package com.bullshit.endpoint.entity.vo;

import com.bullshit.endpoint.entity.VersionControl;

public class VerCtrlVo extends BaseVo {

	private VersionControl versionControl;
	
	private boolean isNewestVersionFlg;

	public VersionControl getVersionControl() {
		return versionControl;
	}

	public void setVersionControl(VersionControl versionControl) {
		this.versionControl = versionControl;
	}

	public boolean isNewestVersionFlg() {
		return isNewestVersionFlg;
	}

	public void setNewestVersionFlg(boolean isNewestVersionFlg) {
		this.isNewestVersionFlg = isNewestVersionFlg;
	}

}
