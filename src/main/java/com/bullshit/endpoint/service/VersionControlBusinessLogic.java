package com.bullshit.endpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.dao.VersionControlExtMapper;
import com.bullshit.endpoint.dao.VersionControlMapper;
import com.bullshit.endpoint.entity.VersionControl;

@Service("verLogic")
public class VersionControlBusinessLogic{

	@Autowired
	private VersionControlMapper versionControlMapper;

	@Autowired
	private VersionControlExtMapper versionControlExtMapper;
	
	public List<VersionControl> getAllVersion() throws Exception {
		return versionControlExtMapper.selectAllVersionInfo();
	}
}
