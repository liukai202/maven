package com.maven.auth.service;

import java.util.List;
import java.util.Map;

/**
 * @date 2017年12月20日 下午2:53:18
 * @author liukai
 * @description
 * 
 */
public interface AuthService {

	// 查询所有权限和资源路径
	public List<Map<String,Object>> findAllResourceAndRole();

}
