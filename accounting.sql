/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80012 (8.0.12)
 Source Host           : localhost:3306
 Source Schema         : accounting

 Target Server Type    : MySQL
 Target Server Version : 80012 (8.0.12)
 File Encoding         : 65001

 Date: 16/04/2023 23:43:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `orderId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `amount` double NULL DEFAULT NULL,
  `date` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`) USING BTREE,
  UNIQUE INDEX `orderId_UNIQUE`(`orderId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES (-2039037735, 5802175, 'yueshi', 22.21, '2023-04-11', 'this is eclipse');
INSERT INTO `order_info` VALUES (3, 2102240, 'food', 23.12, '2023-01-02', 'not bad');
INSERT INTO `order_info` VALUES (4, 2102240, 'flight', 112, '2023-01-08', 'quick');
INSERT INTO `order_info` VALUES (6, 2102240, 'rent', 1700, '2023-01-12', NULL);
INSERT INTO `order_info` VALUES (7, 3605528, 'pharmacy', 36.32, '2023-03-20', 'nono');
INSERT INTO `order_info` VALUES (8, 2102240, 'food', 49.2, '2023-02-02', 'too much');
INSERT INTO `order_info` VALUES (9, 3605528, 'food', 70.01, '2023-03-22', 'heavy');
INSERT INTO `order_info` VALUES (10, 3605528, 'pharmacy', 23.56, '2023-03-28', NULL);
INSERT INTO `order_info` VALUES (11, 2102240, 'car', 52.12, '2023-02-02', 'damn');
INSERT INTO `order_info` VALUES (12, 5802175, 'food', 18.73, '2023-01-13', 'good');
INSERT INTO `order_info` VALUES (13, 2102240, 'car', 60.1, '2023-03-10', NULL);
INSERT INTO `order_info` VALUES (14, 5802175, 'rent', 1250, '2023-01-01', NULL);
INSERT INTO `order_info` VALUES (15, 5802175, 'food', 58.66, '2023-01-31', 'bestSeller');
INSERT INTO `order_info` VALUES (46, 5802175, 'wateroh', 58.7, '2023-04-11', 'h martoh');
INSERT INTO `order_info` VALUES (215436821, 2102240, 'kitchen', 35.61, '2023-04-10', 'plastic warp');
INSERT INTO `order_info` VALUES (886414149, 2102240, 'kitchen', 50.21, '2023-02-10', 'new machine');
INSERT INTO `order_info` VALUES (1260651452, 5802175, 'yueshi', 22.21, '2023-04-11', 'this is eclipse');
INSERT INTO `order_info` VALUES (1483201142, 2102240, 'kitchen', 5.33, '2023-04-10', 'dish soap');
INSERT INTO `order_info` VALUES (1914643528, 2102240, 'kitchen', 23.22, '2023-04-10', 'best coke');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `userId` int(11) NOT NULL,
  `userName` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `userId_UNIQUE`(`userId` ASC) USING BTREE,
  UNIQUE INDEX `userName_UNIQUE`(`userName` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (2102240, 'Pikachu', 'Pikachu@gmail.com', '7152668668', 'TN7CMJOTJ6wAJ6XWhEdElQ==');
INSERT INTO `user_info` VALUES (3605528, 'Ultraman', 'Ultraman@gmail.com', '2525136701', '');
INSERT INTO `user_info` VALUES (5802175, 'Avatar', 'Avatar@gmail.com', '8572905778', '');
INSERT INTO `user_info` VALUES (654470625, '', NULL, NULL, 'UD+MFZIulNqCTx6V01wJkQ==');
INSERT INTO `user_info` VALUES (663746485, 'zhanshi', NULL, NULL, 'g84yhLfxLmC20j1aQJ9j5g==');
INSERT INTO `user_info` VALUES (665484700, '333', NULL, NULL, 'TN7CMJOTJ6wAJ6XWhEdElQ==');
INSERT INTO `user_info` VALUES (675288810, 'xinjiala', NULL, NULL, '618');
INSERT INTO `user_info` VALUES (801127606, '123456', NULL, NULL, 'TN7CMJOTJ6wAJ6XWhEdElQ==');

SET FOREIGN_KEY_CHECKS = 1;
