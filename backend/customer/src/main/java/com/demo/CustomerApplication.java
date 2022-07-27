package com.demo;

import com.demo.util.SwaggerStartUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value={"com.demo.mapper"})
public class CustomerApplication {

	public static void main(String[] args) {
		SwaggerStartUtil.start(CustomerApplication.class, args);
	}

}
