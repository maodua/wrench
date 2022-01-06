package io.github.maodua.wrench.file.autoconfig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@EnableConfigurationProperties
@ComponentScan(basePackages = "io.github.maodua.wrench.file")
@MapperScan("io.github.maodua.wrench.file.mapper")
public class FileAutoConfigure {}
