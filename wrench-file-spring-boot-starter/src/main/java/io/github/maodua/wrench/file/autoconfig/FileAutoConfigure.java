package io.github.maodua.wrench.file.autoconfig;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties(FileStoreConfigure.class)
@ComponentScan(basePackages = "io.github.maodua.wrench.file")
@MapperScan(basePackages = "io.github.maodua.wrench.file.mapper", annotationClass = Mapper.class)
public class FileAutoConfigure {}
