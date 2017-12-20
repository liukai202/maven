/**
 * copyright by liukai
 */
package com.maven.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import com.maven.auth.service.AuthService;

/**
 * @author liukai
 * @date 2017年7月25日 下午5:42:39
 * @description 
 */
@Service("mySecurityMetadataSource")
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	
	private final static Logger logger = LoggerFactory.getLogger(MySecurityMetadataSource.class);
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	 public MySecurityMetadataSource(AuthService authService) {
		//查询所有资源路径和角色
		List<Map<String,Object>> resourceRoles = authService.findAllResourceAndRole();
		//根据URL组装权限Map key为URL，集合为权限集合
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		for(Map<String,Object> resourceRole :resourceRoles) {
			ConfigAttribute ca = new SecurityConfig((String) resourceRole.get("CODE"));
			String url = (String) resourceRole.get("PATH");
			if (resourceMap.containsKey(url)) {
				Collection<ConfigAttribute> value = resourceMap.get(url);
				value.add(ca);
				resourceMap.put(url, value);
			} else {
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				atts.add(ca);
				resourceMap.put(url, atts);
			}
		}
	 }
	 
	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url。
		String url = ((FilterInvocation) object).getRequestUrl();
		
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		AntPathMatcher matcher = new AntPathMatcher();
		matcher.setTrimTokens(false);
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (matcher.match(resURL, url)) {
				logger.info("资源配置URL为"+resURL+"请求的URL为："+url);
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
