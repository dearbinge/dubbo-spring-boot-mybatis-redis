package com.dearbinge.data.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dearbinge.data.api.SecurityService;
import com.dearbinge.data.dao.SecurityMapper;
import com.dearbinge.data.pojo.Security;

@Service(value = "securityService")
public class SecurityServiceImpl implements SecurityService {

	@Resource
	private SecurityMapper securityMapper;

	@Override
	public Security getSecurityByKey(String key) {
		// TODO Auto-generated method stub
		Security security = securityMapper.selectByPrimaryKey(key);
		return security;
	}
}
