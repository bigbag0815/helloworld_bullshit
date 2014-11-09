package com.bullshit.endpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.dao.AccountExtMapper;
import com.bullshit.endpoint.dao.AccountMapper;
import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.AccountKey;

@Service("accessLogic")
public class AccessBusinessLogic {
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private AccountExtMapper accountExtMapper;

	public int registerUser(Account account) throws Exception {
		return accountMapper.insertSelective(account);
	}

	public boolean isContainUser(String id) throws Exception {
		if (accountMapper.selectByPrimaryKey(id) != null) {
			return true;
		}
		return false;
	}

	public Account getAccountInfo(String id) throws Exception {
		return accountMapper.selectByPrimaryKey(id);
	}

	public int updateAccount(Account account) throws Exception {
		return accountMapper.updateByPrimaryKeySelective(account);
	}

	public List<Account> getRelationPatInfo(AccountKey key) throws Exception {
		return accountExtMapper.selectRelationPatInfo(key);
	}
	
	public List<Account> getByAccountKey(AccountKey key) throws Exception {
		return accountExtMapper.selectByAccountKey(key);
	}
	
}
