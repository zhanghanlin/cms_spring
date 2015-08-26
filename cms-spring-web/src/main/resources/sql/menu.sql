-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '菜单名',
  `note` varchar(200) COMMENT '菜单说明',
  `code` varchar(200) COMMENT '菜单结构码',
  `parent_id` int(11) DEFAULT 0 COMMENT '上一级菜单Id',
  `link` varchar(200) NOT NULL COMMENT '菜单连接地址',
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
  UNIQUE KEY `UNI_PNAME` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO cms_menu ( NAME, note, CODE, parent_code, link, icon, weight, created_at, created_by ) VALUES ( '系统管理', '系统管理', '001', '0', '###', 'fa-list', 1, now(), 'System' );
INSERT INTO cms_menu ( NAME, note, CODE, parent_code, link, icon, weight, created_at, created_by ) VALUES ( '菜单管理', '菜单管理', '001001', '001', '/menu/toList', 'fa-circle-o', 1, now(), 'System' ); 