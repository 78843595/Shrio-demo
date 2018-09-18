package com.zhaozm.shirodemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = {"com.zhaozm.shirodemo.mapper"})
public class ShiroDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroDemoApplication.class, args);
	}
}
