package com.bullshit.endpoint.dao;

import com.bullshit.endpoint.entity.Hospital;

public interface HospitalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String hospitalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital
     *
     * @mbggenerated
     */
    int insert(Hospital record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital
     *
     * @mbggenerated
     */
    int insertSelective(Hospital record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital
     *
     * @mbggenerated
     */
    Hospital selectByPrimaryKey(String hospitalId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Hospital record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table hospital
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Hospital record);
}