-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `cms_module`;
CREATE TABLE `cms_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '模块名称(非文件名)',
  `file_name` varchar(100) NOT NULL DEFAULT '' COMMENT '文件名',
  `type` int(3) NOT NULL DEFAULT '0' COMMENT '模块类型 自动/非自动',
  `status` int(3) NOT NULL DEFAULT '0' COMMENT '状态,默认正常',
  `is_refresh` int(1) NOT NULL DEFAULT '0' COMMENT '是否定时刷新 0-否',
  `refresh_time` date DEFAULT NULL COMMENT '定时刷新时间',
  `created_at` date NOT NULL COMMENT '创建时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `changed_at` date DEFAULT NULL COMMENT '修改时间',
  `changed_by` varchar(100) DEFAULT NULL COMMENT '修改人',
  `version` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;