package io.github.maodua.wrench.sysconfig.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.maodua.wrench.common.vo.result.Result;
import io.github.maodua.wrench.sysconfig.entity.SystemConfig;
import io.github.maodua.wrench.sysconfig.entity.SystemConfigType;
import lombok.NonNull;

import java.util.Optional;

/**
 * 系统配置 服务层接口
 */
public interface ISystemConfigService extends IService<SystemConfig> {
    /**
     * 根据 key 查询系统配置
     * @param key 被查询的 key
     * @return 查到的系统配置
     */
    Optional<SystemConfig> getByKey(@NonNull String key);

    /**
     * 根据 key 更新 val
     * @param key 被更新的 key
     * @param val 新的 val
     * @param operator 操作人
     * @return 更新是否成功
     */
    Result<?> updateValue(@NonNull String key, @NonNull String val, String operator);

    /**
     * 根据 key 更新 val
     * @param key 被更新的 key
     * @param val 新的 val
     * @return 更新是否成功
     */
    Result<?> updateValue(@NonNull String key, @NonNull String val);

    /**
     * 校验配置值的有效性
     * @param type 配置的类型
     * @param val 配置的值
     * @return 校验是否通过
     */
    Result<?> checkValue(@NonNull SystemConfigType type, String val);

    /**
     * 读取一个配置，根据 type 转换为对应类型的值
     * @param type 配置的类型
     * @param val 配置的值
     * @return 转换后的值
     * @throws NumberFormatException 如果数字的格式错误
     * @throws UnsupportedOperationException 如果配置类型未知
     */
    Object convertValue(@NonNull SystemConfigType type, @NonNull String val);
}
