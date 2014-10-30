package com.bullshit.endpoint.v1;

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

import com.bullshit.endpoint.entity.schedule;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.DocBusinessLogic;


@Component
@Path("/v1/doc")
public class DocController {
	Logger log=LoggerFactory.getLogger(DocController.class);
	
	@Autowired
	DocBusinessLogic docLogic;
	
	@GET
	@Path("/schedulelist/{did}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<schedule> docscheduleList(@PathParam("did") String did) throws ApiException{
		log.debug("===========当前的scheduleList==================");
	    log.debug("request params:");
	    log.debug(did);
		
		List<schedule> schedulelist=docLogic.scheduleList(Integer.valueOf(did));
		return schedulelist;	
	}
	

	@GET
	@Path("/schedulelist/page/{did}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<schedule> docscheduleListOrder(@PathParam("did") String did,@DefaultValue("1") @QueryParam("from") int fromNum,@DefaultValue("999999999") @QueryParam("to") int toNum,@DefaultValue("id") @QueryParam("order") String order) throws ApiException{
	   /*正常调用
	    * List<schedule> schedulelist=docLogic.scheduleList(Integer.valueOf(did));
		for (schedule schedule : schedulelist) {
			log.debug(schedule.getContent());
		}*/
		/*测试含有异常的方法*/
		List<schedule> schedulelist=docLogic.testExcetpion(Integer.valueOf(did));
		return schedulelist;
		
	}
}
