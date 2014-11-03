package com.bullshit.endpoint.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @version 1.0
 * @created 17-十月-2014 18:29:54
 */
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键自增
	 */
	private  int id;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 部门职能描述
	 */
	private String description;
	private Timestamp ctime;
	private Timestamp mtime;
	/**
	 * 
	 */
	public Department() {
		super();
	}
	/**
	 * @param id
	 * @param name
	 * @param description
	 * @param ctime
	 * @param mtime
	 */
	public Department(int id, String name, String description, Timestamp ctime,
			Timestamp mtime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
}