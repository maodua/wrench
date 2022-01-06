package io.github.maodua.wrench.tree.autoconfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@ComponentScan(basePackages = "io.github.maodua.wrench.tree")
@MapperScan("io.github.maodua.wrench.tree.mapper")
public class TreeAutoConfigure {}
