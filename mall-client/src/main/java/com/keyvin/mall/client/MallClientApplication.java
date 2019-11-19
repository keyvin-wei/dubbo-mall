package com.keyvin.mall.client;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.keyvin.mall"})
@MapperScan("com.keyvin.mall.client.dao")
@ImportResource("classpath:spring-dubbo.xml")
@DubboComponentScan(basePackages = "com.keyvin.mall.client.service")
public class MallClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallClientApplication.class, args);
    }

}
