# 增加续约表 执行数据库：智慧短信商业化合作接口ope_opr
DROP TABLE IF EXISTS `t_open_api_pub_renewal_data`;
CREATE TABLE `t_open_api_pub_renewal_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `xy_code` varchar(50) NOT NULL,
  `biz_code` varchar(11) NOT NULL,
  `biz_start_time` datetime NOT NULL,
  `org_expiry_date` date NOT NULL,
  `new_expiry_date` date NOT NULL,
  `biz_result` varchar(11) NOT NULL,
  `biz_desc` varchar(255) DEFAULT NULL,
  `auditor` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `audit_date` datetime DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`xy_code`,`biz_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

# 增加品牌表 执行数据库：智慧短信商业化合作接口ope_opr
DROP TABLE IF EXISTS `t_open_api_brand_data`;
CREATE TABLE `t_open_api_brand_data` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(100) NOT NULL,
  `brand_code` varchar(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`brand_name`,`brand_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

# 公众号信息增加合作月字段
ALTER TABLE t_open_api_pub_info DROP `cooperative_month`;
ALTER TABLE t_open_api_pub_info ADD `cooperative_month` int(11) DEFAULT 12 COMMENT '合作月';

# 通道号信息增加是否公众号专属号码字段
ALTER TABLE t_open_api_num_info DROP `num_exclusive`;
ALTER TABLE t_open_api_num_info ADD `num_exclusive` varchar(11) DEFAULT 0 COMMENT '是否公众号专属号码';

# 菜单信息增加品牌代码字段
ALTER TABLE t_open_api_menu_info DROP `brand_code`;
ALTER TABLE t_open_api_menu_info ADD `brand_code` varchar(500) DEFAULT NULL COMMENT '品牌代码';

# 短信服务信息增加品牌代码字段
ALTER TABLE t_open_api_action_info DROP `brand_code`;
ALTER TABLE t_open_api_action_info ADD `brand_code` varchar(500) DEFAULT NULL COMMENT '品牌代码';

#公众号信息扩展表增加验收状态字段
ALTER TABLE t_open_pub_ext_info DROP `acceptance_status`;
ALTER TABLE t_open_pub_ext_info ADD `acceptance_status` varchar(11) DEFAULT 0 COMMENT '验收状态';


#公众号信息扩展表增加验收状态描述字段
ALTER TABLE t_open_pub_ext_info DROP `acceptance_desc`;
ALTER TABLE t_open_pub_ext_info ADD `acceptance_desc` varchar(500) DEFAULT NULL COMMENT '验收状态描述';