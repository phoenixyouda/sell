/*
Navicat MySQL Data Transfer

Source Server         : work
Source Server Version : 50717
Source Host           : 161.9.0.208:3306
Source Database       : sell

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-11-14 13:53:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品单价',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '商品图标',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1541670633947987140', '1541670633901957607', '123457', '皮皮虾', '3.20', 'http://xxxxx.jpg', '2', '2018-11-08 17:50:36', '2018-11-08 17:50:36');
INSERT INTO `order_detail` VALUES ('1541740302078949112', '1541670633901957607', '123457', '皮皮虾2', '3.20', 'https://img10.360buyimg.com/n1/s150x150_jfs/t9490/74/1068756959/241855/2b96cb3d/59b3aa60N18e13cbe.jpg', '2', '2018-11-09 13:11:53', '2018-11-13 09:10:23');
INSERT INTO `order_detail` VALUES ('1542079248154978207', '1542079248137910945', '123457', '皮皮虾2', '3.20', 'https://img10.360buyimg.com/n1/s150x150_jfs/t9490/74/1068756959/241855/2b96cb3d/59b3aa60N18e13cbe.jpg', '1', '2018-11-13 11:20:49', '2018-11-13 11:20:49');
INSERT INTO `order_detail` VALUES ('1542079248186965403', '1542079248137910945', '1234978673', 'cto书', '98.00', 'https://img10.360buyimg.com/n1/s150x150_jfs/t9490/74/1068756959/241855/2b96cb3d/59b3aa6xx.jpg', '2', '2018-11-13 11:20:49', '2018-11-13 11:20:49');
INSERT INTO `order_detail` VALUES ('1542079302468905524', '1542079302465925232', '123457', '皮皮虾2', '3.20', 'https://img10.360buyimg.com/n1/s150x150_jfs/t9490/74/1068756959/241855/2b96cb3d/59b3aa60N18e13cbe.jpg', '1', '2018-11-13 11:21:43', '2018-11-13 11:21:43');
INSERT INTO `order_detail` VALUES ('1542079317665977340', '1542079317658979114', '1234978673', 'cto书', '98.00', 'https://img10.360buyimg.com/n1/s150x150_jfs/t9490/74/1068756959/241855/2b96cb3d/59b3aa6xx.jpg', '1', '2018-11-13 11:21:58', '2018-11-13 11:21:58');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家姓名',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openi',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态,默认0新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单主表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1541670633901957607', ' 张三', ' 18868822111', ' 慕课网总部', ' ew3euwhd7sjw9diwkq', '6.40', '0', '0', '2018-11-08 17:50:36', '2018-11-13 10:34:00');
INSERT INTO `order_master` VALUES ('1541740300989915929', '123', '34', '7890', 'xx123aaa', '6.40', '0', '0', '2018-11-09 13:11:53', '2018-11-09 13:11:53');
INSERT INTO `order_master` VALUES ('1542079248137910945', 'tom', '12345', 'xxzz', 'xx123aaa', '199.20', '1', '0', '2018-11-13 11:20:49', '2018-11-13 12:13:59');
INSERT INTO `order_master` VALUES ('1542079302465925232', 'zhang', '123', '45678', 'xx123aaa', '3.20', '2', '0', '2018-11-13 11:21:43', '2018-11-13 11:51:06');
INSERT INTO `order_master` VALUES ('1542079317658979114', 'hello', '2568', '4567', 'xx123aaa', '98.00', '2', '0', '2018-11-13 11:21:58', '2018-11-13 11:49:18');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(32) NOT NULL COMMENT '类目名称',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '2', '2', '2018-11-06 22:44:55', '2018-11-06 22:44:55');
INSERT INTO `product_category` VALUES ('2', '小说榜', '3', '2018-11-07 00:29:14', '2018-11-07 00:29:14');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) NOT NULL COMMENT '图标',
  `product_status` int(11) unsigned zerofill NOT NULL COMMENT '商品状态',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('123457', '皮皮虾2', '3.20', '95', '很好吃的虾', 'https://img10.360buyimg.com/n1/s150x150_jfs/t9490/74/1068756959/241855/2b96cb3d/59b3aa60N18e13cbe.jpg', '00000000002', '2', '2018-11-07 01:37:41', '2018-11-13 15:55:31');
INSERT INTO `product_info` VALUES ('1234978673', 'cto书', '98.00', '198', '科技书籍', 'https://img10.360buyimg.com/n1/s150x150_jfs/t9490/74/1068756959/241855/2b96cb3d/59b3aa6xx.jpg', '00000000001', '3', '2018-11-07 01:37:41', '2018-11-13 15:46:00');
