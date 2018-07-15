/*
 Navicat Premium Data Transfer

 Source Server         : 193.112.171.113
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 193.112.171.113:3306
 Source Schema         : treasure_hunt

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 15/07/2018 13:47:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动Id',
  `customer_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '发起人Id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '活动标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '活动内容',
  `type_id` bigint(255) NOT NULL DEFAULT 0 COMMENT '活动类型',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '活动地址',
  `lng` double(100, 20) NOT NULL DEFAULT 0.00000000000000000000 COMMENT '经度',
  `lat` double(100, 20) NOT NULL DEFAULT 0.00000000000000000000 COMMENT '纬度',
  `end_time` datetime(0) NOT NULL COMMENT '结束时间',
  `qr_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '二维码',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '活动状态：0审核中，1进行中，2活动结束',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_top` tinyint(4) NOT NULL DEFAULT 0 COMMENT '置顶0否1是',
  `type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '1藏宝2帖子',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_image
-- ----------------------------
DROP TABLE IF EXISTS `activity_image`;
CREATE TABLE `activity_image`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动Id',
  `activity_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '活动Id',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片url',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_join
-- ----------------------------
DROP TABLE IF EXISTS `activity_join`;
CREATE TABLE `activity_join`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `customer_id` bigint(20) NULL DEFAULT 0 COMMENT '用户id',
  `activity_id` bigint(20) NULL DEFAULT 0 COMMENT '活动id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_like
-- ----------------------------
DROP TABLE IF EXISTS `activity_like`;
CREATE TABLE `activity_like`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `customer_id` bigint(20) NULL DEFAULT 0 COMMENT '点赞用户id',
  `activity_id` bigint(20) NULL DEFAULT 0 COMMENT '活动id',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_statistics
-- ----------------------------
DROP TABLE IF EXISTS `activity_statistics`;
CREATE TABLE `activity_statistics`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '规则id,自增主键',
  `activity_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '活动id',
  `like_num` int(10) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `view_num` int(10) NOT NULL DEFAULT 0 COMMENT '浏览数',
  `comment_num` int(10) NOT NULL DEFAULT 0 COMMENT '评论数',
  `join_num` int(10) NOT NULL DEFAULT 0 COMMENT '加入数',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '活动类型',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '活动状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for activity_type
-- ----------------------------
DROP TABLE IF EXISTS `activity_type`;
CREATE TABLE `activity_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动类型id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '类型名称',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态：0不启用，1启用',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_type
-- ----------------------------
INSERT INTO `activity_type` VALUES (1, '保密', 1, '2018-06-27 14:21:30', '2018-06-27 14:21:32');
INSERT INTO `activity_type` VALUES (2, '现金', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:43');
INSERT INTO `activity_type` VALUES (3, '卡卷', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:41');
INSERT INTO `activity_type` VALUES (4, '书籍', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:41');
INSERT INTO `activity_type` VALUES (5, '数码', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:41');
INSERT INTO `activity_type` VALUES (6, '生活', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:41');
INSERT INTO `activity_type` VALUES (7, '私藏', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:41');
INSERT INTO `activity_type` VALUES (8, '食品', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:41');
INSERT INTO `activity_type` VALUES (9, '设备', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:41');
INSERT INTO `activity_type` VALUES (10, '其他', 1, '2018-06-27 14:22:41', '2018-06-27 14:22:41');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `activity_id` bigint(20) NOT NULL COMMENT '活动id',
  `customer_id` bigint(20) NOT NULL COMMENT '评论用户id',
  `to_customer_id` bigint(20) NULL DEFAULT 0 COMMENT '被回复用户',
  `to_comment_id` bigint(20) NULL DEFAULT 0 COMMENT '被回复评论',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_best` tinyint(4) NOT NULL DEFAULT 0,
  `type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '类型1评论2回答3帖子',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息Id',
  `customer_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '发送人Id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1点赞,2评论,3审核通过',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `to_customer_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '接收人Id',
  `activity_id` bigint(20) NULL DEFAULT NULL,
  `status` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '规则id,自增主键',
  `customer_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '顾客ID',
  `comment_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '评论ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回复内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_lqjrcobrh9jc8wpcar64q1bfh`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2018-06-17 12:55:45', 'admin', '2018-06-17 12:55:47', 'admin');

-- ----------------------------
-- Table structure for wx_customer
-- ----------------------------
DROP TABLE IF EXISTS `wx_customer`;
CREATE TABLE `wx_customer`  (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像',
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '城市',
  `country` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '国家',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `gender` int(11) NOT NULL DEFAULT 1 COMMENT '性别',
  `mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '电话',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'openId',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '省份',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'unionId',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `integral` int(11) NOT NULL DEFAULT 0 COMMENT '积分',
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
