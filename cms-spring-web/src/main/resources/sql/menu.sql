-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '菜单名',
  `note` varchar(200) COMMENT '菜单说明 - 如果是按钮,则代表html信息',
  `code` varchar(200) COMMENT '菜单结构码',
  `unique_key` varchar(200) COMMENT '菜单唯一key',
  `parent_id` int(11) DEFAULT 0 COMMENT '上一级菜单Id',
  `link` varchar(200) NOT NULL COMMENT '菜单连接地址',
  `type` int(3) NOT NULL DEFAULT 0 COMMENT '菜单类型,0-菜单,1-按钮',
  `icon` varchar(200) COMMENT '菜单图标样式',
  `weight` int(3) COMMENT '权重,排序使用',
  `status` int(3) NOT NULL DEFAULT '0' COMMENT '状态,默认正常',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `changed_at` datetime DEFAULT NULL COMMENT '修改时间',
  `changed_by` varchar(100) DEFAULT NULL COMMENT '修改人',
  `version` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_CODE` (`code`) USING BTREE,
  UNIQUE KEY `UNI_KEY` (`unique_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_menu
-- ----------------------------
INSERT INTO `cms_menu` (name,note,code,link,icon,weight,status,created_at,created_by,unique_key) VALUES ('系统监控管理', '系统监控管理', '002', '###', 'fa-list', '0', '0', now(), 'System', 'sys_monitor');
INSERT INTO `cms_menu` (name,note,code,link,icon,weight,status,created_at,created_by,unique_key) VALUES ('系统基础信息', '系统基础信息', '001', '###', 'fa-list', '0', '0', now(), 'System', 'system');
INSERT INTO `cms_menu` (name,note,code,link,icon,weight,status,created_at,created_by,unique_key) VALUES ('菜单管理', '菜单管理', '001001', '/menu/toList', 'fa-circle-o', '0', '0', now(), 'System', 'system:menu');