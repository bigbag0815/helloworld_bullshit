package com.bullshit.endpoint.v1;

import java.sql.Timestamp;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.Schedule;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.DocBusinessLogic;
import com.bullshit.endpoint.utils.Text2Md5;
import com.easemob.server.example.jersey.apidemo.EasemobIMUsers;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
@Path("/v1/acc")
public class AccessController {
	Logger log = LoggerFactory.getLogger(AccessController.class);

	@Autowired
	DocBusinessLogic docLogic;

	/**
	 * 登录验证 login 是医生和病人公用部分 给front返回 hxusername and hxpassword login
	 * 目前缺少token验证，暂且先不管
	 * **/
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Account registerinfo(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("roleflg") String roleflg) throws ApiException {
		System.out.println(username);
		System.out.println(password);
		System.out.println(roleflg);

		Boolean isContainUser = false;
		if (!isContainUser) {

			String hxusername = Text2Md5.getMD5Text(username);
			String hxpassword = Text2Md5.getMD5Text(password);
			ObjectNode resNode01 = EasemobIMUsers.getIMUsersByPrimaryKey(hxusername);

			if (resNode01.has("error")
					&& "service_resource_not_found".equals(resNode01.get("error").asText())) {

				ObjectNode dataNode = JsonNodeFactory.instance.objectNode();
				dataNode.put("username", hxusername);
				dataNode.put("password", hxpassword);
				ObjectNode resNode02 = EasemobIMUsers.createNewIMUserSingle(dataNode);

				if (!resNode02.has("error")) {
					Account account = new Account();
					account.setId("18240851231");
					/*
					 * roleflg 1.doc 2.pat
					 */
					account.setRoleflg("doc");
					account.setHxusername(hxusername);
					account.setHxpassword(hxpassword);
					account.setCtime(new Timestamp(System.currentTimeMillis()));
					account.setMtime(new Timestamp(System.currentTimeMillis()));

					return account;
				}
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
			if ("admin".equals(password)) {
				Account account = new Account();
				account.setId("18240851231");
				account.setName("张三");
				account.setImageurl("http://p0.qhimg.com/dmsmty/70_70_100/t016b4e0227f9b9b042.png");
				account.setAge("35");
				account.setDocTitle("教授");
				account.setDocProfessional("外科，手术");
				account.setMobilePhone("18622345678");
				account.setTelPhone("0103456789");
				account.setDocDescription("从业十五年，一直被模仿，从未被超越");
				account.setDocDepartmentName("外科");
				/*
				 * roleflg 1.doc 2.pat
				 */
				account.setRoleflg("doc");

				account.setHxusername("");
				account.setHxpassword("");
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
