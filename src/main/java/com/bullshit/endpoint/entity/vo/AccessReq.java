package com.bullshit.endpoint.entity.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AccessReq {

	private String username;
	private String password;
	private String roleflg;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleflg() {
		return roleflg;
	}

	public void setRoleflg(String roleflg) {
		this.roleflg = roleflg;
	}

}
