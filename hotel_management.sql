/*
Navicat MySQL Data Transfer

Source Server         : jfjg
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : hotel_management

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-10-18 15:27:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for check
-- ----------------------------
DROP TABLE IF EXISTS `check`;
CREATE TABLE `check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reserve_id` int(11) DEFAULT NULL COMMENT '预订单号id',
  `check_id_num` varchar(32) NOT NULL COMMENT '身份证号',
  `check_phone_num` varchar(32) NOT NULL COMMENT '手机号',
  `check_name` varchar(32) NOT NULL COMMENT '真实姓名',
  `check_sex` varchar(4) NOT NULL COMMENT '性别',
  `check_in_time` datetime NOT NULL COMMENT '入住时间',
  `check_out_time` datetime NOT NULL COMMENT '退房时间',
  `check_room_id` varchar(32) NOT NULL COMMENT '入住房间号',
  `check_day` int(8) NOT NULL COMMENT '实际入住天数',
  `check_price` decimal(10,2) NOT NULL COMMENT '实际金额',
  `check_status` varchar(32) NOT NULL COMMENT '结账状态',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `check_id_num` (`check_id_num`) USING BTREE,
  KEY `fk_check_reserve` (`reserve_id`) USING BTREE,
  CONSTRAINT `fk_check_reserve` FOREIGN KEY (`reserve_id`) REFERENCES `reserve` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of check
-- ----------------------------

-- ----------------------------
-- Table structure for reserve
-- ----------------------------
DROP TABLE IF EXISTS `reserve`;
CREATE TABLE `reserve` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `room_type` varchar(32) NOT NULL COMMENT '房间类型',
  `room_price` decimal(10,2) NOT NULL COMMENT '房间价格',
  `reserve_in_time` datetime NOT NULL COMMENT '预定入住时间',
  `reserve_out_time` datetime NOT NULL COMMENT '预定退房时间',
  `reserve_now_time` datetime NOT NULL COMMENT '预定时的时间',
  `reserve_money` decimal(10,0) NOT NULL COMMENT '押金',
  `reserve_status` varchar(16) NOT NULL COMMENT '预订单状态',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `fk_reserve_user` (`user_id`),
  CONSTRAINT `fk_reserve_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reserve
-- ----------------------------

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(9) NOT NULL,
  `room_code` varchar(32) NOT NULL COMMENT '房间号',
  `room_type` varchar(64) NOT NULL COMMENT '房间类型',
  `room_status` varchar(32) NOT NULL COMMENT '房间状态',
  `room_price` decimal(10,2) NOT NULL COMMENT '房间价格',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `sys_num` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `true_name` varchar(64) NOT NULL COMMENT '真实名字',
  `permission` int(4) NOT NULL DEFAULT '1' COMMENT '权限',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_num` (`sys_num`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_num` varchar(32) NOT NULL COMMENT '手机号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `true_name` varchar(32) NOT NULL COMMENT '真实姓名',
  `id_num` varchar(64) NOT NULL COMMENT '身份证号',
  `permission` int(4) NOT NULL DEFAULT '0' COMMENT '权限',
  `sex` varchar(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_num` (`phone_num`) USING BTREE,
  UNIQUE KEY `id_num` (`id_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '110', '123456', '张三', '354024110', '0', '男');
