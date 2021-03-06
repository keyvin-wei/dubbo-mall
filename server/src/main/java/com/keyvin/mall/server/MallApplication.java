package com.keyvin.mall.server;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author weiwh
 * @date 2019/10/13 12:13
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.keyvin.mall"})
@MapperScan("com.keyvin.mall.model.dao")
@DubboComponentScan(basePackages = "com.keyvin.mall.server.service.impl")
@ImportResource("classpath:spring-dubbo.xml")
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }
}
