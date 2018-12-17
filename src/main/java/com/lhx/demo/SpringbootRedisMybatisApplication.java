package com.lhx.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.lhx.demo")
//mapper 接口类扫描包配置
@MapperScan("com.lhx.demo.dao")
public class SpringbootRedisMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRedisMybatisApplication.class, args);
	}
}
