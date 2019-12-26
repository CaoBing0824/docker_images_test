/*
SQLyog Job Agent v12.09 (64 bit) Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.6.32-log : Database - ope_opr
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `ope_opr`;

/*Table structure for table `t_open_agent_scene` */

CREATE TABLE `t_open_agent_scene` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `xy_agent_code` varchar(50) NOT NULL COMMENT '代理商编码',
  `scene_code` varchar(20) NOT NULL COMMENT '情景id',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `idxs` (`scene_code`,`xy_agent_code`,`created`,`updated`,`created_by`,`updated_by`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_action_info` */

CREATE TABLE `t_open_api_action_info` (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `xy_pub_code` varchar(50) NOT NULL COMMENT '公众号编码',
  `scene_code` varchar(50) NOT NULL COMMENT '情景编码',
  `btn_name` varchar(50) NOT NULL COMMENT '按钮名称',
  `action_type` varchar(20) NOT NULL COMMENT '动作类型',
  `priority` int(11) NOT NULL DEFAULT '1' COMMENT '优先级',
  `action_type_val` varchar(2048) NOT NULL COMMENT '动作类型值',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '数据生效开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '数据生效结束时间',
  `ext_map` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展参数,保留字段',
  `version` varchar(50) NOT NULL COMMENT '数据版本，数据生成时间',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`version`,`xy_code`),
  KEY `btn_name` (`btn_name`,`action_type`,`start_time`,`end_time`,`status`,`created`,`updated`,`created_by`,`updated_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_agent_ext_info` */

CREATE TABLE `t_open_api_agent_ext_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `agent_name` varchar(50) NOT NULL COMMENT '代理商名称',
  `xy_code` varchar(50) NOT NULL COMMENT '代理商编码',
  `app_id` varchar(50) NOT NULL COMMENT 'appId',
  `app_secrect` varchar(50) NOT NULL COMMENT '应用随机密钥(随机32位)',
  `check_status_notice_callback_url` varchar(200) NOT NULL COMMENT '审核结果回调通知URl',
  `white_ips` varchar(1000) NOT NULL COMMENT '服务器白名单IP',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`xy_code`),
  KEY `idx` (`created`,`updated`,`created_by`,`updated_by`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_data_relation_edit` */

CREATE TABLE `t_open_api_data_relation_edit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `version` varchar(20) NOT NULL COMMENT '数据版本',
  `data_type` varchar(50) NOT NULL COMMENT '数据类型（企业信息、黄页等等）',
  `workorder_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '工单ID',
  `check_status` int(11) NOT NULL DEFAULT '1' COMMENT '审核状态',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`xy_code`),
  KEY `idxs` (`data_type`,`workorder_id`,`status`,`created`,`updated`,`created_by`,`updated_by`)
) ENGINE=InnoDB AUTO_INCREMENT=1085139225077665794 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_data_relation_history` */

CREATE TABLE `t_open_api_data_relation_history` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `version` varchar(20) NOT NULL COMMENT '数据版本',
  `data_type` varchar(50) NOT NULL COMMENT '数据类型（企业信息、黄页等等）',
  `workorder_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '工单ID',
  `check_status` int(11) NOT NULL DEFAULT '1' COMMENT '审核状态',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  KEY `idxs` (`data_type`,`workorder_id`,`status`,`created`,`updated`,`created_by`,`updated_by`,`xy_code`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_data_relation_release` */

CREATE TABLE `t_open_api_data_relation_release` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `version` varchar(50) NOT NULL COMMENT '数据版本',
  `data_type` varchar(50) NOT NULL COMMENT '数据类型（企业信息、黄页等等）',
  `workorder_id` bigint(20) NOT NULL DEFAULT '-1' COMMENT '工单ID',
  `check_status` int(11) NOT NULL DEFAULT '1' COMMENT '审核状态',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`xy_code`),
  KEY `idxs` (`data_type`,`workorder_id`,`status`,`created`,`updated`,`created_by`,`updated_by`)
) ENGINE=InnoDB AUTO_INCREMENT=1080764677813084173 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_ent_info` */

CREATE TABLE `t_open_api_ent_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `xy_agent_code` varchar(50) NOT NULL COMMENT '代理商编码',
  `ent_name` varchar(100) NOT NULL COMMENT '企业名称',
  `credit_code` varchar(18) NOT NULL COMMENT '统一社会编码',
  `auth_docx` varchar(1000) NOT NULL DEFAULT '' COMMENT '企业认证授权书资源地址',
  `ext_map` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展参数,保留字段',
  `version` varchar(50) NOT NULL COMMENT '数据版本，数据生成时间戳',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`version`,`xy_code`),
  KEY `idxs` (`ent_name`,`xy_agent_code`,`created`,`updated`,`created_by`,`updated_by`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1085131702941626370 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_menu_detail_info` */

CREATE TABLE `t_open_api_menu_detail_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `item_code` varchar(12) NOT NULL COMMENT '菜单项编码',
  `item_name` varchar(150) NOT NULL COMMENT '菜单项名称',
  `item_desc` varchar(300) NOT NULL DEFAULT '' COMMENT '菜单项描述',
  `item_type` varchar(150) NOT NULL COMMENT '菜单项类型',
  `item_type_val` text NOT NULL COMMENT '菜单项类型值',
  `xy_code` varchar(50) NOT NULL COMMENT '菜单编码',
  `version` varchar(50) NOT NULL COMMENT '版本号',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `xy_code` (`xy_code`,`version`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1085139225102831618 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_menu_info` */

CREATE TABLE `t_open_api_menu_info` (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `xy_pub_code` varchar(50) NOT NULL COMMENT '公众号编码',
  `ext_map` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展参数,保留字段',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '数据生效开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '数据生效结束时间',
  `version` varchar(50) NOT NULL COMMENT '数据版本，数据生成时间',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`version`,`xy_code`),
  KEY `idxs` (`xy_pub_code`,`start_time`,`end_time`,`created`,`updated`,`created_by`,`updated_by`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_menu_relation_info` */

CREATE TABLE `t_open_api_menu_relation_info` (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `xy_agent_code` varchar(50) NOT NULL COMMENT '代理商编码',
  `xy_pub_code` varchar(50) NOT NULL COMMENT '公众号编码',
  `xy_num_code` varchar(50) DEFAULT NULL COMMENT '号码编码',
  `xy_menu_code` varchar(50) NOT NULL COMMENT '菜单编码',
  `ag_phoneId` varchar(50) DEFAULT NULL COMMENT '代理商用户标识',
  `areas_relation_id` varchar(200) NOT NULL COMMENT '区域',
  `ext_map` varchar(500) DEFAULT '' COMMENT '扩展参数,保留字段',
  `version` varchar(50) NOT NULL COMMENT '数据版本，数据生成时间',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`version`,`xy_code`),
  KEY `idxs` (`xy_pub_code`,`xy_num_code`,`xy_menu_code`,`areas_relation_id`,`status`,`created`,`updated`,`created_by`,`updated_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_muilt_data` */

CREATE TABLE `t_open_api_muilt_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `relation_id` bigint(20) NOT NULL COMMENT '数据关联Id',
  `value` varchar(100) NOT NULL COMMENT '值',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx` (`relation_id`,`value`)
) ENGINE=InnoDB AUTO_INCREMENT=1085100973822115859 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_num_info` */

CREATE TABLE `t_open_api_num_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `xy_pub_code` varchar(50) NOT NULL COMMENT '公众号编码',
  `receive_num` varchar(50) NOT NULL COMMENT '接入码',
  `areas_relation_id` bigint(20) NOT NULL COMMENT '区域关联ID',
  `signatures_relation_id` bigint(20) NOT NULL COMMENT '签名关联ID',
  `min_len` int(11) NOT NULL DEFAULT '0' COMMENT '最小长度(用于标识号段所包含位数，若是固定位数，则最小值=最大值)',
  `max_len` int(11) NOT NULL DEFAULT '0' COMMENT '最大长度',
  `purpose` varchar(200) NOT NULL DEFAULT '' COMMENT '用途',
  `ext_map` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展参数,保留字段',
  `version` varchar(50) NOT NULL COMMENT '数据版本，数据生成时间戳',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`version`,`xy_code`),
  KEY `idxs` (`xy_pub_code`,`receive_num`,`areas_relation_id`,`signatures_relation_id`,`purpose`,`created`,`updated`,`created_by`,`updated_by`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1085100973830504451 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_pub_info` */

CREATE TABLE `t_open_api_pub_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `xy_ent_code` varchar(50) NOT NULL COMMENT '企业编码',
  `pub_name` varchar(50) NOT NULL COMMENT '公众号名称',
  `show_logo_white` varchar(200) NOT NULL DEFAULT '' COMMENT '白底logo图片的资源地址',
  `show_logo_color` varchar(200) NOT NULL DEFAULT '' COMMENT '彩底logo图片的资源地址',
  `ext_map` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展参数,保留字段',
  `product_type` varchar(50) NOT NULL COMMENT '产品类型',
  `version` varchar(50) NOT NULL COMMENT '数据版本，数据生成时间戳',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`version`,`xy_code`),
  KEY `idxs` (`xy_ent_code`,`pub_name`,`created`,`updated`,`created_by`,`updated_by`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1085131704569016322 DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_sms_info` */

CREATE TABLE `t_open_api_sms_info` (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `xy_pub_code` varchar(50) NOT NULL COMMENT '公众号编码',
  `scene_code` varchar(50) NOT NULL COMMENT '情景编码',
  `sms_content` varchar(1000) NOT NULL COMMENT '短信内容',
  `sms_template` varchar(1000) NOT NULL COMMENT '短信模板',
  `parse_content_relation_id` bigint(20) NOT NULL COMMENT '提取模板',
  `ext_map` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展参数,保留字段',
  `version` varchar(50) NOT NULL COMMENT '数据版本，数据生成时间',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `version` (`version`,`xy_code`),
  KEY `xy_pub_code` (`xy_pub_code`,`status`,`created`,`updated`,`created_by`,`updated_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_api_white_info` */

CREATE TABLE `t_open_api_white_info` (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `xy_agent_code` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '代理商编码',
  `phone_brand` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '手机品牌',
  `phone_type` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '手机型号',
  `xy_pub_codes_relation_id` bigint(20) NOT NULL COMMENT '公众号, 多个英文分号 ; 隔开',
  `imeis_relation_id` bigint(20) NOT NULL COMMENT 'IMEI/SN等，多个英文分号 ; 隔开',
  `ext_map` varchar(500) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '扩展参数,保留字段',
  `version` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '数据版本，数据生成时间',
  `xy_code` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`version`,`xy_code`),
  KEY `idxs` (`xy_agent_code`,`phone_brand`,`phone_type`,`status`,`created`,`updated`,`created_by`,`updated_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

/*Table structure for table `t_open_api_yellow_info` */

CREATE TABLE `t_open_api_yellow_info` (
  `id` bigint(20) NOT NULL COMMENT '自增id',
  `xy_pub_code` varchar(50) NOT NULL COMMENT '公众号编码',
  `slogan` varchar(100) NOT NULL COMMENT '企业口号',
  `introduction` varchar(1000) NOT NULL COMMENT '企业介绍',
  `tell_phone` varchar(20) NOT NULL COMMENT '服务热线',
  `address` varchar(100) NOT NULL COMMENT '企业地址',
  `serch_key` varchar(200) NOT NULL DEFAULT '' COMMENT '企业搜索关键词',
  `range` varchar(1000) NOT NULL COMMENT '经营范围',
  `open_time` varchar(100) NOT NULL COMMENT '营业时间',
  `lonlat` varchar(100) NOT NULL COMMENT '经纬度',
  `ext_map` varchar(500) NOT NULL DEFAULT '' COMMENT '扩展参数,保留字段',
  `version` varchar(50) NOT NULL COMMENT '数据版本，数据生成时间',
  `xy_code` varchar(50) NOT NULL COMMENT '业务编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_code` (`version`,`xy_code`),
  KEY `idxs` (`xy_pub_code`,`status`,`created`,`updated`,`created_by`,`updated_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_open_scene_list` */

CREATE TABLE `t_open_scene_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `scene_name` varchar(50) NOT NULL COMMENT '情景名称',
  `scene_code` varchar(20) NOT NULL COMMENT '情景编码',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `created_by` varchar(50) NOT NULL DEFAULT '' COMMENT '创建人',
  `updated_by` varchar(50) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx` (`scene_code`),
  KEY `idxs` (`scene_name`,`status`,`created`,`updated`,`created_by`,`updated_by`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/* Trigger structure for table `t_open_api_data_relation_edit` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'%' */ /*!50003 TRIGGER `tri_data_relation_edit_insert` AFTER INSERT ON `t_open_api_data_relation_edit` FOR EACH ROW BEGIN
	 INSERT INTO t_open_api_data_relation_history(id,xy_code,`version`,`data_type`,created_by,updated_by)
	 VALUES(new.id,new.xy_code,new.version,new.data_type,new.created_by,new.updated_by);
    END */$$


DELIMITER ;

/* Trigger structure for table `t_open_api_data_relation_edit` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'%' */ /*!50003 TRIGGER `tri_data_relation_edit_update` AFTER UPDATE ON `t_open_api_data_relation_edit` FOR EACH ROW BEGIN
	 INSERT INTO t_open_api_data_relation_history(id,xy_code,`version`,`data_type`,workorder_id,check_status,`status`,created,updated,created_by,updated_by)
	 VALUES(new.id,new.xy_code,new.version,new.data_type,new.workorder_id,new.check_status,new.status,new.created,new.updated,new.created_by,new.updated_by);
    END */$$


DELIMITER ;

/* Trigger structure for table `t_open_api_data_relation_edit` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'%' */ /*!50003 TRIGGER `tri_data_relation_edit_delete` BEFORE DELETE ON `t_open_api_data_relation_edit` FOR EACH ROW BEGIN
	 INSERT INTO t_open_api_data_relation_history(id,xy_code,`version`,`data_type`,workorder_id,check_status,`status`,created,created_by,updated_by)
	 VALUES(old.id,old.xy_code,old.version,old.data_type,old.workorder_id,old.check_status,0,old.created,old.created_by,old.updated_by);
    END */$$


DELIMITER ;

/* Trigger structure for table `t_open_api_data_relation_release` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'%' */ /*!50003 TRIGGER `tri_data_relation_release_insert` AFTER INSERT ON `t_open_api_data_relation_release` FOR EACH ROW BEGIN
	 INSERT INTO t_open_api_data_relation_history(id,xy_code,`version`,`data_type`,workorder_id,check_status,`status`,created,updated,created_by,updated_by)
	 VALUES(new.id,new.xy_code,new.version,new.data_type,new.workorder_id,new.check_status,new.status,new.created,new.updated,new.created_by,new.updated_by);
    END */$$


DELIMITER ;

/* Trigger structure for table `t_open_api_data_relation_release` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'%' */ /*!50003 TRIGGER `tri_data_relation_release_update` AFTER UPDATE ON `t_open_api_data_relation_release` FOR EACH ROW BEGIN
	 INSERT INTO t_open_api_data_relation_history(id,xy_code,`version`,`data_type`,workorder_id,check_status,`status`,created,updated,created_by,updated_by)
	 VALUES(new.id,new.xy_code,new.version,new.data_type,new.workorder_id,new.check_status,new.status,new.created,new.updated,new.created_by,new.updated_by);
    END */$$


DELIMITER ;

/* Trigger structure for table `t_open_api_data_relation_release` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'%' */ /*!50003 TRIGGER `tri_data_relation_release_delete` BEFORE DELETE ON `t_open_api_data_relation_release` FOR EACH ROW BEGIN
	 INSERT INTO t_open_api_data_relation_history(id,xy_code,`version`,`data_type`,workorder_id,check_status,`status`,created,created_by,updated_by)
	 VALUES(old.id,old.xy_code,old.version,old.data_type,old.workorder_id,old.check_status,0,old.created,old.created_by,old.updated_by);
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
