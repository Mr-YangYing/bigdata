/*
Navicat MySQL Data Transfer

Source Server         : yy
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : bigdata

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-12-11 13:54:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `classes`
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` bigint(255) NOT NULL DEFAULT '0',
  `classNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(50) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `teacherName` varchar(20) DEFAULT NULL,
  `labAddress` varchar(20) DEFAULT NULL,
  `courseOpen` tinyint(1) DEFAULT NULL COMMENT '是否开课 1:开课,0:未开课',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '课程1', '2017-10-30', '2017-12-12', '张三', 'A201', '0', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('2', '课程2', '2017-10-30', '2017-12-22', '李四', 'B501', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('3', '课程3', '2017-10-18', '2017-12-22', '刘达', 'A203', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('4', '课程4', '2017-10-04', '2017-12-22', '阿斯蒂芬', 'C221', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('5', '课程5', '2017-10-23', '2017-12-22', '省道', 'C211', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('6', '课程6', '2017-10-02', '2017-12-22', '对的', 'C322', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('7', '课程111', '2017-09-09', '2017-12-22', '张三', 'A221', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('8', '课程222', '2017-10-09', '2017-12-22', '舒阿文', 'C109', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('9', '张三', '2017-09-09', '2017-12-22', '课程111', 'A221', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('10', '舒阿文', '2017-10-09', '2017-12-22', '课程222', 'C109', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('13', '课程111', '2017-09-09', '2017-12-22', '张三', 'A221', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('14', '课程222', '2017-10-09', '2017-12-22', '舒阿文', 'C109', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('15', '课程111', '2017-09-09', '2017-12-22', '张三', 'A221', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('16', '课程222', '2017-10-09', '2017-12-22', '舒阿文', 'C109', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('17', '课程111', '2017-09-09', '2017-12-22', '张三', 'A221', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('18', '课程222', '2017-10-09', '2017-12-22', '舒阿文', 'C109', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('19', '课程111', '2017-09-09', '2017-12-22', '张三', 'A221', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('20', '课程222', '2017-10-09', '2017-12-22', '舒阿文', 'C109', '1', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('21', '是', '2017-09-09', '2017-12-22', null, null, '0', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('22', '是', '2017-10-09', null, null, null, '0', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述');
INSERT INTO `course` VALUES ('23', '课程111', '2017-09-09', null, '张三', 'A221', '1', null);
INSERT INTO `course` VALUES ('24', '课程222', '2017-10-09', null, '舒阿文', 'C109', '1', null);
INSERT INTO `course` VALUES ('25', '课程111', '2017-09-09', null, '张三', 'A221', '1', null);
INSERT INTO `course` VALUES ('26', '课程222', '2017-10-09', null, '舒阿文', 'C109', '1', null);
INSERT INTO `course` VALUES ('27', '课程111', '2017-09-09', null, '张三', 'A221', '1', null);
INSERT INTO `course` VALUES ('28', '课程222', '2017-10-09', null, '舒阿文', 'C109', '1', null);
INSERT INTO `course` VALUES ('29', '张三', '2017-09-09', null, '课程111', 'A221', '1', null);
INSERT INTO `course` VALUES ('30', '舒阿文', '2017-10-09', null, '课程222', 'C109', '1', null);
INSERT INTO `course` VALUES ('31', '课程111', '2017-09-09', '2017-12-22', '张三', 'A221', '1', null);
INSERT INTO `course` VALUES ('32', '课程222', '2017-10-09', '2017-12-12', '舒阿文', 'C109', '1', null);
INSERT INTO `course` VALUES ('33', '课程111', '2017-09-09', '2017-12-22', '张三', 'A221', '1', null);
INSERT INTO `course` VALUES ('34', '课程222', '2017-10-09', '2017-12-12', '舒阿文', 'C109', '0', null);
INSERT INTO `course` VALUES ('35', '课程111', '2017-09-09', '2017-12-22', '张三', 'A221', '1', null);
INSERT INTO `course` VALUES ('36', '课程222', '2017-10-09', '2017-12-12', '舒阿文', 'C109', '1', null);

-- ----------------------------
-- Table structure for `course_classes_config`
-- ----------------------------
DROP TABLE IF EXISTS `course_classes_config`;
CREATE TABLE `course_classes_config` (
  `cou_id` bigint(20) NOT NULL DEFAULT '0',
  `cla_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cou_id`,`cla_id`),
  KEY `cla_cla_FK` (`cla_id`),
  CONSTRAINT `cla_cla_FK` FOREIGN KEY (`cla_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `cou_cla_FK` FOREIGN KEY (`cou_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_classes_config
-- ----------------------------

-- ----------------------------
-- Table structure for `course_student_config`
-- ----------------------------
DROP TABLE IF EXISTS `course_student_config`;
CREATE TABLE `course_student_config` (
  `cou_id` bigint(255) NOT NULL DEFAULT '0',
  `stu_id` bigint(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cou_id`,`stu_id`),
  KEY `stu_stu_FK` (`stu_id`),
  CONSTRAINT `cou_cou_FK` FOREIGN KEY (`cou_id`) REFERENCES `course` (`id`),
  CONSTRAINT `stu_stu_FK` FOREIGN KEY (`stu_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_student_config
-- ----------------------------
INSERT INTO `course_student_config` VALUES ('1', '1');
INSERT INTO `course_student_config` VALUES ('2', '1');
INSERT INTO `course_student_config` VALUES ('3', '1');
INSERT INTO `course_student_config` VALUES ('2', '2');

-- ----------------------------
-- Table structure for `course_teacher_config`
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher_config`;
CREATE TABLE `course_teacher_config` (
  `cou_id` bigint(255) NOT NULL,
  `tea_id` bigint(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`cou_id`,`tea_id`),
  KEY `tea_cou_FK` (`tea_id`),
  CONSTRAINT `cou_tea_FK` FOREIGN KEY (`cou_id`) REFERENCES `course` (`id`),
  CONSTRAINT `tea_cou_FK` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_teacher_config
-- ----------------------------
INSERT INTO `course_teacher_config` VALUES ('1', '1');
INSERT INTO `course_teacher_config` VALUES ('4', '1');
INSERT INTO `course_teacher_config` VALUES ('1', '2');
INSERT INTO `course_teacher_config` VALUES ('2', '3');

-- ----------------------------
-- Table structure for `permission`
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(32) DEFAULT NULL COMMENT '权限名',
  `permission_sign` varchar(128) DEFAULT NULL COMMENT '权限标识,程序中判断使用,如"user:create"',
  `description` varchar(256) DEFAULT NULL COMMENT '权限描述,UI界面显示使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '新增用户', 'user:create', '对用户拥有增加的权限');
INSERT INTO `permission` VALUES ('2', '删除用户', 'user:delete', '对用户拥有删除的权限');
INSERT INTO `permission` VALUES ('3', '初审嫌疑', 'suspect:confirm', '对嫌疑名单进行初审确认');
INSERT INTO `permission` VALUES ('4', '审核初审', 'suspect:review', '对初审过的记录进行初审');
INSERT INTO `permission` VALUES ('5', '逃费处理', 'confirm:process', '对确认名单记录进行处理标记');

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resourceNumber` varchar(45) DEFAULT NULL,
  `resourceName` varchar(45) DEFAULT NULL,
  `resourceAddr` varchar(255) DEFAULT NULL,
  `uploader` varchar(45) DEFAULT NULL,
  `uploadDate` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `taskId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `resource_task_FK_idx` (`taskId`),
  CONSTRAINT `resource_task_FK` FOREIGN KEY (`taskId`) REFERENCES `task` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '22222', '资源名称2333', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\下载.png', '张三', '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '2');
INSERT INTO `resource` VALUES ('3', '222', '资源名称', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\test.xls', '我', '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '3');
INSERT INTO `resource` VALUES ('4', '222', '资源名称', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\test.xls', '对的', '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '5');
INSERT INTO `resource` VALUES ('5', '222', '资源名称', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\test.xls', '公共', '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '5');
INSERT INTO `resource` VALUES ('6', '222', '资源名称', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\test.xls', '简介', '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '6');
INSERT INTO `resource` VALUES ('7', '222', '资源名称', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\test.xls', '对的', '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '7');
INSERT INTO `resource` VALUES ('8', '222', '资源名称', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\test.xls', null, '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '2');
INSERT INTO `resource` VALUES ('11', '222', '资源名称', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\test.xls', null, '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '2');
INSERT INTO `resource` VALUES ('12', '222', '资源名称', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\test.xls', null, '2017-12-05', '描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述描述', '2');
INSERT INTO `resource` VALUES ('14', 'sss', 'sss', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\teacher.sql', null, '2017-12-05', '', '2');
INSERT INTO `resource` VALUES ('15', 'www', 'www', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\XMP.exe', null, '2017-12-05', 'www', '2');
INSERT INTO `resource` VALUES ('16', 'eeee', 'eeeee222', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\下载.png', null, '2017-12-05', 'eeee', '2');
INSERT INTO `resource` VALUES ('17', 'ddddd', 'ddddd', 'D:\\eclipseCode\\LargeDataTeachMS\\WebContent\\images\\下载.png', null, '2017-12-05', 'dddd', '2');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识,程序中判断使用,如"admin"',
  `description` varchar(300) DEFAULT NULL COMMENT '角色描述,UI界面显示使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'superviser', 'superviser', '车辆稽查班长');
INSERT INTO `role` VALUES ('2', 'inspecter', 'inspecter', '车辆稽查员');
INSERT INTO `role` VALUES ('3', 'superviser1', 'superviser1', '收费稽查员');
INSERT INTO `role` VALUES ('4', 'inspecter1', 'inspecter1', '收费稽查班长');

-- ----------------------------
-- Table structure for `role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `role_id` int(5) unsigned DEFAULT NULL COMMENT '角色id',
  `permission_id` int(5) unsigned DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='角色与权限关联表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '4');
INSERT INTO `role_permission` VALUES ('2', '2', '3');
INSERT INTO `role_permission` VALUES ('3', '2', '5');
INSERT INTO `role_permission` VALUES ('4', '3', '4');
INSERT INTO `role_permission` VALUES ('5', '4', '3');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `studentNumber` varchar(20) DEFAULT NULL,
  `studentName` varchar(20) DEFAULT NULL,
  `currentTerm` varchar(20) DEFAULT NULL,
  `college` varchar(20) DEFAULT NULL,
  `profession` varchar(20) DEFAULT NULL,
  `classesId` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentNumber` (`studentNumber`),
  KEY `student_classes_FK` (`classesId`),
  CONSTRAINT `student_classes_FK` FOREIGN KEY (`classesId`) REFERENCES `classes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '88888', '', '', '', '数理统计', null);
INSERT INTO `student` VALUES ('2', '444444', '', '', '', '数理统计', null);
INSERT INTO `student` VALUES ('3', '20170002', '9', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('4', '20170003', '9', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('5', '20170004', '9', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('6', '20170005', '99', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('7', '20170006', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('8', '20170007', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('9', '20170008', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('10', '20170009', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('11', '20170010', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('12', '20170011', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('13', '20170012', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('14', '20170013', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('15', '20170014', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('16', '20170015', '8', '2017第一学期', '经管学院', '数理统计', null);
INSERT INTO `student` VALUES ('17', '2222.0', '对对对', '111.0', '发发发', null, null);
INSERT INTO `student` VALUES ('23', '2222', '对对对', '111', '发发发', null, null);
INSERT INTO `student` VALUES ('24', '333', '反反复复', '222', '发发发', null, null);

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taskName` varchar(255) DEFAULT NULL,
  `taskType` tinyint(4) DEFAULT NULL,
  `uploadReport` tinyint(4) DEFAULT NULL COMMENT '上传报告是否开启    1:开启,0:关闭',
  `publishTask` tinyint(4) DEFAULT NULL COMMENT '是否发布任务  1:发布,0:未发布',
  `difficulty` tinyint(4) DEFAULT NULL,
  `usefulTime` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `courseId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `task_course_FK` (`courseId`),
  CONSTRAINT `task_course_FK` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('2', '是是是', null, null, '0', null, '4', '', '1');
INSERT INTO `task` VALUES ('3', '00', '0', '0', '1', '0', '0', '', '2');
INSERT INTO `task` VALUES ('4', '00', '0', '0', '0', '0', '0', '', '2');
INSERT INTO `task` VALUES ('5', '00', '0', '0', '0', '0', '0', '', '3');
INSERT INTO `task` VALUES ('6', '00', '0', '0', '0', '0', '0', '', '4');
INSERT INTO `task` VALUES ('7', '00', '0', '0', '1', '0', '0', '', '1');
INSERT INTO `task` VALUES ('8', '00', '0', '0', '1', '0', '0', '', '1');
INSERT INTO `task` VALUES ('9', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('10', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('11', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('12', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('13', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('14', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('15', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('16', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('17', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('18', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('19', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('20', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('21', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('22', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('23', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('24', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('25', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('26', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('27', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('28', '00', '0', '0', '0', '0', '0', '', '1');
INSERT INTO `task` VALUES ('29', '00', '0', '0', '0', '0', '0', '', '1');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `teacherAccount` varchar(255) DEFAULT NULL,
  `teacherName` varchar(20) DEFAULT NULL,
  `positionalTitles` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'zhangsan', '张三2', '助教');
INSERT INTO `teacher` VALUES ('2', 'lisia', '李四三', '讲师');
INSERT INTO `teacher` VALUES ('3', 'wangwu', '王五三', '讲师');
INSERT INTO `teacher` VALUES ('4', 'fffa', '是否三9', '讲师');
INSERT INTO `teacher` VALUES ('5', '5', '5', '讲师');
INSERT INTO `teacher` VALUES ('6', '6', '6', '讲师');
INSERT INTO `teacher` VALUES ('7', '7', '7', '副教授');
INSERT INTO `teacher` VALUES ('8', '9', '9', '讲师');
INSERT INTO `teacher` VALUES ('9', '9', '9', '教授');
INSERT INTO `teacher` VALUES ('15', 'aa', 'aa', '助教');
INSERT INTO `teacher` VALUES ('16', '222.0', '而定', '讲师');
INSERT INTO `teacher` VALUES ('17', '333.0', '对的', '教授');
INSERT INTO `teacher` VALUES ('20', '222.0', '而定', '讲师');
INSERT INTO `teacher` VALUES ('22', '222', '而定', '讲师');
INSERT INTO `teacher` VALUES ('23', '333', '对的', '教授');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` char(100) DEFAULT NULL COMMENT '密码',
  `inspector_id` int(20) DEFAULT NULL,
  `organ_id` int(20) DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'superadmin_mf', 'e10adc3949ba59abbe56e057f20f883e', null, '1000104', null, '2014-07-17 12:59:08');
INSERT INTO `user` VALUES ('2', 'admin_mf', 'e10adc3949ba59abbe56e057f20f883e', null, '1000112', null, '2016-12-23 10:11:18');
INSERT INTO `user` VALUES ('7', 'superadmin_qh', 'e10adc3949ba59abbe56e057f20f883e', null, '1000002', null, '2017-03-29 14:20:07');
INSERT INTO `user` VALUES ('8', 'admin_qh', 'e10adc3949ba59abbe56e057f20f883e', '8', '1000002', null, '2017-03-29 14:20:10');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` int(20) unsigned DEFAULT NULL COMMENT '用户id',
  `role_id` int(20) unsigned DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户与角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
INSERT INTO `user_role` VALUES ('7', '7', '1');
INSERT INTO `user_role` VALUES ('8', '8', '2');
