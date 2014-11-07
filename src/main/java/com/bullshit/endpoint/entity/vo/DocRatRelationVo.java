package com.bullshit.endpoint.entity.vo;

import com.bullshit.endpoint.entity.DocPatRelation;
import com.bullshit.endpoint.entity.ErrInfo;

public class DocRatRelationVo {

	private String rsStatus;
	private DocPatRelation docPatRelation;
	private ErrInfo errInfo;

	public String getRsStatus() {
		return rsStatus;
	}

	public void setRsStatus(String rsStatus) {
		this.rsStatus = rsStatus;
	}

	public DocPatRelation getDocPatRelation() {
		return docPatRelation;
	}

	public void setDocPatRelation(DocPatRelation docPatRelation) {
		this.docPatRelation = docPatRelation;
	}

	public ErrInfo getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(ErrInfo errInfo) {
		this.errInfo = errInfo;
	}
}
