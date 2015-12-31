/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : ltcms

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-05-08 01:26:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for lt_content
-- ----------------------------
DROP TABLE IF EXISTS `lt_content`;
CREATE TABLE `lt_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hdd9wl96nwdbirf08kgsw1j7y` (`user_id`),
  CONSTRAINT `FK_hdd9wl96nwdbirf08kgsw1j7y` FOREIGN KEY (`user_id`) REFERENCES `lt_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_content
-- ----------------------------
INSERT INTO `lt_content` VALUES ('1', '张三', '中散大夫士大夫', '2015-05-06 00:33:51', null, null, null, null, null);
INSERT INTO `lt_content` VALUES ('2', null, null, '2015-05-06 00:32:29', '0', 'keke', '1', null, null);
INSERT INTO `lt_content` VALUES ('3', null, null, '2015-05-06 00:32:40', '0', 'keke', '1', null, null);

-- ----------------------------
-- Table structure for lt_content2
-- ----------------------------
DROP TABLE IF EXISTS `lt_content2`;
CREATE TABLE `lt_content2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_content2
-- ----------------------------
INSERT INTO `lt_content2` VALUES ('1', 'xxxxxxxxxxxxxx', 'dddddddddddd');
INSERT INTO `lt_content2` VALUES ('2', 'xxxxxxxxxxxxxx', 'dddddddddddd');
INSERT INTO `lt_content2` VALUES ('3', 'xxxxxxxxxxxxxx', 'dddddddddddd');
INSERT INTO `lt_content2` VALUES ('4', 'xxxxxxxxxxxxxx', 'dddddddddddd');
INSERT INTO `lt_content2` VALUES ('5', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('6', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('7', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('8', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('9', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('10', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('11', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('12', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('13', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('14', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('15', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('16', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('17', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('18', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('19', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('20', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('21', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('22', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('23', '32234342', 'sdfsdfsd');
INSERT INTO `lt_content2` VALUES ('24', '32234342', 'sdfsdfsd');

-- ----------------------------
-- Table structure for lt_folder
-- ----------------------------
DROP TABLE IF EXISTS `lt_folder`;
CREATE TABLE `lt_folder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_folder
-- ----------------------------
INSERT INTO `lt_folder` VALUES ('1', '计算机', null);
INSERT INTO `lt_folder` VALUES ('2', 'C盘', '1');
INSERT INTO `lt_folder` VALUES ('3', 'D盘', '1');
INSERT INTO `lt_folder` VALUES ('4', 'F盘', '1');
INSERT INTO `lt_folder` VALUES ('5', 'keke', '2');

-- ----------------------------
-- Table structure for lt_permission
-- ----------------------------
DROP TABLE IF EXISTS `lt_permission`;
CREATE TABLE `lt_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `method` varchar(50) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_c09js2cdprxfvryhk4bm2bu1d` (`parent_id`),
  CONSTRAINT `FK_c09js2cdprxfvryhk4bm2bu1d` FOREIGN KEY (`parent_id`) REFERENCES `lt_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_permission
-- ----------------------------
INSERT INTO `lt_permission` VALUES ('1', null, null, '根目录', null, null, '2015-04-30 21:46:36');
INSERT INTO `lt_permission` VALUES ('2', 'list', 'banner', 'banner管理', '/admin/banner/list', '1', '2015-04-30 21:46:36');
INSERT INTO `lt_permission` VALUES ('3', 'list', 'content', '课程管理', '/admin/content/list', '1', '2015-04-30 21:46:36');
INSERT INTO `lt_permission` VALUES ('4', 'list', 'news', '新闻管理', '/admin/news/list', '1', '2015-04-30 21:46:55');
INSERT INTO `lt_permission` VALUES ('5', 'list', 'introduce', '公司介绍', '/admin/introduce/list', null, '2015-04-30 22:16:25');
INSERT INTO `lt_permission` VALUES ('6', 'index', 'index', '首页', '/index', null, '2015-04-30 23:00:29');
INSERT INTO `lt_permission` VALUES ('7', 'add', 'news', '增加新闻', '/admin/news/add', null, '2015-05-01 00:12:23');

-- ----------------------------
-- Table structure for lt_permission2
-- ----------------------------
DROP TABLE IF EXISTS `lt_permission2`;
CREATE TABLE `lt_permission2` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_delete` int(11) DEFAULT NULL,
  `method` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_c09js2cdprxfvryhk4bm2bu1d` USING BTREE (`parent_id`),
  CONSTRAINT `FK_mbbig8yg3x0e6v1n76j8ehfpc` FOREIGN KEY (`parent_id`) REFERENCES `lt_permission2` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=195 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_permission2
-- ----------------------------
INSERT INTO `lt_permission2` VALUES ('1', '2015-05-03 20:33:28', '0', null, null, '根目录', '1', null, null, '/');
INSERT INTO `lt_permission2` VALUES ('2', '2015-05-03 20:40:38', '0', 'index', 'index', '后台首页', '120', '2015-05-04 13:36:49', '1', '/admin/index');
INSERT INTO `lt_permission2` VALUES ('3', '2015-05-03 20:36:36', '0', 'cluster', 'cluster', '集群管理', '3', null, '1', '/admin/cluster/channel');
INSERT INTO `lt_permission2` VALUES ('4', '2015-05-03 20:36:36', '0', 'department', 'department', '部门管理', '4', null, '1', '/admin/department/channel');
INSERT INTO `lt_permission2` VALUES ('5', '2015-05-03 20:36:37', '0', 'moonUser', 'moonUser', '员工管理', '5', null, '1', '/admin/moonUser/channel');
INSERT INTO `lt_permission2` VALUES ('6', '2015-05-03 20:36:37', '0', 'service', 'service', '中间服务部门管理', '6', null, '1', '/admin/service/channel');
INSERT INTO `lt_permission2` VALUES ('7', '2015-05-03 20:36:37', '0', 'performance', 'performance', '部门业绩比例管理', '7', null, '1', '/admin/performance/channel');
INSERT INTO `lt_permission2` VALUES ('8', '2015-05-03 20:36:38', '0', 'vipuser', 'vipuser', 'vip学员', '12', '2015-05-05 19:29:55', '1', '/admin/vipuser/channel');
INSERT INTO `lt_permission2` VALUES ('9', '2015-05-03 20:36:39', '0', 'hzy', 'hzy', '虹之玉统计管理', '9', null, '1', '/admin/hzy/channel');
INSERT INTO `lt_permission2` VALUES ('10', '2015-04-30 16:35:58', '0', 'list', 'cluster', '查询所有的集群信息', '10', null, '3', '/admin/cluster/list');
INSERT INTO `lt_permission2` VALUES ('11', '2015-04-30 16:36:59', '0', 'countCluster', 'cluster', '求总数', '11', null, '3', '/admin/cluster/countCluster');
INSERT INTO `lt_permission2` VALUES ('12', '2015-04-30 16:38:13', '0', 'listTemplate', 'cluster', 'ajax加载模版', '12', null, '3', '/admin/cluster/listTemplate');
INSERT INTO `lt_permission2` VALUES ('13', '2015-04-30 16:39:01', '0', 'add', 'cluster', ' 跳转到添加页面', '13', null, '3', '/admin/cluster/add');
INSERT INTO `lt_permission2` VALUES ('14', '2015-04-30 16:39:45', '0', 'edit', 'cluster', '跳转到编辑页面', '14', null, '3', '/admin/cluster/edit');
INSERT INTO `lt_permission2` VALUES ('15', '2015-04-30 16:40:28', '0', 'save', 'cluster', '保存集群实体', '15', null, '3', '/admin/cluster/save');
INSERT INTO `lt_permission2` VALUES ('16', '2015-04-30 16:40:59', '0', 'update', 'cluster', '更新集群实体', '16', null, '3', '/admin/cluster/update');
INSERT INTO `lt_permission2` VALUES ('17', '2015-04-30 16:42:03', '0', 'delete', 'cluster', '删除集群实体', '17', null, '3', '/admin/cluster/delete');
INSERT INTO `lt_permission2` VALUES ('18', '2015-04-30 16:42:44', '0', 'deletes', 'cluster', '删除多个集群实体', '18', null, '3', '/admin/cluster/deletes');
INSERT INTO `lt_permission2` VALUES ('19', '2015-04-30 16:44:21', '0', 'list', 'department', '查询所有的部门信息', '19', '2015-05-04 11:01:41', '4', '/admin/department/list');
INSERT INTO `lt_permission2` VALUES ('20', '2015-04-30 16:47:57', '0', 'loadDepartment/{cid}', 'department', 'load加载数据', '20', '2015-05-04 18:44:05', '4', '/admin/department/loadDepartment');
INSERT INTO `lt_permission2` VALUES ('21', '2015-04-30 16:48:40', '0', 'countDepartment', 'department', '求总数', '21', null, '4', '/admin/department/countDepartment');
INSERT INTO `lt_permission2` VALUES ('22', '2015-04-30 16:49:23', '0', 'listTemplate', 'department', '右上角查询', '22', '2015-05-05 18:35:14', '4', '/admin/department/listTemplate');
INSERT INTO `lt_permission2` VALUES ('23', '2015-04-30 16:49:58', '0', 'add', 'department', '跳转到添加页面', '23', null, '4', '/admin/department/add');
INSERT INTO `lt_permission2` VALUES ('24', '2015-04-30 16:51:03', '0', 'edit', 'department', ' 跳转到编辑页面', '24', null, '4', '/admin/department/edit');
INSERT INTO `lt_permission2` VALUES ('25', '2015-04-30 16:51:17', '0', 'save', 'department', '保存部门实体', '25', null, '4', '/admin/department/save');
INSERT INTO `lt_permission2` VALUES ('26', '2015-04-30 16:52:00', '0', 'update', 'department', '更新部门实体', '26', null, '4', '/admin/department/update');
INSERT INTO `lt_permission2` VALUES ('27', '2015-04-30 16:52:33', '0', 'delete', 'department', '删除部门实体', '27', null, '4', '/admin/department/delete');
INSERT INTO `lt_permission2` VALUES ('28', '2015-04-30 16:53:27', '0', 'deletes', 'department', '删除多个集群实体', '28', null, '4', '/admin/department/deletes');
INSERT INTO `lt_permission2` VALUES ('29', '2015-04-30 16:55:36', '0', 'list', 'moonUser', '员工信息管理页面', '29', '2015-05-04 21:23:56', '5', '/admin/moonUser/list');
INSERT INTO `lt_permission2` VALUES ('30', '2015-04-30 16:56:14', '0', 'countMoonUser', 'moonUser', '求总数', '30', null, '5', '/admin/moonUser/countMoonUser');
INSERT INTO `lt_permission2` VALUES ('31', '2015-04-30 16:57:00', '0', 'listTemplate', 'moonUser', '表格右上角查询权限', '31', '2015-05-04 21:19:17', '5', '/admin/moonUser/listTemplate');
INSERT INTO `lt_permission2` VALUES ('32', '2015-04-30 16:57:46', '0', 'ajaxTemplate', 'moonUser', '查询消息', '32', null, '5', '/admin/moonUser/ajaxTemplate');
INSERT INTO `lt_permission2` VALUES ('33', '2015-04-30 16:58:36', '0', 'ajaxTemplate2', 'moonUser', '查询权限', '33', '2015-05-04 22:04:52', '5', '/admin/moonUser/ajaxTemplates');
INSERT INTO `lt_permission2` VALUES ('34', '2015-04-30 16:59:07', '0', 'add', 'moonUser', '员工信息录入页面', '34', '2015-05-04 21:24:26', '5', '/admin/moonUser/add');
INSERT INTO `lt_permission2` VALUES ('35', '2015-04-30 16:59:59', '0', 'edit', 'moonUser', '员工信息修改页面', '35', '2015-05-04 21:24:41', '5', '/admin/moonUser/edit');
INSERT INTO `lt_permission2` VALUES ('36', '2015-04-30 17:01:36', '0', 'save', 'moonUser', '保存潭州学院员工管理实体', '36', null, '5', '/admin/moonUser/save');
INSERT INTO `lt_permission2` VALUES ('37', '2015-04-30 17:01:49', '0', 'update', 'moonUser', '更新潭州学院员工管理实体', '37', null, '5', '/admin/moonUser/update');
INSERT INTO `lt_permission2` VALUES ('38', '2015-04-30 17:02:22', '0', 'delete', 'moonUser', '删除潭州学院员工管理实体', '38', null, '5', '/admin/moonUser/delete');
INSERT INTO `lt_permission2` VALUES ('39', '2015-04-30 17:02:55', '0', 'deletes', 'moonUser', '删除多个潭州学院员工', '39', '2015-05-04 18:45:04', '5', '/admin/moonUser/deletes');
INSERT INTO `lt_permission2` VALUES ('40', '2015-04-30 17:05:07', '0', 'list', 'serviceDepartment', '部门服务管理统计', '40', '2015-05-04 21:29:01', '6', '/admin/serviceDepartment/list');
INSERT INTO `lt_permission2` VALUES ('41', '2015-04-30 17:05:44', '0', 'countDepartmentService', 'serviceDepartment', '求总数', '41', null, '6', '/admin/serviceDepartment/countDepartmentService');
INSERT INTO `lt_permission2` VALUES ('42', '2015-04-30 17:06:19', '0', 'listTemplate', 'serviceDepartment', '部门服务列表右上角查询权限', '42', '2015-05-04 21:39:51', '6', '/admin/serviceDepartment/listTemplate');
INSERT INTO `lt_permission2` VALUES ('43', '2015-04-30 17:07:14', '0', 'add', 'serviceDepartment', '部门服务录入页面', '43', '2015-05-04 21:30:02', '6', '/admin/serviceDepartment/add');
INSERT INTO `lt_permission2` VALUES ('44', '2015-04-30 17:07:49', '0', 'edit', 'serviceDepartment', '部门服务修改页面', '44', '2015-05-04 21:30:44', '6', '/admin/serviceDepartment/edit');
INSERT INTO `lt_permission2` VALUES ('45', '2015-04-30 17:08:25', '0', 'save', 'serviceDepartment', '保存部门服务录入实体', '45', null, '6', '/admin/serviceDepartment/save');
INSERT INTO `lt_permission2` VALUES ('46', '2015-04-30 17:09:00', '0', 'update', 'serviceDepartment', '更新部门服务录入实体', '46', null, '6', '/admin/serviceDepartment/update');
INSERT INTO `lt_permission2` VALUES ('47', '2015-04-30 17:09:36', '0', 'delete', 'serviceDepartment', '部门服务删除', '47', '2015-05-04 21:31:16', '6', '/admin/serviceDepartment/delete');
INSERT INTO `lt_permission2` VALUES ('48', '2015-04-30 17:10:19', '0', 'deletes', 'serviceDepartment', '删除多个部门服务录入', '48', '2015-05-04 21:31:25', '6', '/admin/serviceDepartment/deletes');
INSERT INTO `lt_permission2` VALUES ('49', '2015-04-30 17:11:16', '0', 'list', 'serviceItem', '服务项管理', '49', '2015-05-04 21:31:59', '6', '/admin/serviceItem/list');
INSERT INTO `lt_permission2` VALUES ('50', '2015-04-30 17:11:45', '0', 'countServiceItem', 'serviceItem', '求总数', '50', null, '6', '/admin/serviceItem/countServiceItem');
INSERT INTO `lt_permission2` VALUES ('51', '2015-04-30 17:12:17', '0', 'listTemplate', 'serviceItem', '服务项管理列表右上角查询', '51', '2015-05-04 21:32:57', '6', '/admin/serviceItem/listTemplate');
INSERT INTO `lt_permission2` VALUES ('52', '2015-04-30 17:12:46', '0', 'add', 'serviceItem', '服务项录入页面', '52', '2015-05-04 21:33:22', '6', '/admin/serviceItem/add');
INSERT INTO `lt_permission2` VALUES ('53', '2015-04-30 17:13:24', '0', 'edit', 'serviceItem', '服务项修改页面', '53', '2015-05-04 21:33:34', '6', '/admin/serviceItem/edit');
INSERT INTO `lt_permission2` VALUES ('54', '2015-04-30 17:13:56', '0', 'save', 'serviceItem', '保存服务项目实体', '54', null, '6', '/admin/serviceItem/save');
INSERT INTO `lt_permission2` VALUES ('55', '2015-04-30 17:14:24', '0', 'update', 'serviceItem', '更新服务项目实体', '55', null, '6', '/admin/serviceItem/update');
INSERT INTO `lt_permission2` VALUES ('56', '2015-04-30 17:14:55', '0', 'delete', 'serviceItem', '删除服务项目', '56', '2015-05-04 21:33:47', '6', '/admin/serviceItem/delete');
INSERT INTO `lt_permission2` VALUES ('57', '2015-04-30 17:15:33', '0', 'deletes', 'serviceItem', '删除多个服务项', '57', '2015-05-04 21:33:54', '6', '/admin/serviceItem/deletes');
INSERT INTO `lt_permission2` VALUES ('58', '2015-04-30 17:16:23', '0', 'list', 'serviceXz', '行政增值服务管理统计', '58', '2015-05-04 21:34:38', '6', '/admin/serviceXz/list');
INSERT INTO `lt_permission2` VALUES ('59', '2015-04-30 17:17:01', '0', 'countServiceXz', 'serviceXz', ' 求总数', '59', null, '6', '/admin/serviceXz/countServiceXz');
INSERT INTO `lt_permission2` VALUES ('60', '2015-04-30 17:17:37', '0', 'listTemplate', 'serviceXz', '行政增值服务管理统计列表右上角查询权限', '60', '2015-05-04 21:35:55', '6', '/admin/serviceXz/listTemplate');
INSERT INTO `lt_permission2` VALUES ('61', '2015-04-30 17:18:19', '0', 'loadDepartment', 'serviceXz', '查询出部门', '61', '2015-05-04 21:36:36', '6', '/admin/serviceXz/loadDepartment');
INSERT INTO `lt_permission2` VALUES ('62', '2015-04-30 17:19:03', '0', 'add', 'serviceXz', '行政增值服务录入页面', '62', '2015-05-04 21:37:02', '6', '/admin/serviceXz/add');
INSERT INTO `lt_permission2` VALUES ('63', '2015-04-30 17:19:33', '0', 'edit', 'serviceXz', '行政增值服务修改页面', '63', '2015-05-04 21:37:13', '6', '/admin/serviceXz/edit');
INSERT INTO `lt_permission2` VALUES ('64', '2015-04-30 17:20:14', '0', 'save', 'serviceXz', '保存行政增值服务实体', '64', null, '6', '/admin/serviceXz/save');
INSERT INTO `lt_permission2` VALUES ('65', '2015-04-30 17:20:41', '0', 'update', 'serviceXz', '更新行政增值服务实体', '65', null, '6', '/admin/serviceXz/update');
INSERT INTO `lt_permission2` VALUES ('66', '2015-04-30 17:21:17', '0', 'delete', 'serviceXz', '删除行政增值服务', '66', '2015-05-04 21:37:23', '6', '/admin/serviceXz/delete');
INSERT INTO `lt_permission2` VALUES ('67', '2015-04-30 17:21:44', '0', 'loadMoney', 'serviceXz', '加载服务费用', '67', null, '6', '/admin/serviceXz/loadMoney');
INSERT INTO `lt_permission2` VALUES ('68', '2015-04-30 17:22:16', '0', 'deletes', 'serviceXz', '删除多个行政增值服务', '68', '2015-05-04 21:37:36', '6', '/admin/serviceXz/deletes');
INSERT INTO `lt_permission2` VALUES ('69', '2015-04-30 17:23:24', '0', 'list', 'performance', '业绩分成比例管理信息', '69', '2015-05-05 10:22:51', '6', '/admin/performance/list');
INSERT INTO `lt_permission2` VALUES ('70', '2015-04-30 17:24:13', '0', 'countPerformance', 'performance', ' 求总数', '70', null, '7', '/admin/performance/countPerformance');
INSERT INTO `lt_permission2` VALUES ('71', '2015-04-30 17:24:44', '0', 'listTemplate', 'performance', '部门业绩分成比例管理列表右上角查询权限', '71', '2015-05-04 21:41:01', '7', '/admin/performance/listTemplate');
INSERT INTO `lt_permission2` VALUES ('72', '2015-04-30 17:27:14', '0', 'add', 'performance', '部门业绩分成比例录入页面', '72', '2015-05-04 21:41:23', '7', '/admin/performance/add');
INSERT INTO `lt_permission2` VALUES ('73', '2015-04-30 17:27:47', '0', 'edit', 'performance', '部门业绩分成比例修改页面', '73', '2015-05-04 21:41:32', '7', '/admin/performance/edit');
INSERT INTO `lt_permission2` VALUES ('74', '2015-04-30 17:28:18', '0', 'save', 'performance', '保存业绩分成比例管理实体', '74', null, '7', '/admin/performance/save');
INSERT INTO `lt_permission2` VALUES ('75', '2015-04-30 17:28:49', '0', 'update', 'performance', '更新业绩分成比例管理实体', '75', null, '7', '/admin/performance/update');
INSERT INTO `lt_permission2` VALUES ('76', '2015-04-30 17:29:16', '0', 'delete', 'performance', '删除单个业绩分成比例管理', '76', '2015-05-04 21:41:51', '7', '/admin/performance/delete');
INSERT INTO `lt_permission2` VALUES ('77', '2015-04-30 17:29:49', '0', 'deleteitem', 'performance', ' 删除分成比例项', '77', '2015-05-04 21:42:20', '7', '/admin/performance/deleteitem');
INSERT INTO `lt_permission2` VALUES ('78', '2015-04-30 17:30:18', '0', 'deletes', 'performance', '删除多个业绩分成比例管理', '78', '2015-05-04 21:42:25', '7', '/admin/performance/deletes');
INSERT INTO `lt_permission2` VALUES ('79', '2015-04-30 17:32:58', '0', 'list', 'vipuser', 'VIP学员列表', '79', '2015-05-04 21:42:48', '8', '/admin/vipuser/list');
INSERT INTO `lt_permission2` VALUES ('80', '2015-04-30 17:33:26', '0', 'countUser', 'vipuser', '求总数', '80', null, '8', '/admin/vipuser/countUser');
INSERT INTO `lt_permission2` VALUES ('81', '2015-04-30 17:33:59', '0', 'listTemplate', 'vipuser', 'VIP学员列表右上角查询权限', '81', '2015-05-04 21:43:04', '8', '/admin/vipuser/listTemplate');
INSERT INTO `lt_permission2` VALUES ('82', '2015-04-30 17:34:34', '0', 'add', 'vipuser', 'VIP学员录入', '82', '2015-05-04 21:43:18', '8', '/admin/vipuser/add');
INSERT INTO `lt_permission2` VALUES ('83', '2015-04-30 17:35:10', '0', 'edit', 'vipuser', 'VIP学员修改页面', '83', '2015-05-04 21:43:32', '8', '/admin/vipuser/edit');
INSERT INTO `lt_permission2` VALUES ('84', '2015-04-30 17:35:44', '0', 'save', 'vipuser', '保存学员信息管理实体', '84', null, '8', '/admin/vipuser/save');
INSERT INTO `lt_permission2` VALUES ('85', '2015-04-30 17:36:12', '0', 'update', 'vipuser', '更新学员信息管理实体', '85', null, '8', '/admin/vipuser/update');
INSERT INTO `lt_permission2` VALUES ('86', '2015-04-30 17:36:41', '0', 'delete', 'vipuser', '删除VIP学员信息', '86', '2015-05-04 21:43:46', '8', '/admin/vipuser/delete');
INSERT INTO `lt_permission2` VALUES ('87', '2015-04-30 17:37:15', '0', 'deletes', 'vipuser', '删除多个VIP学员信息', '87', '2015-05-04 21:43:57', '8', '/admin/vipuser/deletes');
INSERT INTO `lt_permission2` VALUES ('88', '2015-04-30 17:38:06', '0', 'excel', 'vipuser', '导出VIP学员信息exce', '88', '2015-05-04 21:44:13', '8', '/admin/vipuser/excel');
INSERT INTO `lt_permission2` VALUES ('89', '2015-04-30 17:40:04', '0', 'excel', 'moonUser', '导出潭州学院员工excel', '89', '2015-05-04 18:45:26', '5', '/admin/moonUser/excel');
INSERT INTO `lt_permission2` VALUES ('90', '2015-04-30 18:10:33', '0', 'list', 'income', '虹之玉-订单统计分析', '90', '2015-05-04 21:44:51', '9', '/admin/income/list');
INSERT INTO `lt_permission2` VALUES ('91', '2015-04-30 18:11:14', '0', 'countincome', 'income', '支付方式查看', '91', null, '9', '/admin/income/countincome');
INSERT INTO `lt_permission2` VALUES ('92', '2015-04-30 18:12:01', '0', 'countpaytype', 'income', '成交方式', '92', null, '9', '/admin/income/countpaytype');
INSERT INTO `lt_permission2` VALUES ('93', '2015-04-30 18:12:32', '0', 'year', 'income', '虹之玉-虹之玉整体排名', '93', '2015-05-04 21:47:46', '9', '/admin/income/year');
INSERT INTO `lt_permission2` VALUES ('94', '2015-04-30 18:13:19', '0', 'yearcont', 'income', '查询方法', '94', null, '9', '/admin/income/yearcont');
INSERT INTO `lt_permission2` VALUES ('95', '2015-04-30 18:13:49', '0', 'datcont', 'income', '数据返回', '95', null, '9', '/admin/income/datcont');
INSERT INTO `lt_permission2` VALUES ('96', '2015-04-30 18:15:14', '0', 'list', 'statuser', '虹之玉-学员信息统计', '96', '2015-05-04 21:48:37', '9', '/admin/statuser/list');
INSERT INTO `lt_permission2` VALUES ('97', '2015-04-30 18:15:42', '0', 'listchart', 'statuser', '虹之玉-学员信息统计列表右上角查询权限', '97', '2015-05-04 21:49:11', '9', '/admin/statuser/listchart');
INSERT INTO `lt_permission2` VALUES ('98', '2015-04-30 18:16:12', '0', 'userAge', 'statuser', '年龄分组', '98', null, '9', '/admin/statuser/userAge');
INSERT INTO `lt_permission2` VALUES ('99', '2015-04-30 18:16:43', '0', 'userStatus', 'statuser', '状态分组', '99', null, '9', '/admin/statuser/userStatus');
INSERT INTO `lt_permission2` VALUES ('100', '2015-04-30 18:17:22', '0', 'userStage', 'statuser', '阶段分组', '100', null, '9', '/admin/statuser/userStage');
INSERT INTO `lt_permission2` VALUES ('101', '2015-04-30 18:17:59', '0', 'countRole', 'statuser', '虹之玉-部门虹之玉统计明细', '101', '2015-05-04 21:49:48', '9', '/admin/statuser/countRole');
INSERT INTO `lt_permission2` VALUES ('102', '2015-04-30 18:18:25', '0', 'countRoleData', 'statuser', '统计角色的排行2', '102', null, '9', '/admin/statuser/countRoleData');
INSERT INTO `lt_permission2` VALUES ('103', '2015-04-30 18:18:52', '0', 'findRoleData', 'statuser', '统计角色的排行3', '103', null, '9', '/admin/statuser/findRoleData');
INSERT INTO `lt_permission2` VALUES ('104', '2015-04-30 18:19:22', '0', 'countMoonUser', 'statuser', '虹之玉-个人虹之玉排名', '104', '2015-05-04 21:50:52', '9', '/admin/statuser/countMoonUser');
INSERT INTO `lt_permission2` VALUES ('105', '2015-04-30 18:19:49', '0', 'countMoonUserData', 'statuser', '统计用户的排行2', '105', null, '9', '/admin/statuser/countMoonUserData');
INSERT INTO `lt_permission2` VALUES ('106', '2015-04-30 18:20:18', '0', 'getMoonUser', 'statuser', '统计用户的排行3', '106', null, '9', '/admin/statuser/getMoonUser');
INSERT INTO `lt_permission2` VALUES ('107', '2015-04-30 18:20:44', '0', 'countDepartment', 'statuser', '虹之玉-部门虹之玉排名', '107', '2015-05-04 21:51:42', '9', '/admin/statuser/countDepartment');
INSERT INTO `lt_permission2` VALUES ('108', '2015-04-30 18:21:15', '0', 'countDepartmentData', 'statuser', '统计用户的排行5', '108', null, '9', '/admin/statuser/countDepartmentData');
INSERT INTO `lt_permission2` VALUES ('109', '2015-04-30 18:21:43', '0', 'countRealTime', 'statuser', '事实统计当天的的成交情况', '109', null, '9', '/admin/statuser/countRealTime');
INSERT INTO `lt_permission2` VALUES ('110', '2015-05-03 15:50:05', '0', 'list', 'export', '导出个人虹之玉', '110', null, '1', '/admin/export/list');
INSERT INTO `lt_permission2` VALUES ('111', '2015-05-03 21:31:28', '0', 'permission', 'channel', '权限', '111', null, '1', '/admin/permission/channel');
INSERT INTO `lt_permission2` VALUES ('112', '2015-05-04 10:13:11', '0', 'console', 'export', '导出操作', '112', null, '110', '/admin/export/console');
INSERT INTO `lt_permission2` VALUES ('113', '2015-05-04 10:14:16', '0', 'export', 'export', '导出ajax', '113', null, '110', '/admin/export/export');
INSERT INTO `lt_permission2` VALUES ('114', '2015-05-04 10:24:58', '0', 'role', 'role', '角色管理', '114', null, '1', '/admin/role/channel');
INSERT INTO `lt_permission2` VALUES ('122', '2015-05-04 17:04:00', '0', 'add', 'role', '虹之玉角色录入页面', '12', '2015-05-04 21:53:47', '114', '/admin/role/add');
INSERT INTO `lt_permission2` VALUES ('123', '2015-05-04 17:16:42', '0', 'list', 'role', '虹之玉角色管理', '133', '2015-05-04 21:53:16', '114', '/admin/role/list');
INSERT INTO `lt_permission2` VALUES ('124', '2015-05-04 18:38:48', '0', 'edit', 'role', '虹之玉角色修改页面', '14', '2015-05-04 21:53:35', '114', '/admin/role/edit');
INSERT INTO `lt_permission2` VALUES ('125', '2015-05-05 10:23:32', '0', 'list', 'performance', '业绩分成比例管理', '69', null, '7', '/admin/performance/list');
INSERT INTO `lt_permission2` VALUES ('126', '2015-05-05 19:30:52', '0', 'list', 'orderList', '查询所有学员订单订单列表', '100', null, '8', '/admin/orderList/list');
INSERT INTO `lt_permission2` VALUES ('127', '2015-05-05 19:33:14', '0', 'countOrderList', 'orderList', '查询订单总数', '101', null, '8', '/admin/orderList/countOrderList');
INSERT INTO `lt_permission2` VALUES ('128', '2015-05-05 19:33:53', '0', 'listTemplate', 'orderList', '右上角查询权限', '102', null, '8', '/admin/orderList/listTemplate');
INSERT INTO `lt_permission2` VALUES ('129', '2015-05-05 19:34:36', '0', 'add', 'orderList', '学员订单录入页面', '103', null, '8', '/admin/orderList/add');
INSERT INTO `lt_permission2` VALUES ('130', '2015-05-05 19:35:15', '0', 'edit', 'orderList', '学员订单修改页面', '104', null, '8', '/admin/orderList/edit');
INSERT INTO `lt_permission2` VALUES ('131', '2015-05-05 19:35:53', '0', 'save', 'orderList', '学员订单保存', '105', null, '8', '/admin/orderList/save');
INSERT INTO `lt_permission2` VALUES ('132', '2015-05-05 19:36:24', '0', 'update', 'orderList', '学员订单修改', '106', null, '8', '/admin/orderList/update');
INSERT INTO `lt_permission2` VALUES ('133', '2015-05-05 19:36:53', '0', 'delete', 'orderList', '学员订单删除', '107', null, '8', '/admin/orderList/delete');
INSERT INTO `lt_permission2` VALUES ('134', '2015-05-05 19:37:25', '0', 'deletes', 'orderList', '删除多个学员订单', '1000', null, '8', '/admin/orderList/deletes');
INSERT INTO `lt_permission2` VALUES ('135', '2015-05-06 11:34:20', '0', 'list', 'permission', '权限列表', '120', null, '111', '/admin/permission/list');
INSERT INTO `lt_permission2` VALUES ('136', '2015-05-06 11:36:30', '0', 'countPermission', 'permission', '求总数', '121', null, '111', '/admin/permission/countPermission');
INSERT INTO `lt_permission2` VALUES ('137', '2015-05-06 11:37:43', '0', 'listTemplate', 'permission', '查询下拉', '122', null, '111', '/admin/permission/listTemplat');
INSERT INTO `lt_permission2` VALUES ('138', '2015-05-06 11:38:27', '0', 'ajaxTemplate', 'permission', ' 查询消息', '123', null, '111', '/admin/permission/ajaxTemplate');
INSERT INTO `lt_permission2` VALUES ('139', '2015-05-06 13:11:30', '0', 'tree', 'permission', 'tree', '124', null, '111', '/admin/permission/tree');
INSERT INTO `lt_permission2` VALUES ('140', '2015-05-06 13:13:53', '0', 'edit', 'permission', '修改页面', '125', null, '111', '/admin/permission/edit');
INSERT INTO `lt_permission2` VALUES ('141', '2015-05-06 13:14:22', '0', 'save', 'permission', '保存权限', '126', null, '111', '/admin/permission/save');
INSERT INTO `lt_permission2` VALUES ('142', '2015-05-06 13:14:49', '0', 'update', 'permission', '修改权限', '127', null, '111', '/admin/permission/update');
INSERT INTO `lt_permission2` VALUES ('143', '2015-05-06 13:15:58', '0', 'delete', 'permission', '删除权限', '128', null, '111', '/admin/permission/delete');
INSERT INTO `lt_permission2` VALUES ('144', '2015-05-06 13:16:16', '0', 'index', 'interview', '访谈管理', '128', null, '1', '/admin/interview/index');
INSERT INTO `lt_permission2` VALUES ('146', '2015-05-06 13:16:26', '0', 'list', 'interview', '访谈列表', '129', null, '144', '/admin/interview/list');
INSERT INTO `lt_permission2` VALUES ('147', '2015-05-06 13:26:59', '0', 'listTemplate', 'interview', '访谈列表模板', '130', null, '144', '/admin/interview/listTemplate');
INSERT INTO `lt_permission2` VALUES ('148', '2015-05-06 13:27:58', '0', 'add', 'interview', '访谈添加', '131', null, '144', '/admin/interview/add');
INSERT INTO `lt_permission2` VALUES ('149', '2015-05-06 13:28:15', '0', 'save', 'interview', '访谈保存', '132', null, '144', '/admin/interview/save');
INSERT INTO `lt_permission2` VALUES ('150', '2015-05-06 13:29:14', '0', 'countRole', 'role', '求总数', '130', null, '114', '/admin/role/countRole');
INSERT INTO `lt_permission2` VALUES ('151', '2015-05-06 13:30:00', '0', 'listTemplate', 'role', 'ajax模版', '131', null, '114', '/admin/role/listTemplate');
INSERT INTO `lt_permission2` VALUES ('152', '2015-05-06 13:31:14', '0', 'savePermission', 'role', '保存权限', '132', null, '114', '/admin/role/savePermission');
INSERT INTO `lt_permission2` VALUES ('153', '2015-05-06 13:32:11', '0', 'save', 'role', '保存角色', '133', null, '114', '/admin/role/save');
INSERT INTO `lt_permission2` VALUES ('154', '2015-05-06 13:32:46', '0', 'update', 'role', '修改角色', '134', null, '114', '/admin/role/update');
INSERT INTO `lt_permission2` VALUES ('155', '2015-05-06 13:33:14', '0', 'delete', 'role', '删除角色', '135', null, '114', '/admin/role/delete');
INSERT INTO `lt_permission2` VALUES ('156', '2015-05-06 13:34:12', '0', 'deletes', 'role', '删除多个', '136', null, '114', '/admin/role/deletes');
INSERT INTO `lt_permission2` VALUES ('157', '2015-05-06 13:34:49', '0', 'count', 'role', '统计总数', '137', null, '114', '/admin/role/count');
INSERT INTO `lt_permission2` VALUES ('158', '2015-05-06 13:35:17', '0', 'role_pemission', 'role', '查询角色信息统计', '138', null, '114', '/admin/role/role_pemission');
INSERT INTO `lt_permission2` VALUES ('159', '2015-05-06 13:35:52', '0', 'role_user', 'role', '用户角色统计', '139', null, '114', '/admin/role/role_user');
INSERT INTO `lt_permission2` VALUES ('160', '2015-05-06 16:50:13', '0', 'delete', 'interview', '删除访谈内容', '140', null, '144', '/admin/interview/delete');

-- ----------------------------
-- Table structure for lt_role
-- ----------------------------
DROP TABLE IF EXISTS `lt_role`;
CREATE TABLE `lt_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` longtext,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_role
-- ----------------------------
INSERT INTO `lt_role` VALUES ('1', '2015-04-30 21:23:45', '超级管理员', '超级管理员');
INSERT INTO `lt_role` VALUES ('2', '2015-04-30 21:24:06', '管理员', '管理员');
INSERT INTO `lt_role` VALUES ('3', '2015-04-30 21:24:06', '班长', '班长');
INSERT INTO `lt_role` VALUES ('4', '2015-04-30 21:24:06', '纪律委员', '纪律委员');

-- ----------------------------
-- Table structure for lt_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `lt_role_permission`;
CREATE TABLE `lt_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  KEY `FK_acmhd0x4e2b8atoogj59mfs0a` (`permission_id`),
  KEY `FK_aehwxu0cqe74cb1eg68a81m6d` (`role_id`),
  CONSTRAINT `FK_acmhd0x4e2b8atoogj59mfs0a` FOREIGN KEY (`permission_id`) REFERENCES `lt_permission` (`id`),
  CONSTRAINT `FK_aehwxu0cqe74cb1eg68a81m6d` FOREIGN KEY (`role_id`) REFERENCES `lt_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_role_permission
-- ----------------------------
INSERT INTO `lt_role_permission` VALUES ('1', '2');
INSERT INTO `lt_role_permission` VALUES ('1', '3');
INSERT INTO `lt_role_permission` VALUES ('1', '4');
INSERT INTO `lt_role_permission` VALUES ('2', '2');
INSERT INTO `lt_role_permission` VALUES ('3', '3');
INSERT INTO `lt_role_permission` VALUES ('1', '6');
INSERT INTO `lt_role_permission` VALUES ('3', '6');
INSERT INTO `lt_role_permission` VALUES ('1', '5');
INSERT INTO `lt_role_permission` VALUES ('3', '7');
INSERT INTO `lt_role_permission` VALUES ('4', '6');

-- ----------------------------
-- Table structure for lt_role_user
-- ----------------------------
DROP TABLE IF EXISTS `lt_role_user`;
CREATE TABLE `lt_role_user` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FK_ce6rh7gd2ds88j05muop6ud9u` (`role_id`),
  KEY `FK_21sqkl2ca0refragxmswl02pl` (`user_id`),
  CONSTRAINT `FK_21sqkl2ca0refragxmswl02pl` FOREIGN KEY (`user_id`) REFERENCES `lt_user` (`id`),
  CONSTRAINT `FK_ce6rh7gd2ds88j05muop6ud9u` FOREIGN KEY (`role_id`) REFERENCES `lt_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_role_user
-- ----------------------------
INSERT INTO `lt_role_user` VALUES ('1', '1');
INSERT INTO `lt_role_user` VALUES ('1', '3');
INSERT INTO `lt_role_user` VALUES ('2', '4');
INSERT INTO `lt_role_user` VALUES ('2', '3');

-- ----------------------------
-- Table structure for lt_student
-- ----------------------------
DROP TABLE IF EXISTS `lt_student`;
CREATE TABLE `lt_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1hblx4oua3tho6nt2ri6c3la` (`user_id`),
  CONSTRAINT `FK_b1hblx4oua3tho6nt2ri6c3la` FOREIGN KEY (`user_id`) REFERENCES `lt_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_student
-- ----------------------------
INSERT INTO `lt_student` VALUES ('2', '2015-05-06 00:37:21', '0', 'keke', '1', null, null);
INSERT INTO `lt_student` VALUES ('3', '2015-05-06 00:57:22', '0', 'keke', '1', null, null);

-- ----------------------------
-- Table structure for lt_teacher
-- ----------------------------
DROP TABLE IF EXISTS `lt_teacher`;
CREATE TABLE `lt_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cxgojvidsr9bx7dyx5j2trnp2` (`user_id`),
  CONSTRAINT `FK_cxgojvidsr9bx7dyx5j2trnp2` FOREIGN KEY (`user_id`) REFERENCES `lt_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_teacher
-- ----------------------------
INSERT INTO `lt_teacher` VALUES ('2', '2015-05-06 00:46:19', '0', 'keke', '1', null, null);

-- ----------------------------
-- Table structure for lt_user
-- ----------------------------
DROP TABLE IF EXISTS `lt_user`;
CREATE TABLE `lt_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(100) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(80) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lt_user
-- ----------------------------
INSERT INTO `lt_user` VALUES ('1', 'keke', '2015-04-29 00:13:16', '4QrcOUm6Wau+VuBX8g+IPg==', '柯柯');
INSERT INTO `lt_user` VALUES ('2', 'xiaowei', '2015-04-29 00:13:16', '4QrcOUm6Wau+VuBX8g+IPg==', '小威');
