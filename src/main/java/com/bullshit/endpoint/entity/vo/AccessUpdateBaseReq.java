package com.bullshit.endpoint.entity.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccessUpdateBaseReq {
	private String id;
	private String name;
	private String age;
	private String sex;
	private String imageurl;
	private String mobilePhone;
	private String telPhone;
	
	private String roleflg;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getRoleflg() {
		return roleflg;
	}
	public void setRoleflg(String roleflg) {
		this.roleflg = roleflg;
	}
	
	
	
}
