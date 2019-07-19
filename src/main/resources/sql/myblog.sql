/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 19/07/2019 09:45:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_article_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_category`;
CREATE TABLE `tbl_article_category`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` bigint(40) UNSIGNED NOT NULL,
  `category_id` bigint(40) UNSIGNED NOT NULL,
  `created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_comment`;
CREATE TABLE `tbl_article_comment`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` bigint(40) NOT NULL,
  `comment_id` bigint(40) NOT NULL,
  `created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_article_content
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_content`;
CREATE TABLE `tbl_article_content`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` bigint(40) UNSIGNED NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_article_image
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_image`;
CREATE TABLE `tbl_article_image`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` bigint(40) UNSIGNED NOT NULL,
  `image_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_article_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_article_info`;
CREATE TABLE `tbl_article_info`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `summary` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `view_times` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `is_top` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_category_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category_info`;
CREATE TABLE `tbl_category_info`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_effective` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_comment_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_comment_info`;
CREATE TABLE `tbl_comment_info`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `is_effective` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_log`;
CREATE TABLE `tbl_sys_log`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `uri` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 397 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_sys_visit
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sys_visit`;
CREATE TABLE `tbl_sys_visit`  (
  `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT,
  `visit_times` bigint(40) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
