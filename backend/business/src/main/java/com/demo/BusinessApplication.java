package com.demo;

import com.demo.util.SwaggerStartUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value={"com.demo.mapper"})
public class BusinessApplication {
	public static void main(String[] args) {
		SwaggerStartUtil.start(BusinessApplication.class, args);
	}
}
