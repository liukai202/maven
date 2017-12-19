package com.maven.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @date 2017年12月19日 上午10:35:50
 * @author liukai
 * @description
 * 
 */
@RestController
public class HomeController {
	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/index");
		return mv;
	}
}
