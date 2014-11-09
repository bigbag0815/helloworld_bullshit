package com.bullshit.endpoint.v1;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.AccountKey;
import com.bullshit.endpoint.entity.DocSurgeryPlanKey;
import com.bullshit.endpoint.entity.ErrInfo;
import com.bullshit.endpoint.entity.PatientCaseBean;
import com.bullshit.endpoint.entity.Schedule;
import com.bullshit.endpoint.entity.vo.DocPatientCaseVo;
import com.bullshit.endpoint.entity.vo.DocSurgeryPlanVo;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.AccessBusinessLogic;
import com.bullshit.endpoint.service.DocBusinessLogic;
import com.bullshit.endpoint.service.DocPatRelationBusinessLogic;
import com.bullshit.endpoint.service.PatBusinessLogic;

@Component
@Path("/v1/doc")
public class DocController {
	Logger log = LoggerFactory.getLogger(DocController.class);

	@Autowired
	DocBusinessLogic docLogic;
	
	@Autowired
	PatBusinessLogic patLogic;
	
	@Autowired
	AccessBusinessLogic accessLogic;
	
	@Autowired
	DocPatRelationBusinessLogic docPatRelationBusinessLogic;

	/* ### 获取当前医生管理的病人和病人的病例历史记录 */
	@GET
	@Path("/patientlist/{doctor_id}/{pat_status}")
	@Produces(MediaType.APPLICATION_JSON)
	public DocPatientCaseVo getPatientByDoctID(
			@PathParam("doctor_id") String doctorId,
			@PathParam("pat_status") String patStatus,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("10") @QueryParam("to") int toNum)
			throws ApiException {
		
		log.debug("===========getPatientByDoctID request params:");
		log.debug("doctor_id: " + doctorId);
		log.debug("pat_status: " + patStatus);
		log.debug("from：" + fromNum);
		log.debug("to：" + toNum);
		
		DocPatientCaseVo docPatientCaseVo = new DocPatientCaseVo();

		try {
			if (!accessLogic.isContainUser(doctorId)) {
				docPatientCaseVo.setRsStatus("ng");
				docPatientCaseVo.setErrInfo(new ErrInfo("101", "医生用户Id错误，请确认。"));
				return docPatientCaseVo;
			}
			
			if (fromNum <= 0 || toNum <= 0 || toNum - fromNum < 0) {
				docPatientCaseVo.setRsStatus("ng");
				docPatientCaseVo.setErrInfo(new ErrInfo("102", "区间错误"));
				return docPatientCaseVo;
			}

			List<PatientCaseBean> PatientCaseList = new ArrayList<PatientCaseBean>();
			
			PatientCaseBean PatientCaseBean = null;
			AccountKey key = new AccountKey();
			key.setDocId(doctorId);
			key.setPatStatus(patStatus);
			key.setOffset(fromNum - 1);
			key.setLimit(toNum - key.getOffset());
			
			List<Account> accountList = accessLogic.getRelationPatInfo(key);
			for (Account account : accountList) {
				if (null == account) {
					docPatientCaseVo.setRsStatus("ng");
					docPatientCaseVo.setErrInfo(new ErrInfo("103", "患者用户Id错误，请确认。"));
					return docPatientCaseVo;
				}
				
				// 一个患者的情报
				PatientCaseBean = new PatientCaseBean();
				PatientCaseBean.setAccount(account);
				PatientCaseBean.setCaseList(patLogic.getCasesList(account.getId()));
				
				// 把一个患者的情报放入集合中
				PatientCaseList.add(PatientCaseBean);
			}
			
			docPatientCaseVo.setRsStatus("ok");
			docPatientCaseVo.setPatientCaseBeanList(PatientCaseList);
		} catch (Exception e) {
			e.printStackTrace();
			docPatientCaseVo.setRsStatus("ng");
			docPatientCaseVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		
		return docPatientCaseVo;
	};
	
	/* ### 查找医生手术计划 */
	@GET
	@Path("/planlist/{doctor_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public DocSurgeryPlanVo getSurgeryPlan(
			@PathParam("doctor_id") String docId,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("3") @QueryParam("to") int toNum)
			throws ApiException {
		
		DocSurgeryPlanVo docSurgeryPlanVo = new DocSurgeryPlanVo();
		
		if (fromNum <= 0 || toNum <= 0 || toNum - fromNum < 0) {
			docSurgeryPlanVo.setRsStatus("ng");
			docSurgeryPlanVo.setErrInfo(new ErrInfo("101", "区间错误"));
			return docSurgeryPlanVo;
		}
		
		try {
			if (accessLogic.isContainUser(docId)) {
				docSurgeryPlanVo.setRsStatus("ng");
				docSurgeryPlanVo.setErrInfo(new ErrInfo("102", "医生ID输入有误。"));
				return docSurgeryPlanVo;
			}
			DocSurgeryPlanKey key = new DocSurgeryPlanKey();
			key.setDocId(docId);
			key.setOffset(fromNum - 1);
			key.setLimit(toNum - key.getOffset());
			
			docSurgeryPlanVo.setRsStatus("ok");
			docSurgeryPlanVo.setDocSurgeryPlanList(docLogic.getSurgeryPlanByDocId(key));
		} catch (Exception e) {
			e.printStackTrace();
			docSurgeryPlanVo.setRsStatus("ng");
			docSurgeryPlanVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}

		return docSurgeryPlanVo;
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
