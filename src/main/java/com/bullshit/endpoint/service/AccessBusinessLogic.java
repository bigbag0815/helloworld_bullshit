package com.bullshit.endpoint.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bullshit.endpoint.constants.Constants;
import com.bullshit.endpoint.dao.AccountExtMapper;
import com.bullshit.endpoint.dao.AccountMapper;
import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.AccountKey;
import com.bullshit.endpoint.entity.HXAccount;
import com.bullshit.endpoint.utils.StringUtil;

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
		return changeImageUrl(accountMapper.selectByPrimaryKey(id));
	}
	
	public Account getAccountInfoByHX(String hxId) throws Exception {
		return changeImageUrl(accountExtMapper.selectByHxId(hxId));
	}

	public int updateAccount(Account account) throws Exception {
		return accountMapper.updateByPrimaryKeySelective(account);
	}

	public List<HXAccount> getRelationPatInfo(AccountKey key) throws Exception {
		List<HXAccount> res = new ArrayList<HXAccount>();
		List<HXAccount> list = accountExtMapper.selectRelationPatInfo(key);
		for (HXAccount account : list) {
			if (StringUtils.isNotBlank(account.getImageurl())) {
				account.setImageurl(StringUtil.urlPathEdit(account.getImageurl(), Constants.QINIU_PREFIX_URL));
			}
			res.add(account);
		}
		return res;
	}
	
	public List<Account> getByAccountKey(AccountKey key) throws Exception {
		return editList(accountExtMapper.selectByAccountKey(key));
	}
	
	private List<Account> editList(List<Account> accountList) {
		List<Account> res = new ArrayList<Account>();
		for (Account account : accountList) {
			res.add(changeImageUrl(account));
		}
		return res;
	}
	
	private Account changeImageUrl(Account account) {
		if (StringUtils.isNotBlank(account.getImageurl())) {
			account.setImageurl(StringUtil.urlPathEdit(account.getImageurl(), Constants.QINIU_PREFIX_URL));
		}
		return account;
	}
}
