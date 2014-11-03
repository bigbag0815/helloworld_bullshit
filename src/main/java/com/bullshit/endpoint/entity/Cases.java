package com.bullshit.endpoint.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @version 1.0
 * @created 17-十月-2014 18:29:44
 */
public class Cases implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键自增
	 */
	private  int id;
	/**
	 * 患者ID
	 */
	private int patient_id;
	/**
	 * 主治医师ID
	 */
	private int doctor_id;
	/**
	 * 病例和医生绑定之后，然后医生所属的group中的所有人都可以查看这个病例
	 */
	private int group_id;
	/**
	 * 患者主诉
	 */
	private String patientReport;
	/**
	 * 体格检查
	 */
	private String medicalExamination;
	/**
	 * 辅助检查
	 */
	private String accessoryExamination;
	/**
	 * 初步诊断
	 */
	private String tentativeDiagnosis;
	/**
	 * 处理意见
	 */
	private String handlingSuggestion;
	/**
	 * 既往史
	 */
	private String pastHistory;
	/**
	 * 过敏药物提醒
	 */
	private String allergyDrug;
	/**
	 * 病房号
	 */
	private int sickroom_id;
	/**
	 * 是否已经失效
	 */
	private int isactive;
	
	private Timestamp ctime;
	private Timestamp mtime;
	
	
	/**
	 * 
	 */
	public Cases() {
		super();
	}
	/**
	 * @param id
	 * @param patient_id
	 * @param doctor_id
	 * @param group_id
	 * @param patientReport
	 * @param medicalExamination
	 * @param accessoryExamination
	 * @param tentativeDiagnosis
	 * @param handlingSuggestion
	 * @param pastHistory
	 * @param allergyDrug
	 * @param sickroom_id
	 * @param isactive
	 * @param ctime
	 * @param mtime
	 */
	public Cases(int id, int patient_id, int doctor_id, int group_id,
			String patientReport, String medicalExamination,
			String accessoryExamination, String tentativeDiagnosis,
			String handlingSuggestion, String pastHistory, String allergyDrug,
			int sickroom_id, int isactive, Timestamp ctime, Timestamp mtime) {
		super();
		this.id = id;
		this.patient_id = patient_id;
		this.doctor_id = doctor_id;
		this.group_id = group_id;
		this.patientReport = patientReport;
		this.medicalExamination = medicalExamination;
		this.accessoryExamination = accessoryExamination;
		this.tentativeDiagnosis = tentativeDiagnosis;
		this.handlingSuggestion = handlingSuggestion;
		this.pastHistory = pastHistory;
		this.allergyDrug = allergyDrug;
		this.sickroom_id = sickroom_id;
		this.isactive = isactive;
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
	 * @return the patient_id
	 */
	public int getPatient_id() {
		return this.patient_id;
	}
	/**
	 * @param patient_id the patient_id to set
	 */
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	/**
	 * @return the doctor_id
	 */
	public int getDoctor_id() {
		return this.doctor_id;
	}
	/**
	 * @param doctor_id the doctor_id to set
	 */
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
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
	 * @return the patientReport
	 */
	public String getPatientReport() {
		return this.patientReport;
	}
	/**
	 * @param patientReport the patientReport to set
	 */
	public void setPatientReport(String patientReport) {
		this.patientReport = patientReport;
	}
	/**
	 * @return the medicalExamination
	 */
	public String getMedicalExamination() {
		return this.medicalExamination;
	}
	/**
	 * @param medicalExamination the medicalExamination to set
	 */
	public void setMedicalExamination(String medicalExamination) {
		this.medicalExamination = medicalExamination;
	}
	/**
	 * @return the accessoryExamination
	 */
	public String getAccessoryExamination() {
		return this.accessoryExamination;
	}
	/**
	 * @param accessoryExamination the accessoryExamination to set
	 */
	public void setAccessoryExamination(String accessoryExamination) {
		this.accessoryExamination = accessoryExamination;
	}
	/**
	 * @return the tentativeDiagnosis
	 */
	public String getTentativeDiagnosis() {
		return this.tentativeDiagnosis;
	}
	/**
	 * @param tentativeDiagnosis the tentativeDiagnosis to set
	 */
	public void setTentativeDiagnosis(String tentativeDiagnosis) {
		this.tentativeDiagnosis = tentativeDiagnosis;
	}
	/**
	 * @return the handlingSuggestion
	 */
	public String getHandlingSuggestion() {
		return this.handlingSuggestion;
	}
	/**
	 * @param handlingSuggestion the handlingSuggestion to set
	 */
	public void setHandlingSuggestion(String handlingSuggestion) {
		this.handlingSuggestion = handlingSuggestion;
	}
	/**
	 * @return the pastHistory
	 */
	public String getPastHistory() {
		return this.pastHistory;
	}
	/**
	 * @param pastHistory the pastHistory to set
	 */
	public void setPastHistory(String pastHistory) {
		this.pastHistory = pastHistory;
	}
	/**
	 * @return the allergyDrug
	 */
	public String getAllergyDrug() {
		return this.allergyDrug;
	}
	/**
	 * @param allergyDrug the allergyDrug to set
	 */
	public void setAllergyDrug(String allergyDrug) {
		this.allergyDrug = allergyDrug;
	}
	/**
	 * @return the sickroom_id
	 */
	public int getSickroom_id() {
		return this.sickroom_id;
	}
	/**
	 * @param sickroom_id the sickroom_id to set
	 */
	public void setSickroom_id(int sickroom_id) {
		this.sickroom_id = sickroom_id;
	}
	/**
	 * @return the isactive
	 */
	public int getIsactive() {
		return this.isactive;
	}
	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(int isactive) {
		this.isactive = isactive;
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