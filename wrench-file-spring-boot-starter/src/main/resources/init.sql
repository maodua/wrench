CREATE TABLE `file_pool` (
    `id` char(20) NOT NULL,
    `oid` char(20) DEFAULT NULL COMMENT '可以用户id,或者其他类型id',
    `local` bit(1) NOT NULL COMMENT '是否存在本地',
    `filename` varchar(255) NOT NULL COMMENT '文件的原始名称，只包括名称不包括路径',
    `filetype` varchar(50) NOT NULL COMMENT '文件类型',
    `filepath` varchar(255) NOT NULL COMMENT '文件的实际保存路径',
    `addtime` datetime NOT NULL COMMENT '文件的上传时间',
    `size` bigint(20) NOT NULL COMMENT '文件的大小',
    PRIMARY KEY (`id`) USING BTREE
) COMMENT='文件池';
