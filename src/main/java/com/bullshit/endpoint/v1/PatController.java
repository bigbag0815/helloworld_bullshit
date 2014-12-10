package com.bullshit.endpoint.v1;

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
import org.springframework.util.CollectionUtils;

import com.bullshit.endpoint.entity.AccountKey;
import com.bullshit.endpoint.entity.Department;
import com.bullshit.endpoint.entity.ErrInfo;
import com.bullshit.endpoint.entity.vo.PatAccountVo;
import com.bullshit.endpoint.entity.vo.PatCasesVo;
import com.bullshit.endpoint.entity.vo.PatDepartmentVo;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.AccessBusinessLogic;
import com.bullshit.endpoint.service.DocBusinessLogic;
import com.bullshit.endpoint.service.PatBusinessLogic;

@Component
@Path("/v1/pat")
public class PatController {
	Logger log = LoggerFactory.getLogger(PatController.class);

	@Autowired
	private DocBusinessLogic docLogic;
	
	@Autowired
	private PatBusinessLogic patLogic;	
	
	@Autowired
	private AccessBusinessLogic accessLogic;
	
	/* ### 获取所有科室的列表 */
	@GET
	@Path("/deptlist")
	@Produces(MediaType.APPLICATION_JSON)
	public PatDepartmentVo getDepartmentList() throws ApiException {
		
		PatDepartmentVo patDepartmentVo = new PatDepartmentVo();
		try {
			patDepartmentVo.setRsStatus("ok");
			patDepartmentVo.setDepartmentList(patLogic.getDepartmentList(new Department()));
		} catch (Exception e) {
			e.printStackTrace();
			patDepartmentVo.setRsStatus("ng");
			patDepartmentVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		return patDepartmentVo;
	};

	/* ### 查找医生列表 */
	@GET
	@Path("/doclist")
	@Produces(MediaType.APPLICATION_JSON)
	public PatAccountVo getDoctorListByDepartId(
			@QueryParam("deptname") String dName,
			@QueryParam("keyword") String keyword,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("10") @QueryParam("to") int toNum)
			throws ApiException {
		
		PatAccountVo PatAccountVo = new PatAccountVo();
		
		if (fromNum <= 0 || toNum <= 0 || toNum - fromNum < 0) {
			PatAccountVo.setRsStatus("ng");
			PatAccountVo.setErrInfo(new ErrInfo("102", "区间错误"));
			return PatAccountVo;
		}
		
		try {
			// dName设定则取得某一科室的医生信息，不设定为所有科室的医生信息
			if (StringUtils.isNotBlank(dName)) {
				Department department = new Department();
				department.setName(dName);
				if (CollectionUtils.isEmpty(patLogic.getDepartmentList(department))) {
					PatAccountVo.setRsStatus("ng");
					PatAccountVo.setErrInfo(new ErrInfo("101","科室名不存在"));
					return PatAccountVo;
				}
			}
			AccountKey key = new AccountKey();
			key.setDocDepartmentName(dName);
			key.setKeyword(keyword);
			key.setOffset(fromNum - 1);
			key.setLimit(toNum - key.getOffset());

			PatAccountVo.setRsStatus("ok");
			PatAccountVo.setAccountList(accessLogic.getByAccountKey(key));
		} catch (Exception e) {
			e.printStackTrace();
			PatAccountVo.setRsStatus("ng");
			PatAccountVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}

		return PatAccountVo;
	};

	/* ### 通过病人id获取病例 */
	@GET
	@Path("/caseinfo/{patient_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public PatCasesVo getCaseInfoByPatientId(
			@PathParam("patient_id") String patient_id) throws ApiException {
		
		PatCasesVo patCasesVo = new PatCasesVo();
		try {
			patCasesVo.setRsStatus("ok");
			patCasesVo.setCasesList(patLogic.getCasesList(patient_id));
		} catch (Exception e) {
			e.printStackTrace();
			patCasesVo.setRsStatus("ng");
			patCasesVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		return patCasesVo;
	};

}
