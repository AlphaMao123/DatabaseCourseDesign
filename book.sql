/*
 Navicat Premium Data Transfer

 Source Server         : mydata
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : bookos

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 24/06/2021 17:08:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` int NULL DEFAULT NULL,
  `edition` int NULL DEFAULT NULL,
  `isbn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publisher` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`isbn`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('高等数学（物理类）', '张国玳', 69, 1, '978-7-03-019291-2', '科学出版社', '1');
INSERT INTO `book` VALUES ('概率论与数理统计', '盛骤', 45, 4, '978-7-04-023896-9', '高等教育出版社', '2');
INSERT INTO `book` VALUES ('线性代数', '殷洪友', 17, 1, '978-7-04-034083-9', '高等教育出版社', '1');
INSERT INTO `book` VALUES ('离散数学', '屈婉玲', 41, 2, '978-7-04-041908-5', '高等教育出版社', '2');
INSERT INTO `book` VALUES ('普通物理学', '程守洙', 52, 7, '978-7-04-042919-0', '高等教育出版社', '1');
INSERT INTO `book` VALUES ('人工智能基础', '蔡自兴', 39, 3, '978-7-04-046378-1', '高等教育出版社', '3');
INSERT INTO `book` VALUES ('计算机系统基础', '袁春风', 59, 2, '978-7-111-60489-1', '机械工业出版社', '2');
INSERT INTO `book` VALUES ('编译原理', '陈火旺', 39, 3, '978-7-118-02207-0', '国防工业出版社', '3');
INSERT INTO `book` VALUES ('算法设计技巧与分析', 'M.H.Alsuwaiyel', 55, 1, '978-7-121-29834-9', '电子工业出版社', '2');
INSERT INTO `book` VALUES ('计算机网络', '谢希仁', 49, 7, '978-7-121-30295-4', '电子工业出版社', '3');
INSERT INTO `book` VALUES ('数据结构', '严蔚敏', 35, 2, '978-7-302-14751-0', '清华大学出版社', '2');
INSERT INTO `book` VALUES ('C语言程序设计', '谭浩强', 33, 4, '978-7-302-22446-4', '清华大学出版社', '1');
INSERT INTO `book` VALUES ('面向对象C++程序设计', '皮德常', 39, 1, '978-7-302-45892-0', '清华大学出版社', '1');
INSERT INTO `book` VALUES ('计算机操作系统', '汤小凤', 53, 4, '978-7-5606-3350-3', '西安电子科技大学出版社', '3');
INSERT INTO `book` VALUES ('数据库系统概论', '王珊', 39, 5, '987-7-04-040664-1', '高等教育出版社', '3');

SET FOREIGN_KEY_CHECKS = 1;
