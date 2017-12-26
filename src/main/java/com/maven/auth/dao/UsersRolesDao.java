package com.maven.auth.dao;

import org.springframework.data.repository.CrudRepository;

import com.maven.auth.entity.UsersRoles;

/**
 * @author liukai
 * @date 2017年12月26日 下午5:06:58
 * @description 
 */
public interface UsersRolesDao extends CrudRepository<UsersRoles, Long>{

}
