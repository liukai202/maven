package com.maven.auth.dao;

import org.springframework.data.repository.CrudRepository;

import com.maven.auth.entity.Authority;

/**
 * @author liukai
 * @date 2017年12月26日 下午4:37:47
 * @description 
 */
public interface AuthorityDao extends CrudRepository<Authority, Long>{

}
