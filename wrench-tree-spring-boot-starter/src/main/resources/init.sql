CREATE TABLE `obj_in_tree` (
   `id` char(20) NOT NULL,
   `ancestor_id` char(20) NOT NULL COMMENT '父级或祖父级id',
   `obj_id` char(20) NOT NULL COMMENT '实体id',
   `lvl` int(11) NOT NULL COMMENT 'oid对于ancestor_id中的级别',
   `parent_id` char(20) NOT NULL COMMENT '父级ID',
   `type` varchar(32) NOT NULL COMMENT '树类型',
   `add_time` datetime NOT NULL COMMENT '添加时间',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='对象关系树';

-- 添加根记录
INSERT INTO `water`.`obj_in_tree`(`id`, `ancestor_id`, `obj_id`, `lvl`, `parent_id`, `type`, `add_time`) VALUES ('1000000000000000002', '2', '2', 0, '0', 'AREA', '2020-01-01 00:00:00');
