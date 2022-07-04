package io.github.maodua.wrench.sysconfig.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.maodua.wrench.sysconfig.entity.SystemConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置 持久层
 */
@Mapper
public interface SystemConfigMapper extends BaseMapper<SystemConfig> {}
