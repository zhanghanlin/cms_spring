-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `cms_menu`;
CREATE TABLE `cms_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '权限名',
  `note` varchar(200) COMMENT '权限说明',
  `code` varchar(200) COMMENT '权限结构码',
  `parent_code` varchar(200) COMMENT '上一级权限',
  `status` int(3) NOT NULL DEFAULT '0' COMMENT '状态,默认正常',
  `created_at` date NOT NULL COMMENT '创建时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `changed_at` date DEFAULT NULL COMMENT '修改时间',
  `changed_by` varchar(100) DEFAULT NULL COMMENT '修改人',
  `version` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE,
  UNIQUE KEY `UNI_PNAME` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;