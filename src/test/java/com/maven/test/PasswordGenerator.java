package com.maven.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @date 2017年12月21日 上午10:06:07
 * @author liukai
 * @description
 * 
 */
public class PasswordGenerator {
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("password"));
	}
}
