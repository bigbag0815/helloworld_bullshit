package com.bullshit.endpoint.v1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.PatientCaseBean;
import com.bullshit.endpoint.entity.Cases;
import com.bullshit.endpoint.entity.Department;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.DocBusinessLogic;

@Component
@Path("/v1/doc")
public class DocController {
	Logger log = LoggerFactory.getLogger(DocController.class);

	@Autowired
	DocBusinessLogic docLogic;

	/* ### 获取当前医生管理的病人和病人的病例历史记录 */
	@GET
	@Path("/patientlist/{doctor_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PatientCaseBean> getPatientByDoctID(
			@PathParam("doctor_id") String doctor_id,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("10") @QueryParam("to") int toNum)
			throws ApiException {
		log.debug("===========getPatientByDoctID request params:");
		log.debug(doctor_id);
		log.debug("from：" + fromNum);
		log.debug("to：" + toNum);

		List<PatientCaseBean> patientList = new ArrayList<PatientCaseBean>();
		for (int i = 1; i < 3; i++) {
			PatientCaseBean caseBean = new PatientCaseBean();
			Account account = new Account();
			account.setId("13340857899");
			account.setName("李四");
			account.setImageurl("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
			account.setAge("25");
			
			/*
			 * roleflg 1.doc 2.pat
			 */
			account.setRoleflg("pat");

			account.setHxusername("Hxusername");
			account.setHxpassword("Hxpassword");
			account.setCtime(new Timestamp(System.currentTimeMillis()));
			account.setMtime(new Timestamp(System.currentTimeMillis()));
			account.setPatAllergyDrug("青霉素");
			account.setPatPastHistory("高血压，低血糖");
			account.setPatEmergPhone("13388889999");
			/* 患者的相关信息 */
			caseBean.setAccount(account);

			/* 患者的履历 */
			List<Cases> caseList = new ArrayList<Cases>();
			for (int j = 0; j < 3; j++) {
				Cases caseinfo = new Cases();
				caseinfo.setCaseId("13000005678case"+i);
				caseinfo.setPatId("13000005678");
				caseinfo.setPatReport("最近白带增多，有异味");;
				caseinfo.setMedicalexamination("picture  url");
				caseinfo.setDocSuggestion("建议到医院复查一下");
				caseList.add(caseinfo);
			}
			caseBean.setCaseList(caseList);
			patientList.add(caseBean);
		}
		return patientList;
	};

	/* ### 获取所有科室的列表 */
	@GET
	@Path("/deptlist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getDepartmentList() throws ApiException {
		List<Department> deptList = new ArrayList<Department>();
		String[] deptnames = { "外科", "内科", "精神科", "放射科" };
		for (int i = 0; i < deptnames.length; i++) {
			Department dept = new Department();
			dept.setId(i);
			dept.setName(deptnames[i]);
			dept.setDescription("外科DescriptionDescriptionDescriptionDescriptionDescription");
			deptList.add(dept);
		}
		return deptList;
	};

	/* ### 查找医生列表 */
	@GET
	@Path("/doclist/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getDoctorListByDepartId(
			@QueryParam("deptid") String did,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("10") @QueryParam("to") int toNum)
			throws ApiException {
		List<Account> docList = new ArrayList<Account>();

		if (StringUtils.isNotEmpty(did)) {
			Account account = new Account();
			account.setId("13340855555");
			account.setName("王五");
			account.setImageurl("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
			account.setAge("55");
			account.setDocTitle("主治医师");
			account.setDocProfessional("妇产，婴幼儿保健");
			account.setMobilePhone("18622345678");
			account.setTelPhone("0103456789");
			account.setDocDescription("2007年，毕业于中国医科大学毕业。");
			account.setDocDepartmentName("妇产科");

			Account account2 = new Account();
			account2.setId("13340857777");
			account2.setName("李七");
			account2.setImageurl("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
			account2.setAge("77");
			account2.setDocTitle("教授");
			account2.setDocProfessional("妇产，婴幼儿保健");
			account2.setMobilePhone("18622345678");
			account2.setTelPhone("0103456789");
			account2.setDocDescription("留学海外多年，有多年临床经验");
			account2.setDocDepartmentName("妇产科");

			docList.add(account);
			docList.add(account2);
		} else {
			for (int i = 1; i < 4; i++) {
				Account account3 = new Account();
				account3.setId("13340857777");
				account3.setName("李七");
				account3.setImageurl("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
				account3.setAge("77");
				account3.setDocTitle("教授");
				account3.setDocProfessional("妇产，婴幼儿保健");
				account3.setMobilePhone("18622345678");
				account3.setTelPhone("0103456789");
				account3.setDocDescription("留学海外多年，有多年临床经验");
				account3.setDocDepartmentName("妇产科");
				docList.add(account3);
			}

		}
		return docList;
	};
}
