package com.bullshit.endpoint.v1;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.Cases;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.DocBusinessLogic;

@Component
@Path("/v1/pat")
public class PatController {
	Logger log = LoggerFactory.getLogger(PatController.class);

	@Autowired
	DocBusinessLogic docLogic;

	/* 病人填写病例 */
	@POST
	@Path("/cases/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertPatientCase(@FormParam("user_id") String user_id,
			@FormParam("message") String message, FormDataMultiPart multiPart)
			throws ApiException {
		for (BodyPart bodyPart : multiPart.getBodyParts()) {
			System.out.println(bodyPart.getEntityAs(String.class));
		}
		return null;
	};

	/* ### 通过病人id获取病例 */
	@GET
	@Path("/caseinfo/{patient_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cases getCaseInfoByPatientId(
			@PathParam("patient_id") String patient_id) throws ApiException {
		Cases caseinfo = new Cases();
		caseinfo.setId(1);
		caseinfo.setPatient_id(Integer.valueOf(patient_id));
		// 根据相关需要 ，把名字也给返回
		caseinfo.setDoctor_id(1);
		caseinfo.setAllergyDrug("青霉素过敏");
		caseinfo.setHandlingSuggestion("住院观察");
		return caseinfo;
	};

}
