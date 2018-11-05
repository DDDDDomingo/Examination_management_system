/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : beita

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-11-05 13:05:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admission_ticket_info
-- ----------------------------
DROP TABLE IF EXISTS `admission_ticket_info`;
CREATE TABLE `admission_ticket_info` (
  `ticket_info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试信息id',
  `userinfo_id` char(12) NOT NULL COMMENT '用户账号表关键字',
  `ticket_info_name` varchar(255) NOT NULL COMMENT '准考证id(需要一个生成算法)',
  `ticket_info_identifier` char(18) NOT NULL,
  `ticket_info_time` datetime NOT NULL COMMENT '考试开始时间',
  `ticket_info_duration` int(11) NOT NULL COMMENT '考试时间长度',
  `ticket_info_seatnum` int(3) NOT NULL COMMENT '考场座位号',
  `ticket_info_school` varchar(255) NOT NULL COMMENT '考试学校',
  PRIMARY KEY (`ticket_info_id`),
  KEY `ticket_info_identifier` (`ticket_info_identifier`),
  KEY `userinfo_id2` (`userinfo_id`),
  CONSTRAINT `userinfo_id2` FOREIGN KEY (`userinfo_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admission_ticket_info
-- ----------------------------

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
-- Records of exam_news
-- ----------------------------

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
-- Records of exam_news_type
-- ----------------------------

-- ----------------------------
-- Table structure for exam_score
-- ----------------------------
DROP TABLE IF EXISTS `exam_score`;
CREATE TABLE `exam_score` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试成绩表id',
  `exam_type_id` char(18) NOT NULL COMMENT '考试类别表id',
  `session_id` int(11) NOT NULL,
  `ticket_info_identifier` char(18) NOT NULL COMMENT '准考证id',
  `score_num` varchar(11) NOT NULL DEFAULT '0' COMMENT '成绩',
  PRIMARY KEY (`score_id`),
  KEY `exam_type_id4` (`exam_type_id`),
  KEY `ticket_info_identifier` (`ticket_info_identifier`),
  KEY `rel_examsession_examscore` (`session_id`),
  CONSTRAINT `exam_type_id4` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rel_examsession_examscore` FOREIGN KEY (`session_id`) REFERENCES `exam_session` (`session_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ticket_info_identifier` FOREIGN KEY (`ticket_info_identifier`) REFERENCES `admission_ticket_info` (`ticket_info_identifier`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_score
-- ----------------------------

-- ----------------------------
-- Table structure for exam_session
-- ----------------------------
DROP TABLE IF EXISTS `exam_session`;
CREATE TABLE `exam_session` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试场次id',
  `exam_type_id` char(8) NOT NULL COMMENT '考试类别表id',
  `session_place` varchar(255) NOT NULL COMMENT '考试场次地点',
  `session_time` datetime NOT NULL COMMENT '考试时间',
  `session_capacity` int(11) NOT NULL COMMENT '考场容量',
  PRIMARY KEY (`session_id`),
  KEY `exam_type_id2` (`exam_type_id`),
  CONSTRAINT `exam_type_id2` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_session
-- ----------------------------

-- ----------------------------
-- Table structure for exam_signup_list
-- ----------------------------
DROP TABLE IF EXISTS `exam_signup_list`;
CREATE TABLE `exam_signup_list` (
  `signup_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试报名表id',
  `exam_type_id` char(8) NOT NULL COMMENT '考试类别表id',
  `signup_pic` varchar(255) NOT NULL COMMENT '考试费用提交凭证',
  `details_id` char(12) NOT NULL COMMENT '用户个人信息表id',
  `signup_time` datetime NOT NULL COMMENT '考试报名时间',
  `signup_isconfirm` tinyint(4) NOT NULL COMMENT '审核是否通过',
  `signup_birth_month` int(11) NOT NULL COMMENT '考生出生月份（分配标准）',
  PRIMARY KEY (`signup_id`),
  KEY `exam_type_id` (`exam_type_id`),
  KEY `details_id` (`details_id`),
  CONSTRAINT `details_id` FOREIGN KEY (`details_id`) REFERENCES `user_details` (`details_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exam_type_id` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_signup_list
-- ----------------------------

-- ----------------------------
-- Table structure for exam_type
-- ----------------------------
DROP TABLE IF EXISTS `exam_type`;
CREATE TABLE `exam_type` (
  `exam_type_id` char(8) NOT NULL COMMENT '考试类别表id',
  `exam_name` varchar(255) NOT NULL,
  `exam_isclosed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '考试是否已经结束',
  `exam_issignup` tinyint(1) NOT NULL DEFAULT '0' COMMENT '考试是否正在报名',
  `exam_isquery` tinyint(1) NOT NULL DEFAULT '0' COMMENT '考试是否可查询',
  `exam_audited_num` int(11) NOT NULL DEFAULT '0' COMMENT '考试已审核人数',
  `exam_capacity` int(11) NOT NULL DEFAULT '0' COMMENT '考试可容纳人数',
  `exam_starttime` datetime NOT NULL,
  `exam_endtime` datetime NOT NULL,
  PRIMARY KEY (`exam_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam_type
-- ----------------------------
INSERT INTO `exam_type` VALUES ('18102858', '计算机二级考试', '1', '0', '0', '0', '1000', '2018-10-28 00:00:00', '2018-11-28 00:00:00');

-- ----------------------------
-- Table structure for idcard_photo
-- ----------------------------
DROP TABLE IF EXISTS `idcard_photo`;
CREATE TABLE `idcard_photo` (
  `idcard_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '身份证照片ID',
  `userinfo_id` char(12) NOT NULL COMMENT '用户个人信息表ID',
  `idcard_front_photo_url` varchar(255) NOT NULL COMMENT '身份证正面照片映射地址',
  `idcard_ front _photo_savepath` varchar(255) NOT NULL COMMENT '身份证正面照片存储地址',
  `Idcard_reverse_photo_url` varchar(255) NOT NULL,
  `Idcard_reverse_photo_savepath` varchar(255) NOT NULL,
  PRIMARY KEY (`idcard_id`),
  KEY `userinfo_id5` (`userinfo_id`),
  CONSTRAINT `userinfo_id5` FOREIGN KEY (`userinfo_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of idcard_photo
-- ----------------------------

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
-- Records of operating
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表id',
  `permission_type` varchar(20) NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '报名信息查看');
INSERT INTO `permission` VALUES ('2', '报名信息审核');
INSERT INTO `permission` VALUES ('3', '添加考试场次');
INSERT INTO `permission` VALUES ('4', '删除考试场次');
INSERT INTO `permission` VALUES ('5', '修改考试场次');
INSERT INTO `permission` VALUES ('6', '查看考试场次');
INSERT INTO `permission` VALUES ('7', '录入考试成绩');
INSERT INTO `permission` VALUES ('8', '修改考试成绩');
INSERT INTO `permission` VALUES ('9', '查看考试成绩');
INSERT INTO `permission` VALUES ('10', '添加考试新闻');
INSERT INTO `permission` VALUES ('11', '添加考试新闻资源');
INSERT INTO `permission` VALUES ('12', '删除考试新闻');
INSERT INTO `permission` VALUES ('13', '删除考试新闻资源');
INSERT INTO `permission` VALUES ('14', '修改考试新闻');
INSERT INTO `permission` VALUES ('15', '查看考试新闻');
INSERT INTO `permission` VALUES ('16', '查看考试新闻资源');
INSERT INTO `permission` VALUES ('17', '下载考试新闻资源');
INSERT INTO `permission` VALUES ('18', '下载准考证信息');
INSERT INTO `permission` VALUES ('19', '发送系统通知');
INSERT INTO `permission` VALUES ('20', '添加管理员账号');
INSERT INTO `permission` VALUES ('21', '实名认证上传图片');

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
-- Records of rel_pm_op
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_role_pm
-- ----------------------------
INSERT INTO `rel_role_pm` VALUES ('1', '1', '1');
INSERT INTO `rel_role_pm` VALUES ('2', '1', '2');
INSERT INTO `rel_role_pm` VALUES ('3', '1', '3');
INSERT INTO `rel_role_pm` VALUES ('4', '1', '4');
INSERT INTO `rel_role_pm` VALUES ('5', '1', '5');
INSERT INTO `rel_role_pm` VALUES ('6', '1', '6');
INSERT INTO `rel_role_pm` VALUES ('7', '1', '7');
INSERT INTO `rel_role_pm` VALUES ('8', '1', '8');
INSERT INTO `rel_role_pm` VALUES ('9', '1', '9');
INSERT INTO `rel_role_pm` VALUES ('10', '1', '10');
INSERT INTO `rel_role_pm` VALUES ('11', '1', '11');
INSERT INTO `rel_role_pm` VALUES ('12', '1', '12');
INSERT INTO `rel_role_pm` VALUES ('13', '1', '13');
INSERT INTO `rel_role_pm` VALUES ('14', '1', '14');
INSERT INTO `rel_role_pm` VALUES ('15', '1', '15');
INSERT INTO `rel_role_pm` VALUES ('16', '1', '16');
INSERT INTO `rel_role_pm` VALUES ('17', '1', '17');
INSERT INTO `rel_role_pm` VALUES ('18', '1', '19');
INSERT INTO `rel_role_pm` VALUES ('20', '1', '20');
INSERT INTO `rel_role_pm` VALUES ('21', '2', '1');
INSERT INTO `rel_role_pm` VALUES ('22', '2', '2');
INSERT INTO `rel_role_pm` VALUES ('23', '2', '6');
INSERT INTO `rel_role_pm` VALUES ('24', '2', '9');
INSERT INTO `rel_role_pm` VALUES ('25', '2', '15');
INSERT INTO `rel_role_pm` VALUES ('26', '2', '16');
INSERT INTO `rel_role_pm` VALUES ('27', '2', '19');
INSERT INTO `rel_role_pm` VALUES ('28', '3', '1');
INSERT INTO `rel_role_pm` VALUES ('29', '3', '3');
INSERT INTO `rel_role_pm` VALUES ('30', '3', '4');
INSERT INTO `rel_role_pm` VALUES ('31', '3', '5');
INSERT INTO `rel_role_pm` VALUES ('32', '3', '6');
INSERT INTO `rel_role_pm` VALUES ('34', '3', '9');
INSERT INTO `rel_role_pm` VALUES ('35', '3', '15');
INSERT INTO `rel_role_pm` VALUES ('36', '3', '16');
INSERT INTO `rel_role_pm` VALUES ('37', '3', '19');
INSERT INTO `rel_role_pm` VALUES ('38', '4', '1');
INSERT INTO `rel_role_pm` VALUES ('39', '4', '6');
INSERT INTO `rel_role_pm` VALUES ('40', '4', '7');
INSERT INTO `rel_role_pm` VALUES ('41', '4', '8');
INSERT INTO `rel_role_pm` VALUES ('42', '4', '9');
INSERT INTO `rel_role_pm` VALUES ('43', '4', '15');
INSERT INTO `rel_role_pm` VALUES ('44', '4', '16');
INSERT INTO `rel_role_pm` VALUES ('45', '4', '19');
INSERT INTO `rel_role_pm` VALUES ('46', '5', '1');
INSERT INTO `rel_role_pm` VALUES ('47', '5', '6');
INSERT INTO `rel_role_pm` VALUES ('48', '5', '9');
INSERT INTO `rel_role_pm` VALUES ('49', '5', '10');
INSERT INTO `rel_role_pm` VALUES ('50', '5', '11');
INSERT INTO `rel_role_pm` VALUES ('51', '5', '12');
INSERT INTO `rel_role_pm` VALUES ('52', '5', '13');
INSERT INTO `rel_role_pm` VALUES ('53', '5', '14');
INSERT INTO `rel_role_pm` VALUES ('54', '5', '15');
INSERT INTO `rel_role_pm` VALUES ('55', '5', '16');
INSERT INTO `rel_role_pm` VALUES ('56', '5', '19');
INSERT INTO `rel_role_pm` VALUES ('57', '6', '15');
INSERT INTO `rel_role_pm` VALUES ('58', '6', '16');
INSERT INTO `rel_role_pm` VALUES ('59', '6', '17');
INSERT INTO `rel_role_pm` VALUES ('60', '6', '18');
INSERT INTO `rel_role_pm` VALUES ('61', '6', '21');
INSERT INTO `rel_role_pm` VALUES ('62', '7', '15');
INSERT INTO `rel_role_pm` VALUES ('63', '7', '16');

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_ug_role
-- ----------------------------
INSERT INTO `rel_ug_role` VALUES ('1', '1', '1');
INSERT INTO `rel_ug_role` VALUES ('2', '2', '2');
INSERT INTO `rel_ug_role` VALUES ('3', '3', '3');
INSERT INTO `rel_ug_role` VALUES ('4', '4', '4');
INSERT INTO `rel_ug_role` VALUES ('5', '5', '5');
INSERT INTO `rel_ug_role` VALUES ('6', '6', '6');
INSERT INTO `rel_ug_role` VALUES ('7', '7', '7');

-- ----------------------------
-- Table structure for rel_ui_ug
-- ----------------------------
DROP TABLE IF EXISTS `rel_ui_ug`;
CREATE TABLE `rel_ui_ug` (
  `rel_ui_ug_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户与用户组关联id',
  `userinfo_id` char(12) NOT NULL COMMENT '用户账号密码表关键字（外键）',
  `group_id` int(11) NOT NULL COMMENT '用户组表关键字',
  PRIMARY KEY (`rel_ui_ug_id`),
  KEY `group_id` (`group_id`),
  KEY `userinfo_id` (`userinfo_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userinfo_id` FOREIGN KEY (`userinfo_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rel_ui_ug
-- ----------------------------
INSERT INTO `rel_ui_ug` VALUES ('1', '181028562814', '1');
INSERT INTO `rel_ui_ug` VALUES ('2', '180928562814', '1');
INSERT INTO `rel_ui_ug` VALUES ('3', '180828562814', '1');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源表id',
  `new_id` int(11) NOT NULL COMMENT '考试新闻表id',
  `res_link_address` varchar(255) NOT NULL COMMENT '资源链接地址',
  `rel_savepath` varchar(255) NOT NULL COMMENT '资源存储地址',
  `res_createtime` datetime NOT NULL COMMENT '资源创建时间',
  PRIMARY KEY (`resource_id`),
  KEY `new_id` (`new_id`),
  CONSTRAINT `new_id` FOREIGN KEY (`new_id`) REFERENCES `exam_news` (`news_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for review_personnel
-- ----------------------------
DROP TABLE IF EXISTS `review_personnel`;
CREATE TABLE `review_personnel` (
  `enter_p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试录入表id',
  `exam_type_id` char(8) NOT NULL COMMENT '考试类别表id',
  `userinfo_id` char(12) NOT NULL COMMENT '被派发人员 id',
  `enter_is_check` tinyint(1) NOT NULL COMMENT '是否已经审核',
  `start_review` datetime NOT NULL,
  `end_review` datetime NOT NULL,
  `enter_type` tinyint(1) NOT NULL COMMENT '0为报名审核人员，1为录入人员',
  PRIMARY KEY (`enter_p_id`),
  KEY `exam_type_id3` (`exam_type_id`),
  KEY `userinfo_id3` (`userinfo_id`),
  CONSTRAINT `exam_type_id3` FOREIGN KEY (`exam_type_id`) REFERENCES `exam_type` (`exam_type_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userinfo_id3` FOREIGN KEY (`userinfo_id`) REFERENCES `user_info` (`userinfo_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of review_personnel
-- ----------------------------

-- ----------------------------
-- Table structure for system_notice
-- ----------------------------
DROP TABLE IF EXISTS `system_notice`;
CREATE TABLE `system_notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系统通知表id',
  `notice_sender_id` char(12) NOT NULL COMMENT '发送者id',
  `notice_receive_id` char(12) NOT NULL,
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
-- Records of system_notice
-- ----------------------------

-- ----------------------------
-- Table structure for user_details
-- ----------------------------
DROP TABLE IF EXISTS `user_details`;
CREATE TABLE `user_details` (
  `details_id` char(12) NOT NULL COMMENT '用户个人信息表id',
  `details_avatar` varchar(255) NOT NULL DEFAULT '110.10.1.1' COMMENT '用户头像（有一个默认值）',
  `details_savepath` varchar(255) DEFAULT NULL COMMENT '用户头像存储地址',
  `details_phone` char(11) DEFAULT NULL COMMENT '用户手机号',
  `details_address` varchar(255) DEFAULT NULL COMMENT '用户个人地址',
  `details_realname` varchar(255) DEFAULT NULL COMMENT '用户真实姓名',
  `details_idcard` varchar(255) DEFAULT NULL COMMENT '用户身份证号',
  PRIMARY KEY (`details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_details
-- ----------------------------
INSERT INTO `user_details` VALUES ('181028969487', '110.10.1.1', null, null, null, null, null);

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户组id',
  `group_name` varchar(16) NOT NULL COMMENT '用户组名称',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('1', '最高系统管理员');
INSERT INTO `user_group` VALUES ('2', '报名审核人员');
INSERT INTO `user_group` VALUES ('3', '考场管理人员');
INSERT INTO `user_group` VALUES ('4', '成绩录入人员');
INSERT INTO `user_group` VALUES ('5', '门户网站管理员');
INSERT INTO `user_group` VALUES ('6', '注册用户');
INSERT INTO `user_group` VALUES ('7', '未注册用户');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `userinfo_id` char(12) NOT NULL COMMENT '用户id',
  `userinfo_account` varchar(50) NOT NULL COMMENT '用户账号',
  `userinfo_password` varchar(16) NOT NULL COMMENT '用户密码',
  `userinfo_email` varchar(255) DEFAULT NULL COMMENT '用户邮箱（用来通知考试，必填）',
  PRIMARY KEY (`userinfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('180828562814', 'admin', 'admin', null);
INSERT INTO `user_info` VALUES ('180928562814', 'administrator', '123456', null);
INSERT INTO `user_info` VALUES ('181028562814', 'admin', '123456', null);
INSERT INTO `user_info` VALUES ('181028969487', 'account1', 'pwd123', '1783610921@qq.com');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户角色id',
  `role_name` varchar(255) NOT NULL COMMENT '用户角色名称',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '最高级管理员');
INSERT INTO `user_role` VALUES ('2', '考试报名审核员');
INSERT INTO `user_role` VALUES ('3', '考试场次管理员');
INSERT INTO `user_role` VALUES ('4', '考试成绩录入人员');
INSERT INTO `user_role` VALUES ('5', '考试新闻录入人员');
INSERT INTO `user_role` VALUES ('6', '考生');
INSERT INTO `user_role` VALUES ('7', '游客');
