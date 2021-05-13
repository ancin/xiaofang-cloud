-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.28-log - MySQL Community Server (GPL)
-- 服务器OS:                        Win64
-- HeidiSQL 版本:                  10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for xiaofang
CREATE DATABASE IF NOT EXISTS `xiaofang` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `xiaofang`;

-- Dumping structure for table xiaofang.t_developer
CREATE TABLE IF NOT EXISTS `t_developer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `app_id` varchar(32) DEFAULT NULL,
  `app_secret` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `mobile` varchar(12) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` char(1) DEFAULT 'N' COMMENT 'N正常,Y删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开发者表';

-- Data exporting was unselected.

-- Dumping structure for table xiaofang.t_device
CREATE TABLE IF NOT EXISTS `t_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '设备序列号',
  `device_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `device_type` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '设备类型1烟感2明火3有毒气体4监控5其他',
  `device_model` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '设备型号',
  `device_status` char(2) COLLATE utf8_bin DEFAULT NULL COMMENT '设备状态0离线1在线',
  `user_id` bigint(20) DEFAULT NULL COMMENT '归属用户',
  `region` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '地区',
  `clazz` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '分类',
  `industry` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '行业分类',
  `company_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '属于哪个公司',
  `creator` bigint(20) DEFAULT NULL,
  `modifier` bigint(20) DEFAULT NULL,
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` char(1) COLLATE utf8_bin DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备表';

-- Data exporting was unselected.

-- Dumping structure for table xiaofang.t_menu
CREATE TABLE IF NOT EXISTS `t_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `icon` varchar(50) DEFAULT NULL COMMENT '图片',
  `url` varchar(100) DEFAULT NULL COMMENT 'url',
  `name` varchar(100) DEFAULT NULL,
  `hidden` tinyint(4) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单id',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_deleted` char(1) DEFAULT 'N' COMMENT 'N正常,Y删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- Data exporting was unselected.

-- Dumping structure for table xiaofang.t_role
CREATE TABLE IF NOT EXISTS `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` char(1) DEFAULT 'N' COMMENT 'N正常,Y删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- Data exporting was unselected.

-- Dumping structure for table xiaofang.t_setting
CREATE TABLE IF NOT EXISTS `t_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `device_model` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '设备类型',
  `device_no` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '设备序列号',
  `device_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '设备名称',
  `setting_content` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '设置详情 JSON格式',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `company_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '公司名',
  `modifier` bigint(20) DEFAULT NULL,
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` char(1) COLLATE utf8_bin DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户设置表格';

-- Data exporting was unselected.

-- Dumping structure for table xiaofang.t_user
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `head_img` varchar(255) DEFAULT NULL COMMENT '头像',
  `login_name` varchar(30) DEFAULT NULL COMMENT '登录名称',
  `login_password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `wx_id` varchar(50) DEFAULT NULL COMMENT '微信id',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `focus_count` int(11) DEFAULT NULL COMMENT '关注数量',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `finish_auth` tinyint(4) DEFAULT NULL COMMENT '是否完成实名认证（1是，0否）',
  `introduce` varchar(255) DEFAULT NULL COMMENT '个人简介',
  `level` int(11) DEFAULT NULL COMMENT '用户等级',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `role` varchar(16) DEFAULT NULL,
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `is_deleted` char(1) DEFAULT 'N' COMMENT 'N正常,Y删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Data exporting was unselected.

-- Dumping structure for table xiaofang.t_user_auth
CREATE TABLE IF NOT EXISTS `t_user_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `idcard_front` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `idcard_reverse` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `auth_type` tinyint(4) DEFAULT NULL COMMENT '0-认证中 1-认证完成 2-认证失败',
  `creator` varchar(32) DEFAULT NULL COMMENT '创建人',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` char(1) DEFAULT 'N' COMMENT 'N正常,Y删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户认证表';

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
