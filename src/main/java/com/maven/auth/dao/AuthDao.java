package com.maven.auth.dao;

import java.util.List;
import java.util.Map;

/**
 * @date 2017年12月20日 下午2:52:54
 * @author liukai
 * @description 
 * 
 */
public interface AuthDao {
	public List<Map<String,Object>> findAllResourceAndRole();
}
