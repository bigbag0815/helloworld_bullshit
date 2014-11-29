package com.bullshit.endpoint.v1;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.ErrInfo;
import com.bullshit.endpoint.entity.HospitalKey;
import com.bullshit.endpoint.entity.vo.HospitalVo;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.HospitalBusinessLogic;

@Component
@Path("/v1/hospital")
public class HospitalController {
	Logger log = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	HospitalBusinessLogic hospptalLogic;

	/* ### 通过检索条件获取医院信息 */
	@GET
	@Path("/getinfolist")
	@Produces(MediaType.APPLICATION_JSON)
	public HospitalVo getHospitalInfoList(
			@QueryParam("hospital_id") String hospitalId,
			@QueryParam("hospital_name") String hospitalName,
			@QueryParam("hospital_province") String hospitalProvince,
			@QueryParam("hospital_type") String hospitalType,
			@QueryParam("hospital_address") String hospitalAddress,
			@QueryParam("hospital_keyword") String hospitalKeyword,
			@DefaultValue("1") @QueryParam("from") int fromNum,
			@DefaultValue("10") @QueryParam("to") int toNum)
			throws ApiException {
		
		log.debug("===========getHospitalInfoList request params:");
		log.debug("hospital_id: " + hospitalId);
		log.debug("hospital_name: " + hospitalName);
		log.debug("hospital_province: " + hospitalProvince);
		log.debug("hospital_type: " + hospitalType);
		log.debug("hospital_address: " + hospitalAddress);
		log.debug("hospital_keyword: " + hospitalKeyword);
		log.debug("from：" + fromNum);
		log.debug("to：" + toNum);
		
		HospitalVo hospitalVo = new HospitalVo();
		
		if (fromNum <= 0 || toNum <= 0 || toNum - fromNum < 0) {
			hospitalVo.setRsStatus("ng");
			hospitalVo.setErrInfo(new ErrInfo("101", "区间错误"));
			return hospitalVo;
		}
		
		HospitalKey key = new HospitalKey();
		key.setHospitalId(hospitalId);
		key.setHospitalName(hospitalName);
		key.setHospitalType(hospitalType);
		key.setHospitalProvince(hospitalProvince);
		key.setHospitalAddress(hospitalAddress);
		key.setHospitalKeyword(hospitalKeyword);
		key.setOffset(fromNum - 1);
		key.setLimit(toNum - key.getOffset());

		try {			
			hospitalVo.setHospitalList(hospptalLogic.getHospitalInfoList(key));
			hospitalVo.setRsStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			hospitalVo.setRsStatus("ng");
			hospitalVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		
		return hospitalVo;
	};
}
