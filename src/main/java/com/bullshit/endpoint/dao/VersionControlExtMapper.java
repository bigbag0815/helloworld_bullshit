package com.bullshit.endpoint.dao;

import java.util.List;

import com.bullshit.endpoint.entity.VersionControl;

public interface VersionControlExtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table version_control
     *
     * @mbggenerated
     */
    List<VersionControl> selectAllVersionInfo();
}