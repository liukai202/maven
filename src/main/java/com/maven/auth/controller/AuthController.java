package com.maven.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.maven.auth.service.InitDefaultAuthService;

/**
 * @date 2017年12月20日 下午2:44:18
 * @author liukai
 * @description
 * 
 */
@Controller
public class AuthController {

	@Autowired
	private InitDefaultAuthService initDefaultAuthService;

	@RequestMapping(value = "initDefaultAuth")
	public ModelAndView initDefaultAuth(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/login");
		initDefaultAuthService.initDefaultAuth();
		return mv;
	}

}
