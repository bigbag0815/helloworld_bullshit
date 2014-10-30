package com.bullshit.endpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.dao.ScheduleMapper;
import com.bullshit.endpoint.entity.schedule;
import com.bullshit.endpoint.exception.ApiException;

@Service("docLogic")
public class DocBusinessLogic{
	
	@Autowired
	private ScheduleMapper scheduleMapper;

	public List<schedule> scheduleList(int doc_id) throws ApiException {
		List<schedule> schedulelist=scheduleMapper.getScheduleListById(doc_id);
		for (schedule task : schedulelist) {
			System.out.println(task.getContent());
		}
		return schedulelist;
	}
	
	
	public List<schedule> testExcetpion(int doc_id) throws ApiException {
		try {
			throw new ApiException(500, "服务器异常");
		} catch (ApiException e) {
			e.printStackTrace();
			if (e.getCode() == 404) {
				return null;
			} else {
				throw e;
			}
		}
	}

}
