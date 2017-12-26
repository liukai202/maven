package com.maven.auth.dao;

import org.springframework.data.repository.CrudRepository;

import com.maven.auth.entity.Role;

/**
 * @author liukai
 * @date 2017年12月26日 下午4:30:27
 * @description 
 */
public interface RoleDao extends CrudRepository<Role, Long>{

}
