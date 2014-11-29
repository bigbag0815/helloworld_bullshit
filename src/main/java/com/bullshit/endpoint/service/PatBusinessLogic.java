package com.bullshit.endpoint.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.constants.Constants;
import com.bullshit.endpoint.dao.CasesExtMapper;
import com.bullshit.endpoint.dao.DepartmentExtMapper;
import com.bullshit.endpoint.entity.Cases;
import com.bullshit.endpoint.entity.Department;
import com.bullshit.endpoint.utils.StringUtil;

@Service("patLogic")
public class PatBusinessLogic{
	
	@Autowired
	private CasesExtMapper casesExtMapper;
	
	@Autowired
	private DepartmentExtMapper departmentExtMapper;
	
	public List<Cases> getCasesList(String patId) throws Exception {
		List<Cases> resultCaseList = new ArrayList<Cases>();
		List<Cases> caseList = casesExtMapper.selectByPatId(patId);
		for (Cases caseInfo : caseList) {
			caseInfo.setPatPicUrl1(StringUtil.urlPathEdit(caseInfo.getPatPicUrl1(), Constants.QINIU_PREFIX_URL));
			caseInfo.setPatPicUrl2(StringUtil.urlPathEdit(caseInfo.getPatPicUrl2(), Constants.QINIU_PREFIX_URL));
			caseInfo.setPatPicUrl3(StringUtil.urlPathEdit(caseInfo.getPatPicUrl3(), Constants.QINIU_PREFIX_URL));
			
			resultCaseList.add(caseInfo);
		}
		return resultCaseList;
	}
	
	public List<Department> getDepartmentList(Department department) throws Exception {
		return departmentExtMapper.selectByName(department);
	}

}
