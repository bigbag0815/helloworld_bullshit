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
import com.bullshit.endpoint.entity.Schedule;
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
				caseinfo.setPatPicUrl1("picture  url");
				caseinfo.setDocSuggestion("建议到医院复查一下");
				caseList.add(caseinfo);
			}
			caseBean.setCaseList(caseList);
			patientList.add(caseBean);
		}
		return patientList;
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
