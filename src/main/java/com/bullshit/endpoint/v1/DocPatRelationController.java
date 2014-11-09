package com.bullshit.endpoint.v1;

import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullshit.endpoint.entity.DocPatRelation;
import com.bullshit.endpoint.entity.DocPatRelationKey;
import com.bullshit.endpoint.entity.ErrInfo;
import com.bullshit.endpoint.entity.vo.DocPatRelationReq;
import com.bullshit.endpoint.entity.vo.DocPatRelationVo;
import com.bullshit.endpoint.service.DocPatRelationBusinessLogic;
import com.bullshit.endpoint.utils.DateUtil;

@Component
@Path("/v1/rel")
public class DocPatRelationController {
	Logger log = LoggerFactory.getLogger(DocPatRelationController.class);

	@Autowired
	DocPatRelationBusinessLogic docPatRelationBusinessLogic;
	
	/**
	 * 患者提出一个和医生联系的请求
	 */
	@POST
	@Path("/apply")
	@Produces(MediaType.APPLICATION_JSON)
	public DocPatRelationVo applyRelation(DocPatRelationReq docPatRelationReq) {
		String docId = docPatRelationReq.getDocId();
		String patId = docPatRelationReq.getPatId();
		
		DocPatRelationVo docRatRelationVo = new DocPatRelationVo();
		
		if (StringUtils.equals(patId, docId)) {
			docRatRelationVo.setRsStatus("ng");
			docRatRelationVo.setErrInfo(new ErrInfo("101", "不能添加自己为好友"));
			return docRatRelationVo;
		}
		
		DocPatRelationKey key = new DocPatRelationKey();
		key.setDocId(docId);
		key.setPatId(patId);

		Date currentDate = DateUtil.getCurrentDate();
		
		DocPatRelation docPatRelationData = new DocPatRelation();
		docPatRelationData.setDocId(docId);
		docPatRelationData.setPatId(patId);
		docPatRelationData.setIsactive("active");
		docPatRelationData.setRelationStatus("apl");
		docPatRelationData.setMtime(currentDate);
		
		try {
			DocPatRelation docPatRelationResults = docPatRelationBusinessLogic.getDocPatRelation(key);
			if (null == docPatRelationResults) {
				docPatRelationData.setCtime(currentDate);
				int rs = docPatRelationBusinessLogic.createDocPatRelation(docPatRelationData);
				if (rs > 0){
					docRatRelationVo.setRsStatus("ok");
					docRatRelationVo.setDocPatRelation(docPatRelationBusinessLogic.getDocPatRelation(key));
				} else {
					docRatRelationVo.setRsStatus("ng");
					docRatRelationVo.setErrInfo(new ErrInfo("102", "医患关系创建失败，请重试"));
				}
			} else {
				if (StringUtils.equals("active", docPatRelationResults.getIsactive())) {
					int rs = docPatRelationBusinessLogic.updateDocPatRelation(docPatRelationData);
					if (rs > 0){
						docRatRelationVo.setRsStatus("ok");
						docRatRelationVo.setDocPatRelation(docPatRelationBusinessLogic.getDocPatRelation(key));
					} else {
						docRatRelationVo.setRsStatus("ng");
						docRatRelationVo.setErrInfo(new ErrInfo("102", "医患关系创建失败，请重试"));
					}
				} else {
					docRatRelationVo.setRsStatus("ng");
					docRatRelationVo.setErrInfo(new ErrInfo("500", "已拉黑"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			docRatRelationVo.setRsStatus("ng");
			docRatRelationVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		
		return docRatRelationVo;
	}
	
	/**
	 * 医生响应患者提出的请求
	 */
	@POST
	@Path("/accept")
	@Produces(MediaType.APPLICATION_JSON)
	public DocPatRelationVo acceptRelation(DocPatRelationReq docPatRelationReq) {
		String docId = docPatRelationReq.getDocId();
		String patId = docPatRelationReq.getPatId();
		
		DocPatRelationVo docRatRelationVo = new DocPatRelationVo();
		
		DocPatRelationKey key = new DocPatRelationKey();
		key.setDocId(docId);
		key.setPatId(patId);
		
		DocPatRelation docPatRelationData = new DocPatRelation();
		docPatRelationData.setDocId(docId);
		docPatRelationData.setPatId(patId);
		docPatRelationData.setRelationStatus("ok");
		docPatRelationData.setMtime(DateUtil.getCurrentDate());
		
		try {
			DocPatRelation docPatRelationResults = docPatRelationBusinessLogic.getDocPatRelation(key);
			if (null == docPatRelationResults) {
				docRatRelationVo.setRsStatus("ng");
				docRatRelationVo.setErrInfo(new ErrInfo("101", "没有患者请求联系，请确认"));
			} else {
				if (StringUtils.equals("active", docPatRelationResults.getIsactive())) {
					int rs = docPatRelationBusinessLogic.updateDocPatRelation(docPatRelationData);
					if (rs > 0){
						docRatRelationVo.setRsStatus("ok");
						docRatRelationVo.setDocPatRelation(docPatRelationBusinessLogic.getDocPatRelation(key));
					} else {
						docRatRelationVo.setRsStatus("ng");
						docRatRelationVo.setErrInfo(new ErrInfo("102", "医患关系创建失败，请重试"));
					}
				} else {
					docRatRelationVo.setRsStatus("ng");
					docRatRelationVo.setErrInfo(new ErrInfo("500", "您已拉黑此患者"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			docRatRelationVo.setRsStatus("ng");
			docRatRelationVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		
		return docRatRelationVo;
	}
	
	/**
	 * 患者或者医生主动解除和患者的联系
	 */
	@POST
	@Path("/refuse")
	@Produces(MediaType.APPLICATION_JSON)
	public DocPatRelationVo refuseRelation(DocPatRelationReq docPatRelationReq) {
		String docId = docPatRelationReq.getDocId();
		String patId = docPatRelationReq.getPatId();
		
		DocPatRelationVo docRatRelationVo = new DocPatRelationVo();
		
		DocPatRelationKey key = new DocPatRelationKey();
		key.setDocId(docId);
		key.setPatId(patId);
		
		DocPatRelation docPatRelationData = new DocPatRelation();
		docPatRelationData.setDocId(docId);
		docPatRelationData.setPatId(patId);
		docPatRelationData.setIsactive("active");
		docPatRelationData.setRelationStatus("ng");
		docPatRelationData.setMtime(DateUtil.getCurrentDate());
		
		try {
			DocPatRelation docPatRelationResults = docPatRelationBusinessLogic.getDocPatRelation(key);
			if (null == docPatRelationResults) {
				docRatRelationVo.setRsStatus("ng");
				docRatRelationVo.setErrInfo(new ErrInfo("102", "没有正在联系，请确认"));
			} else {
				if (StringUtils.equals("active", docPatRelationResults.getIsactive())) {
					int rs = docPatRelationBusinessLogic.updateDocPatRelation(docPatRelationData);
					if (rs > 0){
						docRatRelationVo.setRsStatus("ok");
						docRatRelationVo.setDocPatRelation(docPatRelationBusinessLogic.getDocPatRelation(key));
					} else {
						docRatRelationVo.setRsStatus("ng");
						docRatRelationVo.setErrInfo(new ErrInfo("102", "医患关系创建失败，请重试"));
					}
				} else {
					docRatRelationVo.setRsStatus("ng");
					docRatRelationVo.setErrInfo(new ErrInfo("500", "已拉黑"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			docRatRelationVo.setRsStatus("ng");
			docRatRelationVo.setErrInfo(new ErrInfo("500", e.getMessage()));
		}
		
		return docRatRelationVo;
	}	
}
