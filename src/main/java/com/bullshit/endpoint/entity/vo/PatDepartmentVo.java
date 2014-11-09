package com.bullshit.endpoint.entity.vo;

import java.util.List;

import com.bullshit.endpoint.entity.Department;

public class PatDepartmentVo extends BaseVo {

	private List<Department> departmentList;

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}
}
