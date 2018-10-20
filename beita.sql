/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : beita

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-20 23:55:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admission_ticket_info
-- ----------------------------
DROP TABLE IF EXISTS `admission_ticket_info`;
CREATE TABLE `admission_ticket_info` (
  `ticket_info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试信息id',
  `userinfo_id` int(11) NOT NULL COMMENT '用户账号表关键字',
  `ticket_info_name` varchar(255) NOT NULL COMMENT '准考证id(需要一个生成算法)',
  `ticket_info_identifier` int(18) NOT NULL,
  `ticket_info_time` datetime NOT NULL COMMENT '考试开始时间',
  `ticket_info_duration` int(11) NOT NULL COMMENT '考试时间长度',
  `ticket_info_seatnum` int(3) NOT NULL COMMENT '考场座位号',
  `ticket_info_school` varchar(255) NOT NULL COMMENT '考试学校',
  PRIMARY KEY (`ticket_info_id`),
  KEY `userinfo_id2` (`userinfo_id`),
  KEY `ticket_info_identifier` (`ticket_info_identifier`),
  CONSTRAINT `userinfo_id2` FOREIGN KEY (`userinfo_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for enter_personnel
-- ----------------------------
DROP TABLE IF EXISTS `enter_personnel`;
CREATE TABLE `enter_personnel` (
  `enter_p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试录入表id',
  `exam_type_id` int(11) NOT NULL COMMENT '考试类别表id',
  `userinfo_id` int(11) NOT NULL COMMENT '被派发人员 id',
  `enter_is_check` tinyint(4) NOT NULL COMMENT '是否已经审核',
  PRIMARY KEY (`enter_p_id`),
  KEY `exam_type_id3` (`exam_type_id`),
  KEY `userinfo_id3` (`userinfo_id`),
  CONSTRAINT `exam_type_id3` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userinfo_id3` FOREIGN KEY (`userinfo_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_news
-- ----------------------------
DROP TABLE IF EXISTS `exam_news`;
CREATE TABLE `exam_news` (
  `news_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试新闻id',
  `etype_id` int(11) NOT NULL COMMENT '考试新闻类别表id',
  `news_content` text NOT NULL COMMENT '考试新闻内容（存储纯html）',
  `news_time` datetime NOT NULL COMMENT '考试新闻发布时间',
  `news_isnew` tinyint(4) NOT NULL COMMENT '是否为最新新闻',
  `news_visits` int(11) NOT NULL DEFAULT '0' COMMENT '考试新闻浏览次数，默认值为0',
  PRIMARY KEY (`news_id`),
  KEY `etype_id` (`etype_id`),
  CONSTRAINT `etype_id` FOREIGN KEY (`etype_id`) REFERENCES `exam_news_type` (`etype_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_news_type
-- ----------------------------
DROP TABLE IF EXISTS `exam_news_type`;
CREATE TABLE `exam_news_type` (
  `etype_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试新闻类别表id',
  `etype_name` varchar(255) NOT NULL COMMENT '考试新闻类别名称',
  PRIMARY KEY (`etype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_score`;
CREATE TABLE `exam_score` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试成绩表id',
  `exam_type_id` int(11) NOT NULL COMMENT '考试类别表id',
  `ticket_info_identifier` int(18) NOT NULL COMMENT '准考证id',
  `score_num` varchar(11) NOT NULL COMMENT '成绩',
  PRIMARY KEY (`score_id`),
  KEY `exam_type_id4` (`exam_type_id`),
  KEY `ticket_info_identifier` (`ticket_info_identifier`),
  CONSTRAINT `exam_type_id4` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_info_identifier` FOREIGN KEY (`ticket_info_identifier`) REFERENCES `admission_ticket_info` (`ticket_info_identifier`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_session
-- ----------------------------
DROP TABLE IF EXISTS `exam_session`;
CREATE TABLE `exam_session` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试场次id',
  `exam_type_id` int(11) NOT NULL COMMENT '考试类别表id',
  `session_place` varchar(255) NOT NULL COMMENT '考试场次地点',
  `session_time` datetime NOT NULL COMMENT '考试时间',
  `session_capacity` int(11) NOT NULL COMMENT '考场容量',
  PRIMARY KEY (`session_id`),
  KEY `exam_type_id` (`exam_type_id`),
  CONSTRAINT `exam_type_id` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_signup_list
-- ----------------------------
DROP TABLE IF EXISTS `exam_signup_list`;
CREATE TABLE `exam_signup_list` (
  `signup_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试报名表id',
  `exam_type_id` int(11) NOT NULL COMMENT '考试类别表id',
  `details_id` int(11) NOT NULL COMMENT '用户个人信息表id',
  `signup_time` datetime NOT NULL COMMENT '考试报名时间',
  `signup_isconfirm` tinyint(4) NOT NULL COMMENT '审核是否通过',
  PRIMARY KEY (`signup_id`),
  KEY `exam_type_id2` (`exam_type_id`),
  KEY `details_id` (`details_id`),
  CONSTRAINT `details_id` FOREIGN KEY (`details_id`) REFERENCES `user_details` (`details_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exam_type_id2` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for exam_type
-- ----------------------------
DROP TABLE IF EXISTS `exam_type`;
CREATE TABLE `exam_type` (
  `exam_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试类别表id',
  `exam_name` varchar(255) NOT NULL,
  `exam_isclosed` tinyint(4) NOT NULL COMMENT '考试是否已经结束',
  `exam_issignup` tinyint(4) NOT NULL COMMENT '考试是否正在报名',
  `exam_isquery` tinyint(4) NOT NULL COMMENT '考试是否可查询',
  `exam_audited_num` int(11) NOT NULL COMMENT '考试已审核人数',
  `exam_capacity` int(11) NOT NULL COMMENT '考试可容纳人数',
  PRIMARY KEY (`exam_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for operating
-- ----------------------------
DROP TABLE IF EXISTS `operating`;
CREATE TABLE `operating` (
  `operating_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作表id',
  `operating_name` varchar(50) NOT NULL COMMENT '操作名称',
  `operating_coding` varchar(50) NOT NULL COMMENT '操作编码',
  `operating_intercept` varchar(255) NOT NULL COMMENT '拦截url前缀',
  `operating_parent_id` int(11) DEFAULT NULL COMMENT '父操作id',
  PRIMARY KEY (`operating_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表id',
  `permission_type` varchar(20) NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rel_pm_op
-- ----------------------------
DROP TABLE IF EXISTS `rel_pm_op`;
CREATE TABLE `rel_pm_op` (
  `rel_pm_op_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '操作权限表id',
  `operating_id` int(11) NOT NULL COMMENT '操作表关键字',
  `permission_id` int(11) NOT NULL COMMENT '权限表关键字',
  PRIMARY KEY (`rel_pm_op_id`),
  KEY `operating_id` (`operating_id`),
  KEY `permission_id2` (`permission_id`),
  CONSTRAINT `operating_id` FOREIGN KEY (`operating_id`) REFERENCES `operating` (`operating_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permission_id2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rel_role_pm
-- ----------------------------
DROP TABLE IF EXISTS `rel_role_pm`;
CREATE TABLE `rel_role_pm` (
  `rel_role_pm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色权限表id',
  `role_id` int(11) NOT NULL COMMENT '角色表关键字',
  `permission_id` int(11) NOT NULL COMMENT '权限表关键字',
  PRIMARY KEY (`rel_role_pm_id`),
  KEY `permission_id` (`permission_id`),
  KEY `role_id2` (`role_id`),
  CONSTRAINT `permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id2` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rel_ug_role
-- ----------------------------
DROP TABLE IF EXISTS `rel_ug_role`;
CREATE TABLE `rel_ug_role` (
  `rel_ug_ch_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组与角色关联id',
  `group_id` int(11) NOT NULL COMMENT '用户组表关键字',
  `role_id` int(11) NOT NULL COMMENT '角色表关键字',
  PRIMARY KEY (`rel_ug_ch_id`),
  KEY `role_id` (`role_id`),
  KEY `group_id2` (`group_id`),
  CONSTRAINT `group_id2` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rel_ui_ug
-- ----------------------------
DROP TABLE IF EXISTS `rel_ui_ug`;
CREATE TABLE `rel_ui_ug` (
  `rel_ui_ug_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户与用户组关联id',
  `userinfo_id` int(11) NOT NULL COMMENT '用户账号密码表关键字（外键）',
  `group_id` int(11) NOT NULL COMMENT '用户组表关键字',
  PRIMARY KEY (`rel_ui_ug_id`),
  KEY `group_id` (`group_id`),
  KEY `userinfo_id` (`userinfo_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userinfo_id` FOREIGN KEY (`userinfo_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源表id',
  `new_id` int(11) NOT NULL COMMENT '考试新闻表id',
  `res_link_address` varchar(255) NOT NULL COMMENT '资源链接地址',
  `res_createtime` datetime NOT NULL COMMENT '资源创建时间',
  PRIMARY KEY (`resource_id`),
  KEY `new_id` (`new_id`),
  CONSTRAINT `new_id` FOREIGN KEY (`new_id`) REFERENCES `exam_news` (`news_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_notice
-- ----------------------------
DROP TABLE IF EXISTS `system_notice`;
CREATE TABLE `system_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统通知表id',
  `notice_sender_id` int(11) NOT NULL COMMENT '发送者id',
  `notice_receive_id` int(11) NOT NULL,
  `notice_content` varchar(255) NOT NULL COMMENT '消息内容',
  `notice_createtime` datetime NOT NULL COMMENT '消息时间',
  `notice_isread` tinyint(4) NOT NULL COMMENT '是否已读',
  PRIMARY KEY (`notice_id`),
  KEY `notice_sender_id` (`notice_sender_id`),
  KEY `notice_receive_id` (`notice_receive_id`),
  CONSTRAINT `notice_receive_id` FOREIGN KEY (`notice_receive_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `notice_sender_id` FOREIGN KEY (`notice_sender_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_details
-- ----------------------------
DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `details_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户个人信息表id',
  `details_avatar` varchar(255) NOT NULL COMMENT '用户头像（有一个默认值）',
  `details_phone` char(11) DEFAULT NULL COMMENT '用户手机号',
  `details_address` varchar(255) DEFAULT NULL COMMENT '用户个人地址',
  `details_realname` varchar(255) DEFAULT NULL COMMENT '用户真实姓名',
  `details_idcard` varchar(255) DEFAULT NULL COMMENT '用户身份证号',
  PRIMARY KEY (`details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组id',
  `group_name` varchar(16) NOT NULL COMMENT '用户组名称',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `userinfo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userinfo_account` varchar(50) NOT NULL COMMENT '用户账号',
  `userinfo_password` varchar(16) NOT NULL COMMENT '用户密码',
  `userinfo_email` varchar(255) NOT NULL COMMENT '用户邮箱（用来通知考试，必填）',
  PRIMARY KEY (`userinfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
  `role_name` varchar(255) NOT NULL COMMENT '用户角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
