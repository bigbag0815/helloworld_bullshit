package com.bullshit.endpoint.entity;

import org.apache.commons.lang3.StringEscapeUtils;

public class HospitalKey extends BaseKey{

    private String hospitalId;

    private String hospitalTel;

    private String hospitalProvince;

    private String hospitalName;

    private String hospitalType;

    private String hospitalAddress;
    
    private String hospitalKeyword;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId == null ? null : hospitalId.trim();
    }

    public String getHospitalTel() {
        return hospitalTel;
    }

    public void setHospitalTel(String hospitalTel) {
        this.hospitalTel = hospitalTel == null ? null : hospitalTel.trim();
    }

    public String getHospitalProvince() {
        return hospitalProvince;
    }

    public void setHospitalProvince(String hospitalProvince) {
        this.hospitalProvince = hospitalProvince == null ? null : hospitalProvince.trim();
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName == null ? null : hospitalName.trim();
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType == null ? null : hospitalType.trim();
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress == null ? null : hospitalAddress.trim();
    }

	public String getHospitalKeyword() {
		return hospitalKeyword;
	}

	public void setHospitalKeyword(String hospitalKeyword) {
		this.hospitalKeyword = hospitalKeyword == null ? null : "%" + hospitalKeyword.trim() + "%";
	}
}