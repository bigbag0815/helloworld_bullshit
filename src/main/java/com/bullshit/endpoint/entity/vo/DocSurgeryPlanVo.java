package com.bullshit.endpoint.entity.vo;

import java.util.List;

import com.bullshit.endpoint.entity.DocSurgeryPlan;

public class DocSurgeryPlanVo extends BaseVo {

	private List<DocSurgeryPlan> docSurgeryPlanList;

	public List<DocSurgeryPlan> getDocSurgeryPlanList() {
		return docSurgeryPlanList;
	}

	public void setDocSurgeryPlanList(List<DocSurgeryPlan> docSurgeryPlanList) {
		this.docSurgeryPlanList = docSurgeryPlanList;
	}
}
