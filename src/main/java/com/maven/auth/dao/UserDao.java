package com.maven.auth.dao;

import org.springframework.data.repository.RepositoryDefinition;

import com.maven.auth.entity.User;

/**
 * @date 2017年12月20日 下午3:28:09
 * @author liukai
 * @description 
 * 
 */
@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserDao {
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return User
	 */
	public User findByUsername(String username);
}
