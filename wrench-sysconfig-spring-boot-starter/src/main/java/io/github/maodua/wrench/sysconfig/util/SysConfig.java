package io.github.maodua.wrench.sysconfig.util;

import io.github.maodua.wrench.common.util.Springs;
import io.github.maodua.wrench.sysconfig.entity.SystemConfigType;
import io.github.maodua.wrench.sysconfig.service.ISystemConfigService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 系统配置读写工具类，不应当在 Spring 初始化阶段调用本类的任何方法<br>
 * 因为配置随时可能会修改，因此也不应该在任何时候获取配置值后缓存
 */
@Slf4j
public class SysConfig {
    protected SysConfig() {
        throw new UnsupportedOperationException();
    }

    /**
     * 系统配置 服务层接口的实例
     */
    private static final ISystemConfigService systemConfigService = Springs.getBean(ISystemConfigService.class);
    /**
     * 配置列表
     * key: 配置类型
     * value: key: 配置名, value: 配置值
     */
    private static volatile Map<SystemConfigType, Map<String, Object>> conf;
    /**
     * 是否需要更新配置列表，用于修改配置后的更新
     */
    private static volatile boolean needRefresh = true;

    /**
     * 如果 {@linkplain #needRefresh} 为 true 则刷新配置
     */
    private static void autoRefresh() {
        if (SysConfig.needRefresh) {
            synchronized (SysConfig.class) {
                if (SysConfig.needRefresh) {
                    // 读取配置(顺便转换类型)
                    SysConfig.conf = new HashMap<>();
                    SysConfig.systemConfigService.list(null)
                            .forEach(s -> {
                                try {
                                    Map<String, Object> typeConf = SysConfig.conf.get(s.getType());
                                    if (Objects.isNull(typeConf)) {
                                        typeConf = new HashMap<>();
                                        SysConfig.conf.put(s.getType(), typeConf);
                                    }

                                    typeConf.put(s.getKey(), SysConfig.systemConfigService.convertValue(s.getType(), s.getValue()));
                                } catch (NumberFormatException | UnsupportedOperationException e) {
                                    throw new RuntimeException(String.format("parse system config fail: %s", s), e);
                                }
                            });

                    SysConfig.needRefresh = false;
                }
            }
        }
    }

    /**
     * 设置下次获取配置时需要更新
     */
    public static void refresh() {
        SysConfig.needRefresh = true;
    }

    /**
     * 检查配置类型并读取配置
     * @param key 配置的 key
     * @param type 预期的配置类型
     * @return 配置的 Config 对象
     * @throws NullPointerException 如果 key 不存在
     * @throws IllegalArgumentException 如果预期的配置类型不等于实际的配置类型
     */
    @NonNull
    private static <T> T getConfig(@NonNull String key, @NonNull SystemConfigType type) {
        // 刷新配置
        SysConfig.autoRefresh();
        // 获取配置
        Map<String, Object> typeConf = SysConfig.conf.get(type);
        if (Objects.nonNull(typeConf)) {
            @SuppressWarnings("unchecked")
            var result = (T) typeConf.get(key);
            if (Objects.nonNull(result)) {
                return result;
            }
        }

        throw new NullPointerException(String.format("key %s not exists.", key));
    }

    /**
     * 获取 String 类型的参数
     * @param key 配置名
     * @return 配置值
     */
    @NonNull
    public static String getString(@NonNull String key) {
        return SysConfig.getConfig(key, SystemConfigType.STRING);
    }

    /**
     * 获取 boolean 类型的参数
     * @param key 配置名
     * @return 配置值
     */
    @NonNull
    public static Boolean getBoolean(@NonNull String key) {
        return SysConfig.getConfig(key, SystemConfigType.BOOLEAN);
    }

    /**
     * 获取 long 类型的参数
     * @param key 配置名
     * @return 配置值
     */
    @NonNull
    public static Long getLong(@NonNull String key) {
        return SysConfig.getConfig(key, SystemConfigType.LONG);
    }

    /**
     * 获取 double 类型的参数
     * @param key 配置名
     * @return 配置值
     */
    @NonNull
    public static Double getDouble(@NonNull String key) {
        return SysConfig.getConfig(key, SystemConfigType.DOUBLE);
    }

    /**
     * 获取 BigInteger 类型的参数
     * @param key 配置名
     * @return 配置值
     */
    @NonNull
    public static BigInteger getInteger(@NonNull String key) {
        return SysConfig.getConfig(key, SystemConfigType.INTEGER);
    }

    /**
     * 获取 BigDecimal 类型的参数(百分比)
     * @param key 配置名
     * @return 配置值
     */
    @NonNull
    public static BigDecimal getDecimal(@NonNull String key) {
        return SysConfig.getConfig(key, SystemConfigType.DECIMAL);
    }

    /**
     * 获取 BigDecimal 类型的参数(百分比)
     * @param key 配置名
     * @return 配置值
     */
    @NonNull
    public static BigDecimal getPercentage(@NonNull String key) {
        return SysConfig.getConfig(key, SystemConfigType.PERCENTAGE);
    }

    /**
     * 获取 Date 类型的参数
     * @param key 配置名
     * @return 配置值
     */
    @NonNull
    public static LocalDateTime getDate(@NonNull String key) {
        return SysConfig.getConfig(key, SystemConfigType.DATE);
    }

}
