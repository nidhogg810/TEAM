package com.example.demo;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = "com.example.demo.common.mapper")
@EnableCaching
public class SpringbootDemoApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(SpringbootDemoApplication.class);
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootDemoApplication.class, args);
		System.setProperty("project.name",context.getEnvironment().getProperty("spring.application.name","sentinel"));
		System.setProperty("csp.sentinel.dashboard.server",context.getEnvironment().getProperty("sentinel.dashboard.server","localhost:9090"));
		InitExecutor.doInit();

	}

}
