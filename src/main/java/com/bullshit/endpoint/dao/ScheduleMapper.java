package com.bullshit.endpoint.dao;

import java.util.List;

import com.bullshit.endpoint.entity.Schedule;

public interface ScheduleMapper {
     List<Schedule> getScheduleListById(int doc_id);
}
