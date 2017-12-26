package com.maven.auth.dao;

import org.springframework.data.repository.CrudRepository;

import com.maven.auth.entity.Resource;

/**
 * @date 2017年12月20日 下午3:19:57
 * @author liukai
 * @description
 * 
 */
public interface ResourceDao extends CrudRepository<Resource, Long>{
	
}
