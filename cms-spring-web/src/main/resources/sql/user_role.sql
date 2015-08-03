-- ----------------------------
-- Table structure for `template`
-- ----------------------------
DROP TABLE IF EXISTS `cms_user_role`;
CREATE TABLE `cms_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `role_id` int(11) NOT NULL COMMENT '角色Id',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `changed_at` datetime DEFAULT NULL COMMENT '修改时间',
  `changed_by` varchar(100) DEFAULT NULL COMMENT '修改人',
  `version` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNI_ID` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;