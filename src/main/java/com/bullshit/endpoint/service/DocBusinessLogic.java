package com.bullshit.endpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.dao.ScheduleMapper;
import com.bullshit.endpoint.entity.Schedule;
import com.bullshit.endpoint.exception.ApiException;

@Service("docLogic")
public class DocBusinessLogic{
	
	@Autowired
	private ScheduleMapper scheduleMapper;

	public List<Schedule> scheduleList(int doc_id) throws ApiException {
		List<Schedule> schedulelist=scheduleMapper.getScheduleListById(doc_id);
		for (Schedule task : schedulelist) {
			System.out.println(task.getContent());
		}
		return schedulelist;
	}
	
	
	public List<Schedule> testExcetpion(int doc_id) throws ApiException {
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
