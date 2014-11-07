package com.bullshit.endpoint.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.dao.DocPatRelationMapper;
import com.bullshit.endpoint.entity.DocPatRelation;
import com.bullshit.endpoint.entity.DocPatRelationKey;

@Service("docPatRelationLogic")
public class DocPatRelationBusinessLogic {
	@Autowired
	private DocPatRelationMapper docPatRelationMapper;

	public DocPatRelation getDocPatRelation(DocPatRelationKey key) throws Exception {
		return docPatRelationMapper.selectByPrimaryKey(key);
	}
	
	public int createDocPatRelation(DocPatRelation record) throws Exception {
		return docPatRelationMapper.insertSelective(record);
	}
	
	public int updateDocPatRelation(DocPatRelation record) throws Exception {
		return docPatRelationMapper.updateByPrimaryKeySelective(record);
	}

	public int releaseDocPatRelation(DocPatRelation record) throws Exception {
		return docPatRelationMapper.updateByPrimaryKeySelective(record);
	}
	
	public int deleteDocPatRelation(DocPatRelationKey key) throws Exception {
		return docPatRelationMapper.deleteByPrimaryKey(key);
	}
}
