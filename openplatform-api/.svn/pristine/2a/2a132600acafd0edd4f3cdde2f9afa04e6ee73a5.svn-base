# 增加行业表 执行数据库：智慧短信商业化合作接口ope_opr
DROP TABLE IF EXISTS  `t_open_industry_list`;
CREATE TABLE `t_open_industry_list` (
                                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                        `large_class` varchar(50) NOT NULL COMMENT '大类',
                                        `sub_class` varchar(100) NOT NULL COMMENT '小类',
                                        `industry_code` varchar(20) NOT NULL COMMENT '行业编码',
                                        `permission_yellow_report` int(11) NOT NULL DEFAULT 1 COMMENT '支持黄页接口上报：1 表示支持，0表示不支持',
                                        `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态',
                                        `created` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `updated` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
                                        `created_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                        `updated_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '修改人',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

# 公众号信息增加行业字段
ALTER TABLE t_open_api_pub_info ADD industry_code VARCHAR(20)  DEFAULT NULL COMMENT '行业';


