package com.bullshit.endpoint.v1;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.constants.Constants;
import com.bullshit.endpoint.entity.ErrInfo;
import com.bullshit.endpoint.entity.VersionControl;
import com.bullshit.endpoint.entity.vo.VerCtrlVo;
import com.bullshit.endpoint.exception.ApiException;
import com.bullshit.endpoint.service.VersionControlBusinessLogic;

@Component
@Path("/v1/ver")
public class VersionControlController {
	Logger log = LoggerFactory.getLogger(VersionControlController.class);

	@Autowired
	private VersionControlBusinessLogic verLogic;

	/* ### check客户端版本号，如果不是最新的，返回客户端更新用信息  */
	@GET
	@Path("/ckeckVersion/{version_number}")
	@Produces(MediaType.APPLICATION_JSON)
	public VerCtrlVo getCaseInfoByPatientId(
			@PathParam("version_number") String versionNumber) throws ApiException {
		VerCtrlVo verCtrlVo = new VerCtrlVo();
		try {
			List<VersionControl> verCtrlList = verLogic.getAllVersion();
			// 从DB中取出来的数据是倒序，最新的是第一条。
			boolean isNewestVersionFlg = true;
			boolean isValidVerionNumberFlg = false;
			for (VersionControl verCtrl : verCtrlList) {
				if (StringUtils.equals(versionNumber, verCtrl.getVersionNumber())) {
					isValidVerionNumberFlg = true;
					break;
				}
				isNewestVersionFlg = false;
			}
			if (!isValidVerionNumberFlg) {
				verCtrlVo.setRsStatus("ng");
				verCtrlVo.setErrInfo(new ErrInfo("101", "不是一个合法的版本号"));
				return verCtrlVo;
			}
			verCtrlVo.setNewestVersionFlg(isNewestVersionFlg);
			if (!isNewestVersionFlg) {
				VersionControl verCtrl = verCtrlList.get(0);
				verCtrl.setVersionUrl(Constants.VERSION_UPDATE_PREFIX_URL + verCtrl.getVersionUrl());
				verCtrlVo.setVersionControl(verCtrl);
			}
			verCtrlVo.setRsStatus("ok");
		} catch (Exception e) {
			e.printStackTrace();
			verCtrlVo.setRsStatus("ng");
			verCtrlVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		return verCtrlVo;
	};
}
