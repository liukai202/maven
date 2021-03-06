package com.maven;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * @date 2017年12月19日 上午10:29:17
 * @author liukai
 * @description
 * 
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
