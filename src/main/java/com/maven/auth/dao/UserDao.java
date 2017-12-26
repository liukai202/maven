package com.maven.auth.dao;

import org.springframework.data.repository.CrudRepository;

import com.maven.auth.entity.User;

/**
 * @date 2017年12月20日 下午3:28:09
 * @author liukai
 * @description 
 * 
 */
public interface UserDao extends CrudRepository<User, Long>{
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return User
	 */
	public User findByUsername(String username);
}
