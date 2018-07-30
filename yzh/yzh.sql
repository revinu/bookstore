/*
Navicat MySQL Data Transfer

Source Server         : native
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : yzh

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-04-22 23:30:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `keyV` smallint(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `sort` varchar(255) NOT NULL DEFAULT '0',
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type`,`keyV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('2d35ec99-456d-11e8-b9a6-00ffd5681a63', 'category', '0', '其它', '99', null);
INSERT INTO `sys_dict` VALUES ('5089673c-456d-11e8-b9a6-00ffd5681a63', 'category', '1', '科学类', '0', null);
INSERT INTO `sys_dict` VALUES ('596594f1-456d-11e8-b9a6-00ffd5681a63', 'category', '2', '生物学类', '0', null);
INSERT INTO `sys_dict` VALUES ('60e0dd6a-456d-11e8-b9a6-00ffd5681a63', 'category', '3', '文学类', '0', null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `head_image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` int(255) DEFAULT '0',
  `mobile` varchar(255) DEFAULT NULL,
  `type` int(6) DEFAULT '1',
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', null, 'admin', '0', '13663366337', '1');

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `shelves_time` datetime NOT NULL COMMENT '上架时间',
  `publish_date` date DEFAULT NULL COMMENT '发布时间',
  `author` varchar(255) DEFAULT NULL COMMENT '作者',
  `is_hot` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否热门  0：否  1:是',
  `is_shelves` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否上架  0：否  1：是',
  `category` smallint(255) NOT NULL DEFAULT '0' COMMENT '类别ID，0：其它',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('29fa6730330c44ee898afdacf89889a1', '6', '6', '/image/book/1/29fa6730330c44ee898afdacf89889a1/6.jpg', '2018-04-22 20:59:30', '2015-04-17', '6', '', '', '3');
INSERT INTO `t_book` VALUES ('4004dd6cc9d14bf28d52cde3e9f9938f', '5', '5', '/image/book/1/4004dd6cc9d14bf28d52cde3e9f9938f/5.jpg', '2018-04-22 20:59:14', '2018-04-08', '5', '', '', '0');
INSERT INTO `t_book` VALUES ('43fde038c60249e0a4892690e9d5d879', '3', '3', '/image/book/1/43fde038c60249e0a4892690e9d5d879/3.jpg', '2018-04-22 20:58:45', '2018-04-10', '3', '', '', '2');
INSERT INTO `t_book` VALUES ('472e9e96007349ffa9c5bcdac7ec36e1', '7', '7', '/image/book/1/472e9e96007349ffa9c5bcdac7ec36e1/7.jpg', '2018-04-22 20:59:51', '2018-02-13', '7', '', '', '3');
INSERT INTO `t_book` VALUES ('76e4258f575846eb98c320164a34bb26', '1', '1', '/image/book/1/76e4258f575846eb98c320164a34bb26/1.jpg', '2018-04-22 20:58:09', '2018-04-02', '1', '', '', '2');
INSERT INTO `t_book` VALUES ('77419d55c12b44de823c3136d9ce6db2', '8', '8', '/image/book/1/77419d55c12b44de823c3136d9ce6db2/8.jpg', '2018-04-22 21:00:03', '2018-04-17', '8', '', '', '1');
INSERT INTO `t_book` VALUES ('b8c36bd8e0644b7bb23eb7df9ce0531f', '2', '2', '/image/book/1/b8c36bd8e0644b7bb23eb7df9ce0531f/2.jpg', '2018-04-22 20:58:29', '2018-04-02', '2', '', '', '2');
INSERT INTO `t_book` VALUES ('f6dc26f708254ba4a1ffcca42be4bd81', '4', '4', '/image/book/1/f6dc26f708254ba4a1ffcca42be4bd81/4.jpg', '2018-04-22 20:58:58', '2018-04-17', '4', '', '', '3');
INSERT INTO `t_book` VALUES ('f94d58c077ca4b6f98f554a075ba2c74', '9', '9', '/image/book/1/f94d58c077ca4b6f98f554a075ba2c74/9.jpg', '2018-04-22 21:00:20', '2018-04-22', '9', '\0', '\0', '1');

-- ----------------------------
-- Table structure for t_item
-- ----------------------------
DROP TABLE IF EXISTS `t_item`;
CREATE TABLE `t_item` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) NOT NULL,
  `book_id` varchar(255) NOT NULL,
  `book_price` decimal(65,0) DEFAULT '0',
  `num` int(11) NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `type` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_item
-- ----------------------------
INSERT INTO `t_item` VALUES ('2b7cbe48-463d-11e8-9f3e-00ffd5681a63', '1', '472e9e96007349ffa9c5bcdac7ec36e1', null, '1', '2018-04-22 22:55:17', '\0');
INSERT INTO `t_item` VALUES ('2ee0fcce-463d-11e8-9f3e-00ffd5681a63', '1', 'f94d58c077ca4b6f98f554a075ba2c74', null, '1', '2018-04-22 22:55:23', '\0');

-- ----------------------------
-- Table structure for t_linkinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_linkinfo`;
CREATE TABLE `t_linkinfo` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_linkinfo
-- ----------------------------
INSERT INTO `t_linkinfo` VALUES ('052ac095-4636-11e8-9f3e-00ffd5681a63', '1', '1', '111111', '13611111111', '1', '2018-04-22 22:04:06');
INSERT INTO `t_linkinfo` VALUES ('12cc2e02-4636-11e8-9f3e-00ffd5681a63', '1', '2', '222222', '13622222222', '2', '2018-04-22 22:04:29');
INSERT INTO `t_linkinfo` VALUES ('1a8e3062-4636-11e8-9f3e-00ffd5681a63', '1', '3', '333333', '13633333333', '3', '2018-04-22 22:04:42');
INSERT INTO `t_linkinfo` VALUES ('26c4bc95-4636-11e8-9f3e-00ffd5681a63', '1', '4', '444444', '13644444444', '4', '2018-04-22 22:05:03');
INSERT INTO `t_linkinfo` VALUES ('2d3cc102-4636-11e8-9f3e-00ffd5681a63', '1', '5', '555555', '13655555555', '5', '2018-04-22 22:05:13');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(255) NOT NULL,
  `linkinfo_id` varchar(255) NOT NULL,
  `total_price` decimal(10,0) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('d3d5793c77f742cd9fc786ee60dbba4d', '0393c681-4608-11e8-9f3e-00ffd5681a63', '6', '1', '2018-04-22 19:22:36');

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `order_id` varchar(255) NOT NULL,
  `item_id` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES ('d3d5793c77f742cd9fc786ee60dbba4d', '818ec517-45c5-11e8-b9a6-00ffd5681a63', '2018-04-22 19:22:36');
