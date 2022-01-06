package io.github.maodua.wrench.sysconfig.autoconfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@ComponentScan(basePackages = "io.github.maodua.wrench.sysconfig")
@MapperScan("io.github.maodua.wrench.sysconfig.mapper")
public class SysConfigAutoConfigure {}
