/**
 * 
 */
package com.bullshit.endpoint.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 *
 *@author  ts-shoufang.wang
 *@createtime 2014-11-1
 *@vision:V1.0
 **/
public class Account  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String image_url;
	private String age;
	private String title;
	private String professional;
	private String  telphone;
	private String emergTel;
	private String description;
	private String deptname;
	/*
	 * role_flg
	 * 1.doctor
	 * 2.patient
	 * */
	private String role_flg;	
	private Timestamp ctime;
	private Timestamp mtime;
	
	private String hxusername;
	private String hxpassword;
	
	/**
	 * 
	 */
	public Account() {
		super();
	}
	
	/**
	 * @param id
	 * @param name
	 * @param image_url
	 * @param age
	 * @param title
	 * @param professional
	 * @param telphone
	 * @param emergTel
	 * @param description
	 * @param deptname
	 * @param role_flg
	 * @param ctime
	 * @param mtime
	 */
	public Account(int id, String name, String image_url, String age,
			String title, String professional, String telphone,
			String emergTel, String description, String deptname,
			String role_flg, String hxusername,String hxpassword,Timestamp ctime, Timestamp mtime) {
		super();
		this.id = id;
		this.name = name;
		this.image_url = image_url;
		this.age = age;
		this.title = title;
		this.professional = professional;
		this.telphone = telphone;
		this.emergTel = emergTel;
		this.description = description;
		this.deptname = deptname;
		this.role_flg = role_flg;
		this.hxusername=hxusername;
		this.hxpassword=hxpassword;
		this.ctime = ctime;
		this.mtime = mtime;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the image_url
	 */
	public String getImage_url() {
		return this.image_url;
	}
	/**
	 * @param image_url the image_url to set
	 */
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return this.age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the professional
	 */
	public String getProfessional() {
		return this.professional;
	}
	/**
	 * @param professional the professional to set
	 */
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	/**
	 * @return the telphone
	 */
	public String getTelphone() {
		return this.telphone;
	}
	/**
	 * @param telphone the telphone to set
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	/**
	 * @return the emergTel
	 */
	public String getEmergTel() {
		return this.emergTel;
	}
	/**
	 * @param emergTel the emergTel to set
	 */
	public void setEmergTel(String emergTel) {
		this.emergTel = emergTel;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the deptname
	 */
	public String getDeptname() {
		return this.deptname;
	}
	/**
	 * @param deptname the deptname to set
	 */
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	/**
	 * @return the role_flg
	 */
	public String getRole_flg() {
		return this.role_flg;
	}
	/**
	 * @param role_flg the role_flg to set
	 */
	public void setRole_flg(String role_flg) {
		this.role_flg = role_flg;
	}
	/**
	 * @return the ctime
	 */
	public Timestamp getCtime() {
		return this.ctime;
	}
	/**
	 * @param ctime the ctime to set
	 */
	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
	/**
	 * @return the mtime
	 */
	public Timestamp getMtime() {
		return this.mtime;
	}
	/**
	 * @param mtime the mtime to set
	 */
	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	/**
	 * @return the hxusername
	 */
	public String getHxusername() {
		return this.hxusername;
	}

	/**
	 * @param hxusername the hxusername to set
	 */
	public void setHxusername(String hxusername) {
		this.hxusername = hxusername;
	}

	/**
	 * @return the hxpassword
	 */
	public String getHxpassword() {
		return this.hxpassword;
	}

	/**
	 * @param hxpassword the hxpassword to set
	 */
	public void setHxpassword(String hxpassword) {
		this.hxpassword = hxpassword;
	}
	
}
