/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : ssm_shiro

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2018-04-09 07:16:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '资源 ID',
  `name` varchar(20) NOT NULL COMMENT '资源名称,一般是中文名称(给人看的)',
  `permission` varchar(40) NOT NULL COMMENT '资源权限字符串,一般是 Shiro 默认的通配符表示(给人看的)',
  `url` varchar(40) NOT NULL COMMENT '资源 url 表示,我们设计的系统让 Shiro 通过这个路径字符串去匹配浏览器中显示的路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('1', '系统管理', 'admin:*', '/admin/**');
INSERT INTO `t_resource` VALUES ('2', '用户管理', 'user:*', '/admin/user/**');
INSERT INTO `t_resource` VALUES ('3', '用户添加', 'user:add', '/admin/user/add');
INSERT INTO `t_resource` VALUES ('4', '用户删除', 'user:delete', '/admin/user/delete');
INSERT INTO `t_resource` VALUES ('5', '用户修改', 'user:update', '/admin/user/update');
INSERT INTO `t_resource` VALUES ('6', '用户查询', 'user:list', '/admin/user/list');
INSERT INTO `t_resource` VALUES ('7', '用户资源查询', 'user:resources:*', '/admin/user/resources/*');
INSERT INTO `t_resource` VALUES ('8', '角色管理', 'role:*', '/admin/role/**');
INSERT INTO `t_resource` VALUES ('9', '角色添加', 'role:add', '/admin/role/add');
INSERT INTO `t_resource` VALUES ('10', '角色删除', 'role:delete', '/admin/role/delete');
INSERT INTO `t_resource` VALUES ('11', '角色修改', 'role:update', '/admin/role/update');
INSERT INTO `t_resource` VALUES ('12', '角色查询', 'role:list', '/admin/role/list');
INSERT INTO `t_resource` VALUES ('13', '角色资源查询', 'role:resources:*', '/admin/role/resources/*');
INSERT INTO `t_resource` VALUES ('14', '资源管理', 'resource:*', '/admin/resource/**');
INSERT INTO `t_resource` VALUES ('15', '资源增加', 'resource:add', '/admin/resource/add');
INSERT INTO `t_resource` VALUES ('16', '资源删除', 'resource:delete', '/admin/resource/delete');
INSERT INTO `t_resource` VALUES ('17', '资源修改', 'resource:update', '/admin/resource/update');
INSERT INTO `t_resource` VALUES ('18', '资源查询', 'resource:list', '/admin/resource/list');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '角色表 ID',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `sn` varchar(20) NOT NULL COMMENT '角色字符串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', 'admin');
INSERT INTO `t_role` VALUES ('2', '开发工程师', 'dev');
INSERT INTO `t_role` VALUES ('3', '测试工程师', 'test');
INSERT INTO `t_role` VALUES ('4', '文档工程师', 'doc');
INSERT INTO `t_role` VALUES ('5', 'UI设计', 'ui');
INSERT INTO `t_role` VALUES ('6', '客户', 'guest');

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '角色资源关联 ID',
  `role_id` tinyint(4) NOT NULL COMMENT '角色 id',
  `resource_id` tinyint(4) NOT NULL COMMENT '资源 id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------
INSERT INTO `t_role_resource` VALUES ('1', '1', '1');
INSERT INTO `t_role_resource` VALUES ('2', '2', '3');
INSERT INTO `t_role_resource` VALUES ('3', '2', '5');
INSERT INTO `t_role_resource` VALUES ('4', '2', '6');
INSERT INTO `t_role_resource` VALUES ('5', '2', '7');
INSERT INTO `t_role_resource` VALUES ('6', '2', '9');
INSERT INTO `t_role_resource` VALUES ('7', '2', '11');
INSERT INTO `t_role_resource` VALUES ('8', '2', '12');
INSERT INTO `t_role_resource` VALUES ('9', '2', '13');
INSERT INTO `t_role_resource` VALUES ('10', '2', '15');
INSERT INTO `t_role_resource` VALUES ('11', '2', '17');
INSERT INTO `t_role_resource` VALUES ('12', '2', '18');
INSERT INTO `t_role_resource` VALUES ('13', '3', '6');
INSERT INTO `t_role_resource` VALUES ('14', '3', '7');
INSERT INTO `t_role_resource` VALUES ('15', '3', '8');
INSERT INTO `t_role_resource` VALUES ('16', '3', '14');
INSERT INTO `t_role_resource` VALUES ('17', '4', '6');
INSERT INTO `t_role_resource` VALUES ('18', '4', '7');
INSERT INTO `t_role_resource` VALUES ('19', '4', '12');
INSERT INTO `t_role_resource` VALUES ('20', '4', '18');
INSERT INTO `t_role_resource` VALUES ('22', '5', '3');
INSERT INTO `t_role_resource` VALUES ('23', '6', '3');
INSERT INTO `t_role_resource` VALUES ('24', '6', '2');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `nickname` varchar(30) NOT NULL COMMENT '昵称',
  `status` tinyint(4) NOT NULL COMMENT '状态:1 启用,2 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'a66abb5684c45962d887564f08346e8d', '超级管理员', '1');
INSERT INTO `t_user` VALUES ('2', 'dev', '2cb919284dc284f4994fcd064ef0542b', '开发工程师', '1');
INSERT INTO `t_user` VALUES ('3', 'test', '47ec2dd791e31e2ef2076caf64ed9b3d', '测试工程师', '1');
INSERT INTO `t_user` VALUES ('4', 'doc', '6fe0f0338c688d4b139b0f9280d325a9', '文档工程师', '0');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT COMMENT '用户角色关联表 ID',
  `user_id` tinyint(4) NOT NULL,
  `role_id` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('2', '2', '2');
INSERT INTO `t_user_role` VALUES ('31', '1', '1');
INSERT INTO `t_user_role` VALUES ('33', '3', '3');
INSERT INTO `t_user_role` VALUES ('34', '3', '6');
INSERT INTO `t_user_role` VALUES ('35', '4', '5');
INSERT INTO `t_user_role` VALUES ('36', '4', '6');
