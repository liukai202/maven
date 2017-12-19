package com.maven.home.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date 2017年12月19日 上午10:35:50
 * @author liukai
 * @description
 * 
 */
@RestController
public class HomeController {
	@RequestMapping("/")
	public String home() {
		return "hello!";
	}
}
