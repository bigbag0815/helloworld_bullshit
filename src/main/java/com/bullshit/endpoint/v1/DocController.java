package com.bullshit.endpoint.v1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.PatientCaseBean;
import com.bullshit.endpoint.entity.Cases;
import com.bullshit.endpoint.entity.Department;
import com.bullshit.endpoint.entity.Schedule;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.DocBusinessLogic;

@Component
@Path("/v1/doc")
public class DocController {
	Logger log = LoggerFactory.getLogger(DocController.class);

	@Autowired
	DocBusinessLogic docLogic;

	/**
	 * 登录验证 login 是医生和病人公用部分 给front返回 hxusername and hxpassword login
	 * 目前缺少token验证，暂且先不管
	 * **/
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Account logininfo(@FormParam("username") String username,
			@FormParam("password") String password) throws ApiException {
		System.out.println(username);
		System.out.println(password);
		Boolean isContainUser = true;
		if (isContainUser) {
			// password need encode,md5
			if ("admin".equals(password)) {
				Account account = new Account();
				account.setId(1);
				account.setName("张三");
				account.setImage_url("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
				account.setAge("35");
				account.setTitle("教授");
				account.setProfessional("外科，手术");
				account.setTelphone("18622345678");
				account.setEmergTel("0103456789");
				account.setDescription("从业十五年，一直被模仿，从未被超越");
				account.setDeptname("外科");
				/*
				 * role_flg 1.doctor 2.patient
				 */
				account.setRole_flg("1");
				account.setHxusername("xxxxxxxxxxxxxxxxxxx");
				account.setHxpassword("xxxxxxxxxxxxxxxxxxx");
				account.setCtime(new Timestamp(System.currentTimeMillis()));
				account.setMtime(new Timestamp(System.currentTimeMillis()));

				return account;
			}
		} else {
			try {
				throw new ApiException(500, "服务器异常");
			} catch (ApiException e) {
				e.printStackTrace();
				if (e.getCode() == 404) {
					return null;
				} else {
					throw e;
				}
			}
		}
		return null;
	}

	/* ### 获取当前医生管理的病人和病人的病例历史记录 */
	@GET
	@Path("/patientlist/{doctor_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PatientCaseBean> getPatientByDoctID(
			@PathParam("doctor_id") String doctor_id,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("10") @QueryParam("to") int toNum) {
		log.debug("===========getPatientByDoctID request params:");
		log.debug(doctor_id);
		log.debug("from：" + fromNum);
		log.debug("to：" + toNum);

		List<PatientCaseBean> patientList = new ArrayList<PatientCaseBean>();
		for (int i = 1; i < 3; i++) {
			PatientCaseBean caseBean = new PatientCaseBean();
			Account account = new Account();
			account.setId(1);
			account.setName("张三-" + String.valueOf(i));
			account.setImage_url("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
			account.setAge("35");
			account.setTitle("病人");
			account.setProfessional("外科，手术");
			account.setTelphone("18622345678");
			account.setEmergTel("0103456789");
			account.setDescription("从业十五年，一直被模仿，从未被超越");
			account.setDeptname("外科");
			/*
			 * role_flg 1.doctor 2.patient
			 */
			account.setRole_flg("2");
			account.setHxusername("xxxxxxxxxxxxxxxxxxx");
			account.setHxpassword("xxxxxxxxxxxxxxxxxxx");
			account.setCtime(new Timestamp(System.currentTimeMillis()));
			account.setMtime(new Timestamp(System.currentTimeMillis()));

			/* 患者的相关信息 */
			caseBean.setAccount(account);

			/* 患者的履历 */
			List<Cases> caseList = new ArrayList<Cases>();
			for (int j = 0; j < 3; j++) {
				Cases caseinfo = new Cases();
				caseinfo.setId(i);
				caseinfo.setPatient_id(1);
				caseinfo.setDoctor_id(1);
				caseinfo.setAllergyDrug("青霉素过敏");
				caseinfo.setHandlingSuggestion("住院观察");
				caseList.add(caseinfo);
			}
			caseBean.setCaseList(caseList);
			patientList.add(caseBean);
		}
		return patientList;
	};

	/* ### 通过病人id获取病例 */
	@GET
	@Path("/caseinfo/{patient_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cases getCaseInfoByPatientId(
			@PathParam("patient_id") String patient_id) {
		Cases caseinfo = new Cases();
		caseinfo.setId(1);
		caseinfo.setPatient_id(Integer.valueOf(patient_id));
		// 根据相关需要 ，把名字也给返回
		caseinfo.setDoctor_id(1);
		caseinfo.setAllergyDrug("青霉素过敏");
		caseinfo.setHandlingSuggestion("住院观察");
		return caseinfo;
	};

	/* ##### 患者部分 */

	/* ### 获取所有科室的列表 */
	@GET
	@Path("/deptlist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getDepartmentList() {
		List<Department> deptList = new ArrayList<Department>();
		String[] deptnames = { "外科", "内科", "精神科", "放射科" };
		for (int i = 0; i < deptnames.length; i++) {
			Department dept = new Department();
			dept.setId(i);
			dept.setName(deptnames[i]);
			dept.setDescription("专业十五年，一直被模仿，从未被超越");
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
			@DefaultValue("10") @QueryParam("to") int toNum) {
		List<Account> docList = new ArrayList<Account>();

		if (StringUtils.isNotEmpty(did)) {
			Account account = new Account();
			account.setId(1);
			account.setName("张三");
			account.setImage_url("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
			account.setAge("35");
			account.setTitle("教授");
			account.setProfessional("外科，手术");
			account.setTelphone("18622345678");
			account.setEmergTel("0103456789");
			account.setDescription("从业十五年，一直被模仿，从未被超越");
			account.setDeptname("外科");

			Account account2 = new Account();
			account2.setId(2);
			account2.setName("李四");
			account2.setImage_url("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
			account2.setAge("45");
			account2.setTitle("教授");
			account2.setProfessional("外科，手术");
			account2.setTelphone("18622345678");
			account2.setEmergTel("0103456789");
			account2.setDescription("从业十五年，一直被模仿，从未被超越");
			account2.setDeptname("外科");

			docList.add(account);
			docList.add(account2);
		} else {
			for (int i = 1; i < 4; i++) {
				Account account2 = new Account();
				account2.setId(i);
				account2.setName("李四");
				account2.setImage_url("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
				account2.setAge("45");
				account2.setTitle("教授");
				account2.setProfessional("外科，手术");
				account2.setTelphone("18622345678");
				account2.setEmergTel("0103456789");
				account2.setDescription("从业十五年，一直被模仿，从未被超越");
				account2.setDeptname("外科" + i);
				docList.add(account2);
			}

		}
		return docList;
	};

	/* 病人填写病例 */
	@POST
	@Path("/cases/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertPatientCase(@FormParam("user_id") String user_id,
			@FormParam("message") String message, FormDataMultiPart multiPart) {
		for (BodyPart bodyPart : multiPart.getBodyParts()) {
			System.out.println(bodyPart.getEntityAs(String.class));
		}
		return null;
	};

	@GET
	@Path("/schedulelist/page/{did}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Schedule> docscheduleListOrder(@PathParam("did") String did,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("10") @QueryParam("to") int toNum,
			@DefaultValue("id") @QueryParam("order") String order)
			throws ApiException {
		/* 测试含有异常的方法 */
		List<Schedule> schedulelist = docLogic.testExcetpion(Integer
				.valueOf(did));
		return schedulelist;

	}
}
