package com.maven.auth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maven.auth.dao.AuthoritiesResourcesDao;
import com.maven.auth.dao.AuthorityDao;
import com.maven.auth.dao.ResourceDao;
import com.maven.auth.dao.RoleDao;
import com.maven.auth.dao.RolesAuthoritiesDao;
import com.maven.auth.dao.UserDao;
import com.maven.auth.dao.UsersRolesDao;
import com.maven.auth.entity.AuthoritiesResources;
import com.maven.auth.entity.Authority;
import com.maven.auth.entity.Resource;
import com.maven.auth.entity.Role;
import com.maven.auth.entity.RolesAuthorities;
import com.maven.auth.entity.User;
import com.maven.auth.entity.UsersRoles;
import com.maven.auth.service.InitDefaultAuthService;

/**
 * @author liukai
 * @date 2017年12月26日 下午4:14:58
 * @description 
 */
@Service
public class InitDefaultAuthServiceImpl implements InitDefaultAuthService{
	
	private final static Logger logger = LoggerFactory.getLogger(InitDefaultAuthServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private AuthorityDao authorityDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private AuthoritiesResourcesDao authoritiesResourcesDao;
	
	@Autowired
	private RolesAuthoritiesDao rolesAuthoritiesDao;
	
	@Autowired
	private UsersRolesDao usersRolesDao;

	/* (non-Javadoc)
	 * @see com.maven.auth.service.InitDefaultAuthService#initDefaultAuth()
	 */
	@Override
	public void initDefaultAuth() {
		
		User adminUser = this.userDao.findByUsername("admin");
		if(adminUser!=null) {
			return;
		}
		
		//初始化用户admin
		User user = new User();
		user.setAccountNonExpired(false);
		user.setAccountNonLocked(false);
		user.setCreateDate(new Date());
		user.setCredentialsNonExpired(false);
		user.setEnabled(true);
		user.setName("系统超级管理员");
		String password = "admin";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		password = passwordEncoder.encode(password);
		user.setPassword(password);
		user.setUsername("admin");
		user = this.userDao.save(user);
		logger.info("初始化admin:"+user.toString());
		
		
		Role admin = new Role();
		admin.setCode("ROLE_ADMIN");
		admin.setDescription("系统超级管理员角色");
		admin.setEnabled(true);
		admin.setName("系统超级管理员");
		admin = this.roleDao.save(admin);
		logger.info("初始化角色ROLE_ADMIN:"+admin.toString());
		
		Role anonymous = new Role();
		anonymous.setCode("ROLE_ANONYMOUS");
		anonymous.setDescription("系统匿名用户角色");
		anonymous.setEnabled(true);
		anonymous.setName("系统匿名用户");
		anonymous = this.roleDao.save(anonymous);
		logger.info("初始化角色ROLE_ANONYMOUS:"+anonymous.toString());
		
		
		Authority auth1 =  new Authority();
		auth1.setDescription("超级管理员权限");
		auth1.setEnabled(true);
		auth1.setName("管理员");
		auth1.setParentId(null);
		auth1 = this.authorityDao.save(auth1);
		logger.info("初始化管理员权限:"+auth1.toString());
		
		Authority auth2 =  new Authority();
		auth2.setDescription("匿名用户权限");
		auth2.setEnabled(true);
		auth2.setName("匿名用户");
		auth2.setParentId(null);
		auth2 = this.authorityDao.save(auth2);
		logger.info("初始化匿名用户权限:"+auth2.toString());
		
		
		Resource res1 = new Resource();
		res1.setDescription("系统所有请求URL");
		res1.setEnabled(true);
		res1.setName("所有资源");
		res1.setParentId(null);
		res1.setPath("/**");
		res1.setPriority(9999999);
		res1.setType("URL");
		res1 = this.resourceDao.save(res1);
		logger.info("初始化所有资源路径配置:"+res1.toString());
		
		Resource res2 = new Resource();
		res2.setDescription("系统所有静态资源路径");
		res2.setEnabled(true);
		res2.setName("静态资源");
		res2.setParentId(null);
		res2.setPath("/plugins");
		res2.setPriority(1);
		res2.setType("URL");
		res2 = this.resourceDao.save(res2);
		logger.info("初始化静态资源路径配置:"+res2.toString());
		
		Resource res3 = new Resource();
		res3.setDescription("系统浏览器图标路径");
		res3.setEnabled(true);
		res3.setName("favicon");
		res3.setParentId(null);
		res3.setPath("/favicon.ico");
		res3.setPriority(1);
		res3.setType("URL");
		res3 = this.resourceDao.save(res3);
		logger.info("初始化favicon路径配置:"+res3.toString());
		
		Resource res4 = new Resource();
		res4.setDescription("系统根路径");
		res4.setEnabled(true);
		res4.setName("根路径");
		res4.setParentId(null);
		res4.setPath("/");
		res4.setPriority(1);
		res4.setType("URL");
		res4 = this.resourceDao.save(res4);
		logger.info("初始化根路径配置:"+res4.toString());
		
		Resource res5 = new Resource();
		res5.setDescription("系统登录路径");
		res5.setEnabled(true);
		res5.setName("登录路径");
		res5.setParentId(null);
		res5.setPath("/login");
		res5.setPriority(1);
		res5.setType("URL");
		res5 = this.resourceDao.save(res5);
		logger.info("初始化登录路径配置:"+res5.toString());
		
		//配置管理员权限所拥有的资源
		List<AuthoritiesResources> authRes = new ArrayList<AuthoritiesResources>();
		AuthoritiesResources authRes1 = new AuthoritiesResources();
		authRes1.setAuthorityId(auth1.getId());
		authRes1.setResourceId(res1.getId());
		authRes.add(authRes1);
		
		AuthoritiesResources authRes2 = new AuthoritiesResources();
		authRes2.setAuthorityId(auth1.getId());
		authRes2.setResourceId(res2.getId());
		authRes.add(authRes2);
		
		AuthoritiesResources authRes3 = new AuthoritiesResources();
		authRes3.setAuthorityId(auth1.getId());
		authRes3.setResourceId(res3.getId());
		authRes.add(authRes3);
		
		AuthoritiesResources authRes4 = new AuthoritiesResources();
		authRes4.setAuthorityId(auth1.getId());
		authRes4.setResourceId(res4.getId());
		authRes.add(authRes4);
		
		AuthoritiesResources authRes5 = new AuthoritiesResources();
		authRes5.setAuthorityId(auth1.getId());
		authRes5.setResourceId(res5.getId());
		authRes.add(authRes5);
		
		//配置匿名用户所拥有的资源
		AuthoritiesResources authRes6 = new AuthoritiesResources();
		authRes6.setAuthorityId(auth2.getId());
		authRes6.setResourceId(res2.getId());
		authRes.add(authRes6);
		
		AuthoritiesResources authRes7 = new AuthoritiesResources();
		authRes7.setAuthorityId(auth2.getId());
		authRes7.setResourceId(res3.getId());
		authRes.add(authRes7);
		
		AuthoritiesResources authRes8 = new AuthoritiesResources();
		authRes8.setAuthorityId(auth2.getId());
		authRes8.setResourceId(res4.getId());
		authRes.add(authRes8);
		
		AuthoritiesResources authRes9 = new AuthoritiesResources();
		authRes9.setAuthorityId(auth2.getId());
		authRes9.setResourceId(res5.getId());
		authRes.add(authRes9);
		
		this.authoritiesResourcesDao.save(authRes);
		
		
		List<RolesAuthorities> roleAuth = new ArrayList<RolesAuthorities>();
		
		RolesAuthorities roleAuth1 = new RolesAuthorities();
		roleAuth1.setAuthorityId(auth1.getId());
		roleAuth1.setRoleId(admin.getId());
		roleAuth.add(roleAuth1);
		
		RolesAuthorities roleAuth2 = new RolesAuthorities();
		roleAuth2.setAuthorityId(auth2.getId());
		roleAuth2.setRoleId(anonymous.getId());
		roleAuth.add(roleAuth2);
		
		this.rolesAuthoritiesDao.save(roleAuth);
		
		
		UsersRoles userRole = new UsersRoles();
		userRole.setRoleId(admin.getId());
		userRole.setUserId(user.getId());
		this.usersRolesDao.save(userRole);
		
		logger.info("初始化完成!");
	}

}
