package com.maven;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.maven.security.MyFilterSecurityInterceptor;

/**
 * @date 2017年12月19日 下午3:43:06
 * @author liukai
 * @description 
 * 
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("myUserDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	@Qualifier("myAccessDecisionManager")
	private AccessDecisionManager accessDecisionManager;
	
	@Autowired
	@Qualifier("mySecurityMetadataSource")
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	private AuthenticationManager authenticationManager;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http 
		//自定义过滤器，把资源配置存放到数据库中
		.addFilterBefore(filter(),FilterSecurityInterceptor.class)
			.exceptionHandling()
			.and()
		.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/home")
            .and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.deleteCookies("remember-me")
			.and()
		.rememberMe()
			.tokenRepository(tokenRepository())
			.tokenValiditySeconds(1209600)//默认2周
			.rememberMeCookieName("remember-me")
			.userDetailsService(userDetailsService)
			.rememberMeParameter("remember-me");
    }
	
	private PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

	// 配置AuthenticationManager
	@Bean
	public AuthenticationManager authenticationManagerBean() {
		authenticationManagerBuilder.authenticationProvider(authenticationProvider());
		return authenticationManagerBuilder.getOrBuild();
	}

	// 获取AuthenticationManager，避免多次调用authenticationManagerBean()
	public AuthenticationManager getAuthenticationManager() {
		if (this.authenticationManager == null) {
			return authenticationManagerBean();
		} else {
			return this.authenticationManager;
		}
	}

	// 自定义JpaFilterSecurityInterceptor过滤器
	public MyFilterSecurityInterceptor filter() throws Exception {
		MyFilterSecurityInterceptor filter = new MyFilterSecurityInterceptor();
		// 认证管理器，实现用户认证的入口
		filter.setAuthenticationManager(getAuthenticationManager());
		// 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源
		filter.setAccessDecisionManager(accessDecisionManager);
		// 资源源数据定义，即定义某一资源可以被哪些角色访问
		filter.setSecurityMetadataSource(securityMetadataSource);
		return filter;
	}

	// 配置DaoAuthenticationProvider
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		// 设置不隐藏UserNotFoundExceptions
		authenticationProvider.setHideUserNotFoundExceptions(false);
		// 设置自定义UserDetailsService
		authenticationProvider.setUserDetailsService(userDetailsService);
		// 设置密码加密方式
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

}
