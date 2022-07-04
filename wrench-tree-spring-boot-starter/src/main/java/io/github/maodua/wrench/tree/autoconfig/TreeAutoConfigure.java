package io.github.maodua.wrench.tree.autoconfig;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@ComponentScan(basePackages = "io.github.maodua.wrench.tree")
@MapperScan(basePackages = "io.github.maodua.wrench.tree.mapper", annotationClass = Mapper.class)
public class TreeAutoConfigure {}
