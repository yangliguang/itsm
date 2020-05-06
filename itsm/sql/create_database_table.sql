/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.47-log : Database - callcenter
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`callcenter` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `callcenter`;

/*Table structure for table `t_duty` */

DROP TABLE IF EXISTS `t_duty`;

CREATE TABLE `t_duty` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID，自增主键',
  `work_day` date NOT NULL COMMENT '日期',
  `user_id` int(11) DEFAULT NULL COMMENT '接线员ID，关联用户表ID',
  `username` varchar(20) DEFAULT NULL COMMENT '接线员名称',
  `operate_number` int(11) DEFAULT NULL COMMENT '当日服务数量',
  `bak1` varchar(20) DEFAULT NULL COMMENT '扩展字段1',
  `bak2` varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  `bak3` varchar(80) DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_event` */

DROP TABLE IF EXISTS `t_event`;

CREATE TABLE `t_event` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID，自增主键',
  `event` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '事件名称',
  `caller_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户名称',
  `caller_department` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户机构',
  `caller_phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户电话',
  `caller_contact` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '客户期望回复方式',
  `operator_id` int(11) DEFAULT NULL COMMENT '接线员ID',
  `operator_phone` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接线员电话',
  `operator_name` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '接线员名称',
  `event_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '来电时间',
  `event_process` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '事件处置过程',
  `event_state` int(1) DEFAULT NULL COMMENT '事件状态，0处置中，1结束，2联系不上客户',
  `finish_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '完成时间',
  `customer_evaluation` int(2) DEFAULT NULL COMMENT '客户评价，0~10分',
  `evaluate_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '评价时间',
  `bak1` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '扩展字段1',
  `bak2` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '扩展字段2',
  `bak3` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID，自增主键',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `fullname` varchar(50) DEFAULT NULL COMMENT '用户全称',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `isused` tinyint(1) DEFAULT NULL COMMENT '使用状态，0不可用，1可用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `bak1` varchar(20) DEFAULT NULL COMMENT '扩展字段1',
  `bak2` varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  `bak3` varchar(80) DEFAULT NULL COMMENT '扩展字段3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
