/*
Navicat MySQL Data Transfer

Source Server         : cartoon
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : quadratic_element

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2023-05-09 00:38:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `about` varchar(10000) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT NULL,
  `cover_image` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '原神', '《原神》是由米哈游自研的一款全新开放世界冒险RPG。你将在游戏中探索一个被称作「提瓦特」的幻想世界。 在这广阔的世界中，你可以踏遍七国，邂逅性格各异、能力独特的同伴，与他们一同对抗强敌，踏上寻回血亲之路；也可以不带目的地漫游，沉浸在充满生机的世界里，让好奇心驱使自己发掘各个角落的奥秘⋯⋯ 直到你与分离的血亲重聚，在终点见证一切事物的沉淀。 《原神》PC版技术性开放测试（不删档）在2020.9.15开启，全球同步公测定档2020.9.28。', 'https://gss0.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/34fae6cd7b899e5121a2bee044a7d933c8950d11.jpg');
INSERT INTO `category` VALUES ('2', '崩坏3', '作为《崩坏3》团队的一部分，miHoYo Anime在这些年陆续制作了十余部高质量短片，相比游戏、音乐等米哈游内部其他的内容创作部门，他们自嘲称自己“可能是每次更新相隔周期最长的小组，基本游戏更新了好几个版本后，我们才能做出一部动画短片作品', 'fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/34fae6cd7b899e5121a2bee044a7d933c8950d11.jpg');
INSERT INTO `category` VALUES ('3', '王者', '《王者荣耀》是由腾讯游戏天美工作室群开发并运营在Android、IOS、NS平台上的MOBA类国产手游，于2015年11月26日在Android、iOS平台上正式公测，游戏曾经使用名称有《英雄战迹》、《王者联盟》。《王者荣耀》的欧美版本为《传说对决》（Arena Of Valor）。\r\n王者荣耀中的玩法以竞技对战为主，玩家之间进行1V1、3V3、5V5等多种方式的PVP对战，在满足条件后可以参加游戏的排位赛等，还可以参加PVE的闯关模式，是属于推塔类型的游戏。', 'fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/34fae6cd7b899e5121a2bee044a7d933c8950d11.jpg');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `collect_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_out_key` (`role_id`),
  KEY `user_out_key` (`user_id`),
  CONSTRAINT `role_out_key` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `user_out_key` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '1', '5', '2023-05-07 13:45:45');
INSERT INTO `collect` VALUES ('2', '1', '7', '2023-05-07 13:46:31');
INSERT INTO `collect` VALUES ('3', '1', '4', '2023-05-07 13:46:50');
INSERT INTO `collect` VALUES ('4', '2', '4', '2023-05-09 00:07:39');
INSERT INTO `collect` VALUES ('23', '1', '5', '2023-05-09 00:21:43');
INSERT INTO `collect` VALUES ('26', '1', '1', '2023-05-09 00:25:11');
INSERT INTO `collect` VALUES ('30', '1', '1', '2023-05-09 00:37:33');
INSERT INTO `collect` VALUES ('32', '2', '1', '2023-05-09 00:37:46');

-- ----------------------------
-- Table structure for more
-- ----------------------------
DROP TABLE IF EXISTS `more`;
CREATE TABLE `more` (
  `id` int NOT NULL AUTO_INCREMENT,
  `storys` varchar(255) DEFAULT NULL,
  `images` varchar(255) DEFAULT NULL,
  `combat` varchar(255) DEFAULT NULL,
  `group` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `group` (`group`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of more
-- ----------------------------
INSERT INTO `more` VALUES ('1', '弧线走位，玩家使用电脑游戏时采用W+S或者W+D等操作，角色与敌方拉开距离，边跑边射，既能时刻保证和敌方之间的安全距离，又不耽误瞄准射击', 'https://img.3dmgame.com/uploads/images/xiaz/20210106/1609910433_750135.jpg', '弧线走位，玩家使用电脑游戏时采用W+S或者W+D等操作，角色与敌方拉开距离，边跑边射，既能时刻保证和敌方之间的安全距离，又不耽误瞄准射击', '0');
INSERT INTO `more` VALUES ('2', '甘雨的主要输出手法为普通攻击加元素战技。并且元素战技可以作为辅助其他角色进行持续的元素反应输出。由于甘雨是个五星角色，一命座就可以增加蓄力重击的伤害，二命座之后的效果会有明显提升', 'https://img.3dmgame.com/uploads/images/xiaz/20210106/1609910433_750135.jpg', '甘雨的主要输出手法为普通攻击加元素战技。并且元素战技可以作为辅助其他角色进行持续的元素反应输出。由于甘雨是个五星角色，一命座就可以增加蓄力重击的伤害，二命座之后的效果会有明显提升', '0');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `about` varchar(10000) DEFAULT '',
  `head_image` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci DEFAULT '',
  `category` int DEFAULT NULL,
  `more` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_type` (`category`),
  KEY `more_info` (`more`),
  CONSTRAINT `more_info` FOREIGN KEY (`more`) REFERENCES `more` (`group`),
  CONSTRAINT `role_type` FOREIGN KEY (`category`) REFERENCES `category` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '甘雨', '甘雨天性优雅娴静，但仙兽“麒麟”温柔的性情与坚定毅重的工作态度毫无冲突。毕竟，甘雨坚信自己所做的一切工作都是为了践行与帝君的契约，谋求璃月众生的最大福祉', 'https://images5.alphacoders.com/112/1127881.jpg', '1', '0');
INSERT INTO `roles` VALUES ('2', '宵宫', '', 'https://webstatic.mihoyo.com/upload/static-resource/2021/08/13/5533cfe59fa4b3168027510926b1a188_6925661268941087958.jpg', '1', '0');
INSERT INTO `roles` VALUES ('3', '胡桃', '', 'https://upload-bbs.mihoyo.com/upload/2021/07/05/181022726/a23d0da75d32f10428ffa0e8cc53932e_7261091149645338367.jpg', '1', '0');
INSERT INTO `roles` VALUES ('4', '八重樱', '', 'https://img.zcool.cn/community/01fcbf5c2da373a8012029ac9e23f1.jpg@1280w_1l_2o_100sh.jpg', '2', '0');
INSERT INTO `roles` VALUES ('5', '渡鸦', '', 'https://image.9game.cn/2020/1/17/135179395.jpg', '2', '0');
INSERT INTO `roles` VALUES ('6', '孙尚香', '', 'https://pic4.zhimg.com/v2-139503cb5c8b2babcb222aeb0f4de2cc_r.jpg?source=1940ef5c', '3', '0');
INSERT INTO `roles` VALUES ('7', '凯', '', 'https://clubimg.club.vmall.com/data/attachment/forum/202012/29/185042hj9k5lj4kbmngji1.jpg', '3', '0');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'wind', '123456', 'https://tupian.qqw21.com/article/UploadPic/2020-11/2020112022562230967.jpg');
INSERT INTO `users` VALUES ('2', 'king', '456789', 'https://tupian.qqw21.com/article/UploadPic/2020-4/202042021313144957.jpg');
INSERT INTO `users` VALUES ('3', '落叶', '288010', 'http://img.name2012.com/uploads/allimg/180503/201F55201-8.jpg');
