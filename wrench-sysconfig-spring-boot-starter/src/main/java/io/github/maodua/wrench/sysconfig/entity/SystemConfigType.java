package io.github.maodua.wrench.sysconfig.entity;

import lombok.Getter;
import lombok.ToString;

/**
 * 系统配置类型，用于做参数有效性检查
 */
@ToString
public enum SystemConfigType {
    STRING("字符串"),
    BOOLEAN,
    LONG,
    DOUBLE,
    INTEGER("BigInteger，长整数"),
    DECIMAL("BigDecimal，长浮点数"),
    PERCENTAGE("BigDecimal，百分比数", "前端应在展示这种类型的数据之前将数字乘以 100，并在传给后端之前除以 100，内部会以 1 代表 100%"),
    DATE("Date，时间");
    /**
     * 类型的名称
     */
    @Getter
    private final String name;
    /**
     * 类型的描述
     */
    @Getter
    private final String remark;

    SystemConfigType() {
        this(null);
    }

    SystemConfigType(String name) {
        this(name, name);
    }

    SystemConfigType(String name, String remark) {
        this.name = name == null ? this.name() : name;
        this.remark = remark == null ? this.name : remark;
    }
}
