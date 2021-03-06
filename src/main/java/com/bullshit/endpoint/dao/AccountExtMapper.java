package com.bullshit.endpoint.dao;

import java.util.List;

import com.bullshit.endpoint.entity.Account;
import com.bullshit.endpoint.entity.AccountKey;
import com.bullshit.endpoint.entity.AccountWithRelationStatus;
import com.bullshit.endpoint.entity.HXAccount;

public interface AccountExtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    List<HXAccount> selectRelationPatInfo(AccountKey key);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    List<Account> selectByAccountKey(AccountKey key);
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account
     *
     * @mbggenerated
     */
    Account selectByHxId(String hxId);
    
    List<AccountWithRelationStatus> selectRelationStatusInfo(AccountKey key);
}