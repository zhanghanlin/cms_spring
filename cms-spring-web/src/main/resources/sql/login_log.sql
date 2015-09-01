-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `cms_login_log`;
CREATE TABLE `cms_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_account` varchar(100) NOT NULL COMMENT '登陆帐号',
  `login_name` varchar(100) NOT NULL COMMENT '登陆用户名',
  `ip` varchar(100) COMMENT '登陆IP',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `changed_at` datetime DEFAULT NULL COMMENT '修改时间',
  `changed_by` varchar(100) DEFAULT NULL COMMENT '修改人',
  `version` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;