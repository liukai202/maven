/**
 * copyright by xiner
 */
package com.maven.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maven.auth.dao.UserDao;
import com.maven.auth.entity.Role;
import com.maven.auth.entity.User;

/**
 * @author xiner
 * @date 2017年7月15日 下午2:35:12
 * @description 自定义UserDetailsService，用户信息使用数据库的方式
 */
@Service("jpaUserDetailsService")
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private MessageSource messageSource;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDao.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException(this.messageSource.getMessage("DigestAuthenticationFilter.usernameNotFound", new Object[] {username}, null));
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Role> roles = user.getRoles();
		for(Role role:roles) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
		return userDetails;
	}

}