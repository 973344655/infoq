/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : infoq

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 19/06/2021 14:23:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for infoq_orders
-- ----------------------------
DROP TABLE IF EXISTS `infoq_orders`;
CREATE TABLE `infoq_orders`  (
  `id` bigint(12) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `customer_id` int(11) NOT NULL COMMENT '买家信息',
  `product_num` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `product_detail_id` bigint(12) NOT NULL COMMENT '商品详情',
  `amount` decimal(11, 2) NULL DEFAULT NULL COMMENT '总支付金额',
  `transaction_id` bigint(12) NOT NULL COMMENT '交易详情',
  `amount_detail_id` bigint(12) NOT NULL COMMENT '金额详情',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `status` tinyint(1) NOT NULL COMMENT '订单状态',
  `pay_time` bigint(13) NULL DEFAULT NULL COMMENT '付款时间',
  `delivery_time` bigint(13) NULL DEFAULT NULL COMMENT '发货时间',
  `create_time` bigint(13) NULL DEFAULT NULL,
  `update_time` bigint(13) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
