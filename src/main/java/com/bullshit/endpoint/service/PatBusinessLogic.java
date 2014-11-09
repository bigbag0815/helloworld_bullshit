package com.bullshit.endpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.dao.CasesExtMapper;
import com.bullshit.endpoint.dao.DepartmentExtMapper;
import com.bullshit.endpoint.entity.Cases;
import com.bullshit.endpoint.entity.Department;

@Service("patLogic")
public class PatBusinessLogic{

	@Autowired
	private CasesExtMapper casesExtMapper;
	
	@Autowired
	private DepartmentExtMapper departmentExtMapper;
	
	public List<Cases> getCasesList(String patId) throws Exception {
		return casesExtMapper.selectByPatId(patId);
	}
	
	public List<Department> getDepartmentList(Department department) throws Exception {
		return departmentExtMapper.selectByName(department);
	}

}
