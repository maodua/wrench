CREATE TABLE `system_config` (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `key` varchar(64) NOT NULL COMMENT '配置名',
     `value` text NOT NULL COMMENT '配置值',
     `type` char(32) NOT NULL COMMENT '配置类型',
     `remark` varchar(255) DEFAULT NULL COMMENT '配置的描述',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE KEY `unique_key` (`key`) USING BTREE
) COMMENT='系统配置';
