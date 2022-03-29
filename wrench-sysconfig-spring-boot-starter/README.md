# 🔧 系统配置可以更简单

## 安装
Gradle:
```groovy
dependencies {
    compile "io.github.maodua:wrench-sysconfig-spring-boot-starter:最新版本号"
}
```
Maven:
```xml
<dependency>
    <groupId>io.github.maodua</groupId>
    <artifactId>wrench-sysconfig-spring-boot-starter</artifactId>
    <version>最新版本号</version>
</dependency>
```

## 添加表结构
```sql
CREATE TABLE `system_config` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `key` varchar(64) NOT NULL COMMENT '配置名',
     `value` text NOT NULL COMMENT '配置值',
     `type` char(32) NOT NULL COMMENT '配置类型',
     `remark` varchar(255) DEFAULT NULL COMMENT '配置的描述',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE KEY `unique_key` (`key`) USING BTREE
) COMMENT='系统配置';

INSERT INTO `wrench`.`system_config`(`id`, `key`, `value`, `type`, `remark`) VALUES (100001, 'test', 'test2222', 'STRING', NULL);
```
## 示例代码
### 获取数据
```java
// 获取String类型数据
SysConfig.getString("key");
// 获取Boolean类型数据
SysConfig.getBoolean("key");
// 获取Long类型数据
SysConfig.getLong("key");
// 获取Double类型数据
SysConfig.getDouble("key");
// 获取BigInteger类型数据
SysConfig.getInteger("key");
// 获取BigDecimal类型数据
SysConfig.getDecimal("key");
// 获取百分比数类型数据
SysConfig.getPercentage("key");
// 获取LocalDateTime类型数据
SysConfig.getDate("key");
```
### 修改数据
```java
@Autowired
private ISystemConfigService systemConfigService;

systemConfigService.updateValue("key", "val", "操作人");
```
