package com.dh.springbootintegration.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.dh.springbootintegration.mybatis.repository")
public class MyBatisConfig {
}