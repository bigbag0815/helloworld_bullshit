package com.bullshit.endpoint.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @version 1.0
 * @created 17-十月-2014 18:30:43
 */
public class Schedule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键自增
	 */
	private  int id;
	/**
	 * ID
	 */
	private int doc_id;
	/**
	 * ID
	 */
	private int pat_id;
	/**
	 * 任务时间（提�?这个时间点干嘛）
	 */
	private Timestamp scheduletime;
	/**
	 * 任务�?��描述
	 */
	private String content;
	/**
	 */
	private int group_id;
	private Timestamp ctime;
	private Timestamp mtime;
	/**
	 * 
	 */
	public Schedule() {
		super();
	}
	/**
	 * @param id
	 * @param doc_id
	 * @param pat_id
	 * @param scheduletime
	 * @param content
	 * @param group_id
	 * @param ctime
	 * @param mtime
	 */
	public Schedule(int id, int doc_id, int pat_id,
			Timestamp scheduletime, String content, int group_id,
			Timestamp ctime, Timestamp mtime) {
		super();
		this.id = id;
		this.doc_id = doc_id;
		this.pat_id = pat_id;
		this.scheduletime = scheduletime;
		this.content = content;
		this.group_id = group_id;
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
	 * @return the doc_id
	 */
	public int getDoc_id() {
		return this.doc_id;
	}
	/**
	 * @param doc_id the doc_id to set
	 */
	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}
	/**
	 * @return the pat_id
	 */
	public int getPat_id() {
		return this.pat_id;
	}
	/**
	 * @param pat_id the pat_id to set
	 */
	public void setPat_id(int pat_id) {
		this.pat_id = pat_id;
	}
	/**
	 * @return the scheduletime
	 */
	public Timestamp getScheduletime() {
		return this.scheduletime;
	}
	/**
	 * @param scheduletime the scheduletime to set
	 */
	public void setScheduletime(Timestamp scheduletime) {
		this.scheduletime = scheduletime;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return this.content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the group_id
	 */
	public int getGroup_id() {
		return this.group_id;
	}
	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


}