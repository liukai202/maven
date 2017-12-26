package com.maven.auth.dao;

import org.springframework.data.repository.CrudRepository;

import com.maven.auth.entity.RolesAuthorities;

/**
 * @author liukai
 * @date 2017年12月26日 下午5:06:08
 * @description 
 */
public interface RolesAuthoritiesDao extends CrudRepository<RolesAuthorities, Long>{

}
