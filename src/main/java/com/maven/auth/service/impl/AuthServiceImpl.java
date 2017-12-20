package com.maven.auth.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maven.auth.dao.AuthDao;
import com.maven.auth.service.AuthService;

/**
 * @date 2017年12月20日 下午2:53:38
 * @author liukai
 * @description
 * 
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthDao authDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.maven.auth.service.AuthService#findAllResourceAndRole()
	 */
	@Override
	public List<Map<String,Object>> findAllResourceAndRole() {
		return this.authDao.findAllResourceAndRole();
	}

}
