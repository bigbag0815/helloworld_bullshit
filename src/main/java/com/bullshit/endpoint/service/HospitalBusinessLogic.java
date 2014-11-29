package com.bullshit.endpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.dao.HospitalExtMapper;
import com.bullshit.endpoint.entity.Hospital;
import com.bullshit.endpoint.entity.HospitalKey;
import com.bullshit.endpoint.exception.ApiException;

@Service("hospptalLogic")
public class HospitalBusinessLogic{
	
	@Autowired
	private HospitalExtMapper hospitalExtMapper;

	public List<Hospital> getHospitalInfoList(HospitalKey hospitalKey) throws ApiException {
		return hospitalExtMapper.selectInfoList(hospitalKey);
	}
}
