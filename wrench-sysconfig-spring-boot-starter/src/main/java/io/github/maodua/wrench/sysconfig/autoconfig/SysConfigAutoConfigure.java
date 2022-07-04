package io.github.maodua.wrench.sysconfig.autoconfig;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@ComponentScan(basePackages = "io.github.maodua.wrench.sysconfig")
@MapperScan(basePackages = "io.github.maodua.wrench.sysconfig.mapper", annotationClass = Mapper.class)
public class SysConfigAutoConfigure {}
