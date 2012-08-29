/*
Navicat MySQL Data Transfer

Source Server         : 192.168.12.250_3306
Source Server Version : 50525
Source Host           : 192.168.12.250:3306
Source Database       : erp4h

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2012-08-28 17:01:42
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tblmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tblmenu`;
CREATE TABLE `tblmenu` (
  `MenuID` int(3) NOT NULL AUTO_INCREMENT,
  `MenuPosition` int(3) DEFAULT NULL,
  `MenuValue` varchar(45) DEFAULT NULL,
  `MenuFiliationID` int(3) DEFAULT NULL,
  `FormName` varchar(45) DEFAULT NULL,
  `MenuEnable` tinyint(1) DEFAULT NULL,
  `ShortcutKey` varchar(45) DEFAULT NULL,
  `MenuIcon` varchar(255) DEFAULT NULL,
  `MenuType` tinyint(2) DEFAULT NULL,
  `MenuStatus` tinyint(1) DEFAULT NULL,
  `Mnemonic` char(1) DEFAULT NULL,
  `PhanHeID` int(11) DEFAULT NULL,
  `CreatedDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UserID` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`MenuID`),
  KEY `PhanHeID` (`PhanHeID`),
  CONSTRAINT `tblmenu_ibfk_1` FOREIGN KEY (`PhanHeID`) REFERENCES `tblphanhe` (`PhanHeID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblmenu
-- ----------------------------
INSERT INTO tblmenu VALUES ('1', '1', 'Quản lý', '0', null, null, null, null, null, null, 'Q', '1', '2012-08-28 11:30:03', null);
INSERT INTO tblmenu VALUES ('2', '3', 'Trợ giúp', '0', null, null, null, null, null, null, 'T', '1', '2012-08-28 11:30:02', null);
INSERT INTO tblmenu VALUES ('3', '4', 'Hệ thống', '0', null, null, null, null, null, null, 'H', '1', '2012-08-28 11:30:02', null);
INSERT INTO tblmenu VALUES ('4', '1', 'Thiết bị tin học', '1', null, null, null, null, null, null, null, '1', '2012-08-28 14:02:48', null);
INSERT INTO tblmenu VALUES ('5', '1', 'sub 2.1', '2', null, null, null, null, null, null, null, '1', '2012-08-28 11:30:01', null);
INSERT INTO tblmenu VALUES ('6', '2', 'Giải chi thu nhập tăng thêm', '1', null, null, null, '/Images/coins.png', null, null, null, '1', '2012-08-28 14:02:23', null);
INSERT INTO tblmenu VALUES ('7', '2', 'Thoát khỏi ứng dụng', '4', null, null, null, '/Images/shutdown.png', null, null, null, '1', '2012-08-28 13:10:50', null);
INSERT INTO tblmenu VALUES ('8', '3', 'Văn phòng phẩm', '1', null, null, null, null, null, null, null, '1', '2012-08-28 11:29:59', null);
INSERT INTO tblmenu VALUES ('9', '2', 'Thống kê - Báo cáo', '0', null, null, null, null, null, null, 'B', '1', '2012-08-28 11:29:59', null);
INSERT INTO tblmenu VALUES ('10', '1', 'Thông tin chương trình', '3', null, null, null, null, null, null, null, '1', '2012-08-28 11:30:00', null);
INSERT INTO tblmenu VALUES ('11', '1', 'Đăng nhập', '4', null, null, null, '/Images/login.png', null, null, null, '1', '2012-08-28 13:46:13', null);
