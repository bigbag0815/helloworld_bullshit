package com.bullshit.endpoint.v1;

import java.sql.Timestamp;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.ErrInfo;
import com.bullshit.endpoint.entity.vo.AccessVo;
import com.bullshit.endpoint.service.AccessBusinessLogic;
import com.bullshit.endpoint.utils.Text2Md5;

import com.easemob.server.example.jersey.apidemo.EasemobIMUsers;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
@Path("/v1/acc")
public class AccessController {
	Logger log = LoggerFactory.getLogger(AccessController.class);

	@Autowired
	AccessBusinessLogic accessLogic;
	
	/**
	 * 用户注册
	 * 将 用户名、密码、roleflg、hxusername、hxpassword 插入db
	 * **/
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public AccessVo registerAccountInfo(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("roleflg") String roleflg) {
		System.out.println(username);
		System.out.println(password);
		System.out.println(roleflg);
		
		AccessVo accessVo = new AccessVo();
		try {
			//bullshitdb中是否存在此账号
			if (!accessLogic.isContainUser(username)) {
				String hxusername = Text2Md5.getMD5Text(username);
				String hxpassword = Text2Md5.getMD5Text(password);
				ObjectNode resNode01 = EasemobIMUsers.getIMUsersByPrimaryKey(hxusername);
				//hxdb中是否存在此账号
				if (resNode01.has("error")
						&& "service_resource_not_found".equals(resNode01.get("error").asText())) {
					//创建hx账号
					ObjectNode dataNode = JsonNodeFactory.instance.objectNode();
					dataNode.put("username", hxusername);
					dataNode.put("password", hxpassword);
					ObjectNode resNode02 = EasemobIMUsers.createNewIMUserSingle(dataNode);
					
					Account account = new Account();
					//hx账号创建成功，将hxusername和hxpassword写入DB
					if (!resNode02.has("error")) {
						account.setId(username);
						account.setPw(Text2Md5.getMD5Text(password));
						/*
						 * roleflg 1.doc 2.pat
						 */
						account.setRoleflg("doc");
						account.setIsactive("active");
						account.setHxusername(hxusername);
						account.setHxpassword(hxpassword);
						account.setCtime(new Timestamp(System
								.currentTimeMillis()));
						account.setMtime(new Timestamp(System
								.currentTimeMillis()));
						accessLogic.registerUser(account);
					//hx账号创建失败，不写入hxusername和hxpassword
					}else{
						account.setId(username);
						account.setPw(Text2Md5.getMD5Text(password));
						/*
						 * roleflg 1.doc 2.pat
						 */
						account.setRoleflg("doc");
						account.setIsactive("active");
						account.setCtime(new Timestamp(System
								.currentTimeMillis()));
						account.setMtime(new Timestamp(System
								.currentTimeMillis()));
						accessLogic.registerUser(account);
					}
					accessVo.setRsStatus("ok");
					accessVo.setAccountInfo(account);
				} else if (resNode01.has("entities")) {
					// hxusername已经被注册
					accessVo.setRsStatus("ng");
					accessVo.setErrInfo(new ErrInfo("101", "此用户名已被hx注册"));
				}
			}else {
				//bullshitdb中此用户名已经被注册
				accessVo.setRsStatus("ng");
				accessVo.setErrInfo(new ErrInfo("102", "您输入的用户名已被注册"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			accessVo.setRsStatus("ng");
			accessVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		
		return accessVo;
	}

	/**
	 * 登录验证 login 是医生和病人公用部分 给front返回 hxusername and hxpassword login
	 * **/
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public AccessVo loginAccountInfo(@FormParam("username") String username,
			@FormParam("password") String password) {
		System.out.println(username);
		System.out.println(password);
		AccessVo accessVo = new AccessVo();
		try {
			Account account = accessLogic.getAccountInfo(username);
			if (null != account) {
				if (Text2Md5.getMD5Text(password).equals(account.getPw())) {
					
					if (account.getHxusername() == null) {
						String hxusername = Text2Md5.getMD5Text(username);
						String hxpassword = Text2Md5.getMD5Text(password);
						ObjectNode dataNode = JsonNodeFactory.instance.objectNode();
						dataNode.put("username", hxusername);
						dataNode.put("password", hxpassword);
						ObjectNode resNode02 = EasemobIMUsers.createNewIMUserSingle(dataNode);
						if (!resNode02.has("error")) {
							account.setHxusername(hxusername);
							account.setHxpassword(hxpassword);
						}
					}
					accessVo.setRsStatus("ok");
					accessVo.setAccountInfo(account);
				} else {
					accessVo.setRsStatus("ng");
					accessVo.setErrInfo(new ErrInfo("201", "您输入的密码不正确"));
				}
			} else {
				accessVo.setRsStatus("ng");
				accessVo.setErrInfo(new ErrInfo("202", "您输入的用户名不存在"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			accessVo.setRsStatus("ng");
			accessVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		return accessVo;
	}
	
	
/**	bk
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Account loginAccountInfo(@FormParam("username") String username,
			@FormParam("password") String password) throws ApiException {
		System.out.println(username);
		System.out.println(password);

		Account account = accessLogic.getAccountInfo(username);
		if (account != null) {
			password = Text2Md5.getMD5Text(password);
			if (password.equals(account.getPw())) {
				return account;
			}
		}
		return null;
	}

	**/
	
	/**
	 * 更新Account信息 是医生
	 * **/
	
	/**	
	@POST
	@Path("/update/docinfo")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Account updateDocAccountInfo(Account docAccount) throws Exception {
		System.out.println("1111111111");
		if (accessLogic.isContainUser(docAccount.getId())) {
			System.out.println("22222222222222");
			docAccount.setMtime(new Timestamp(System.currentTimeMillis()));
			System.out.println("333333333333333");
			if(accessLogic.updateAccount(docAccount)>0){
				System.out.println("44444444444444444");
				return docAccount;
			}
		}
		return null;
	}
	**/
}
