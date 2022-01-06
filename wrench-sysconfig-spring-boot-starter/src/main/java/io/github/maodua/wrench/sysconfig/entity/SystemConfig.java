package io.github.maodua.wrench.sysconfig.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统配置
 */
@Data
@Accessors(chain = true)
@TableName("system_config")
public class SystemConfig implements Serializable {
    @TableId(type = IdType.INPUT)
    private Integer id;
    /**
     * 配置名
     */
    @TableField("`key`")
    private String key;
    /**
     * 配置值
     */
    @TableField("`value`")
    private String value;
    /**
     * 配置类型
     */
    private SystemConfigType type;
    /**
     * 配置的描述
     */
    private String remark;
}
