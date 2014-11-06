package com.bullshit.endpoint.v1;

import java.sql.Timestamp;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.ErrInfo;
import com.bullshit.endpoint.entity.vo.AccessReq;
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
	@Produces(MediaType.APPLICATION_JSON)
	public AccessVo registerAccountInfo(AccessReq accessReq) {
		String username = accessReq.getUsername();
		String password = accessReq.getPassword();
		String roleflg = accessReq.getRoleflg();
		
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
						account.setRoleflg(roleflg);
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
						account.setRoleflg(roleflg);
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
	@Produces(MediaType.APPLICATION_JSON)
	public AccessVo loginAccountInfo(AccessReq accessReq) {
		String username = accessReq.getUsername();
		String password = accessReq.getPassword();
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
	
	
	
	/**
	 * 更新DocAccount信息     医生
	 * **/
	@POST
	@Path("/update/docinfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Account updateDocAccountInfo(Account docAccount) throws Exception {
		if ("doc".equals(docAccount.getRoleflg())) {
			Account account = accessLogic.getAccountInfo(docAccount.getId());
			if(null != account){
				account.setName(docAccount.getName());
				account.setAge(docAccount.getAge());
				account.setSex(docAccount.getSex());
				account.setImageurl(docAccount.getImageurl());
				account.setMobilePhone(docAccount.getMobilePhone());
				account.setTelPhone(docAccount.getTelPhone());
				account.setDocTitle(docAccount.getDocTitle());
				account.setDocProfessional(docAccount.getDocHospital());
				account.setDocDepartmentName(docAccount.getDocDepartmentName());
				account.setDocDescription(docAccount.getDocDescription());
				account.setDocHospital(docAccount.getDocHospital());
			}
			
			docAccount.setMtime(new Timestamp(System.currentTimeMillis()));
			if(accessLogic.updateAccount(account)>0){
				return account;
			}
		}
		return null;
	}
	
	/**
	 * 更新PatAccount信息     医生
	 * **/
	@POST
	@Path("/update/patinfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Account updatePatAccountInfo(Account patAccount) throws Exception {
		if ("pat".equals(patAccount.getRoleflg()) && accessLogic.isContainUser(patAccount.getId())) {
			patAccount.setMtime(new Timestamp(System.currentTimeMillis()));
			if(accessLogic.updateAccount(patAccount)>0){
				return patAccount;
			}
		}
		return null;
	}

}
