package com.bullshit.endpoint.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.dao.CasesExtMapper;
import com.bullshit.endpoint.dao.DepartmentExtMapper;
import com.bullshit.endpoint.entity.Cases;
import com.bullshit.endpoint.entity.Department;

@Service("patLogic")
public class PatBusinessLogic{
	
	private static final String PREFIX_URL = "http://ziyue991.qiniudn.com/";

	@Autowired
	private CasesExtMapper casesExtMapper;
	
	@Autowired
	private DepartmentExtMapper departmentExtMapper;
	
	public List<Cases> getCasesList(String patId) throws Exception {
		List<Cases> resultCaseList = new ArrayList<Cases>();
		List<Cases> caseList = casesExtMapper.selectByPatId(patId);
		for (Cases caseInfo : caseList) {
			caseInfo.setPatPicUrl1(urlPathEdit(caseInfo.getPatPicUrl1(), PREFIX_URL));
			caseInfo.setPatPicUrl2(urlPathEdit(caseInfo.getPatPicUrl2(), PREFIX_URL));
			caseInfo.setPatPicUrl3(urlPathEdit(caseInfo.getPatPicUrl3(), PREFIX_URL));
			
			resultCaseList.add(caseInfo);
		}
		return resultCaseList;
	}
	
	public List<Department> getDepartmentList(Department department) throws Exception {
		return departmentExtMapper.selectByName(department);
	}

	private String urlPathEdit(String url, String prefixUrl){
		if (StringUtils.isBlank(url)) {
			return "";
		}
		
		return prefixUrl + url;
	}
}
