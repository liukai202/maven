package com.maven.auth.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.maven.auth.dao.AuthDao;

/**
 * @date 2017年12月20日 下午3:50:13
 * @author liukai
 * @description
 * 
 */
@Repository
public class AuthDaoImpl implements AuthDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.maven.auth.dao.AuthDao#findAllResourceAndRole()
	 */
	@Override
	public List<Map<String,Object>> findAllResourceAndRole() {
		String sql = "select res.path, role.code from sys_resource res\r\n"
				+ "  left join sys_authorities_resources ar on ar.resource_Id = res.id\r\n"
				+ "  left join sys_roles_authorities ra on ra.authority_Id = ar.authority_Id\r\n"
				+ "  left join sys_role role on role.id = ra.role_Id\r\n"
				+ "where res.path is not null and res.type = 'URL' and role.code is not null order by res.priority asc";
		List<Map<String,Object>> list = this.jdbcTemplate.queryForList(sql);
		return list;
	}

}
