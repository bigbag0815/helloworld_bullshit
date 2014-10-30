package com.bullshit.endpoint.dao;

import java.util.List;

import com.bullshit.endpoint.entity.schedule;

public interface ScheduleMapper {
     List<schedule> getScheduleListById(int doc_id);
}
