package io.github.maodua.wrench.sysconfig.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.maodua.wrench.common.vo.result.Result;
import io.github.maodua.wrench.sysconfig.entity.SystemConfig;
import io.github.maodua.wrench.sysconfig.entity.SystemConfigType;
import io.github.maodua.wrench.sysconfig.mapper.SystemConfigMapper;
import io.github.maodua.wrench.sysconfig.util.SysConfig;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

/**
 * 系统配置 服务实现类
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
@Slf4j
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements ISystemConfigService {
    @Override
    public Optional<SystemConfig> getByKey(@NonNull String key) {
        var sysConfig = this.lambdaQuery()
                .eq(SystemConfig::getKey, key)
                .one();
        return Optional.ofNullable(sysConfig);
    }

    @Override
    public Result<?> updateValue(@NonNull String key, @NonNull String val, String operator) {
        // 查出配置项
        var optSystemConfig = this.getByKey(key);
        if (optSystemConfig.isEmpty()) {
            return Result.fail("配置项不存在");
        }
        var systemConfig = optSystemConfig.get();

        // 参数有效性检查
        var checkValueResult = this.checkValue(systemConfig.getType(), val);
        if (!checkValueResult.isSuccess()) {
            return checkValueResult;
        }

        // 如果值没变则不要更新到数据库中
        if (Objects.equals(val, systemConfig.getValue())) {
            return Result.success();
        }

        // 输出日志
        log.info("修改系统配置：{} 将 {} 配置从 {} 修改为 {}", operator, key, systemConfig.getValue(), val);

        // 更新值到数据库中
        systemConfig.setValue(val);
        this.updateById(systemConfig);

        // 通知 SysConfig 工具类需要刷新配置
        SysConfig.refresh();

        // 返回更新成功
        return Result.success();
    }

    @Override
    public Result<?> updateValue(String key, String val) {
        return this.updateValue(key, val, "");
    }

    @Override
    public Result<?> checkValue(@NonNull SystemConfigType type, String val) {
        if (Objects.isNull(val)) {
            return Result.fail("新值不能为 null");
        }

        try {
            switch (type) {
                case STRING:
                    break;
                case BOOLEAN:
                    // Boolean.parseBoolean 不靠谱，只判断不为 "true" 的就返回 false，根本没判断是否为 "false"
                    if (!("true".equalsIgnoreCase(val) || "false".equalsIgnoreCase(val))) {
                        return this.fail(type, val);
                    }
                    break;
                case LONG:
                    // 检查是否会丢精度
                    if (!val.equals(String.valueOf(Long.parseLong(val)))) {
                        return this.fail(type, val);
                    }
                    break;
                case DOUBLE:
                    // 由于 double 的不精准性，只检查其格式是否正确
                    Double.parseDouble(val);
                    break;
                case INTEGER:
                    // 能 new 成功就算过
                    new BigInteger(val);
                    break;
                case DECIMAL:
                case PERCENTAGE:
                    // 能 new 成功就算过
                    new BigDecimal(val);
                    break;
                case DATE:
                    // 能 parse 成功就过
                    LocalDateTime.parse(val);
                    break;
                default:
                    return Result.fail(String.format("未知的配置类型: %s", type));
            }
        } catch (NumberFormatException e) {
            // LONG、DOUBLE、INTEGER、DECIMAL、PERCENTAGE 类型抛出的异常
            log.error("", e);
            return this.fail(type, val, "数字格式错误");
        } catch (DateTimeParseException e) {
            // DATE 类型抛出的异常
            log.error("", e);
            return this.fail(type, val, "时间格式不正确");
        }

        // 返回校验成功
        return Result.success();
    }

    private Result<?> fail(SystemConfigType type, String value) {
        return Result.fail(String.format("%s 类型的值 %s 无效", type.name(), value));
    }

    private Result<?> fail(SystemConfigType type, String value, String remark) {
        return Result.fail(String.format("%s 类型的值 %s 无效：%s", type.name(), value, remark));
    }

    @Override
    public Object convertValue(@NonNull SystemConfigType type, @NonNull String val) {
        // 转换类型并返回
        switch (type) {
            case STRING:
                return val;
            case BOOLEAN:
                return Boolean.valueOf(val);
            case LONG:
                return Long.valueOf(val);
            case DOUBLE:
                return Double.valueOf(val);
            case INTEGER:
                return new BigInteger(val);
            case DECIMAL:
            case PERCENTAGE:
                return new BigDecimal(val);
            case DATE:
                return LocalDateTime.parse(val);
            default:
                throw new UnsupportedOperationException("未知的配置类型");
        }
    }
}
