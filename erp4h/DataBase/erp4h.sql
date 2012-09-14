/*
Navicat MySQL Data Transfer

Source Server         : erp4h
Source Server Version : 50525
Source Host           : 192.168.12.250:3306
Source Database       : erp4h

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2012-09-14 16:57:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tblgroup`
-- ----------------------------
DROP TABLE IF EXISTS `tblgroup`;
CREATE TABLE `tblgroup` (
  `GroupID` int(3) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(45) DEFAULT NULL,
  `Organizational` bit(1) DEFAULT NULL,
  `CreateDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Owner` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`GroupID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblgroup
-- ----------------------------
INSERT INTO `tblgroup` VALUES ('1', 'Admin', '', '2012-09-10 17:48:18', null);
INSERT INTO `tblgroup` VALUES ('2', 'Khoa Dược', '', '2012-09-13 13:47:50', null);
INSERT INTO `tblgroup` VALUES ('3', 'Kho chẵn', '', '2012-09-13 13:47:48', null);
INSERT INTO `tblgroup` VALUES ('4', 'Kho lẻ 1', '', null, null);

-- ----------------------------
-- Table structure for `tblgroupright`
-- ----------------------------
DROP TABLE IF EXISTS `tblgroupright`;
CREATE TABLE `tblgroupright` (
  `PhanHeID` tinyint(2) NOT NULL DEFAULT '0',
  `GroupID` int(11) NOT NULL DEFAULT '0',
  `GroupRight` tinytext,
  `CreateDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Owner` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`PhanHeID`,`GroupID`),
  KEY `GroupID` (`GroupID`),
  CONSTRAINT `tblgroupright_ibfk_1` FOREIGN KEY (`GroupID`) REFERENCES `tblgroup` (`GroupID`),
  CONSTRAINT `tblgroupright_ibfk_2` FOREIGN KEY (`PhanHeID`) REFERENCES `tblphanhe` (`PhanHeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblgroupright
-- ----------------------------
INSERT INTO `tblgroupright` VALUES ('1', '1', ';1;2;3;4;5;6;7;8;9;', '2012-09-13 11:47:46', null);
INSERT INTO `tblgroupright` VALUES ('1', '3', ';1;2;3;4;', '2012-09-13 15:36:38', null);

-- ----------------------------
-- Table structure for `tblgroupuser`
-- ----------------------------
DROP TABLE IF EXISTS `tblgroupuser`;
CREATE TABLE `tblgroupuser` (
  `GroupID` int(3) NOT NULL DEFAULT '0',
  `UserID` varchar(12) NOT NULL DEFAULT '',
  `CreateDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Owner` varchar(12) DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`GroupID`,`UserID`),
  KEY `UserID` (`UserID`),
  KEY `GroupID` (`GroupID`),
  CONSTRAINT `tblgroupuser_ibfk_1` FOREIGN KEY (`GroupID`) REFERENCES `tblgroup` (`GroupID`),
  CONSTRAINT `tblgroupuser_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `tbluser` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblgroupuser
-- ----------------------------
INSERT INTO `tblgroupuser` VALUES ('1', 'HieuLV', null, null, null);
INSERT INTO `tblgroupuser` VALUES ('3', 'HieuLV', '2012-09-13 15:35:42', null, null);

-- ----------------------------
-- Table structure for `tblkhoaphong`
-- ----------------------------
DROP TABLE IF EXISTS `tblkhoaphong`;
CREATE TABLE `tblkhoaphong` (
  `KhoaPhongID` int(11) NOT NULL DEFAULT '0',
  `TenKhoaPhong` char(255) DEFAULT NULL,
  PRIMARY KEY (`KhoaPhongID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblkhoaphong
-- ----------------------------
INSERT INTO `tblkhoaphong` VALUES ('1100', 'PHONG TO CHUC CAN BO');
INSERT INTO `tblkhoaphong` VALUES ('1200', 'PHONG HANH CHANH QUAN TRI');
INSERT INTO `tblkhoaphong` VALUES ('1220', 'PHONG THI DUA');
INSERT INTO `tblkhoaphong` VALUES ('1300', 'BAN THANH TRA');
INSERT INTO `tblkhoaphong` VALUES ('1301', 'PHONG NGHIEP VU');
INSERT INTO `tblkhoaphong` VALUES ('1302', 'PHONG THONG KE KE HOACH');
INSERT INTO `tblkhoaphong` VALUES ('1303', 'PHONG QUAN LY DUOC');
INSERT INTO `tblkhoaphong` VALUES ('1304', 'PHONG KE TOAN TAI VU');
INSERT INTO `tblkhoaphong` VALUES ('1305', 'HOI Y HOC');
INSERT INTO `tblkhoaphong` VALUES ('1306', 'PHONG DIEN TOAN');
INSERT INTO `tblkhoaphong` VALUES ('1307', 'BAN CAI TAO');
INSERT INTO `tblkhoaphong` VALUES ('1308', 'CHUONG TRINH PAM');
INSERT INTO `tblkhoaphong` VALUES ('1309', 'BAO VE MOI SINH');
INSERT INTO `tblkhoaphong` VALUES ('1310', 'DANH SACH CHO GIAI QUYET');
INSERT INTO `tblkhoaphong` VALUES ('1314', 'PHONG TO CHUC');
INSERT INTO `tblkhoaphong` VALUES ('1315', 'PHONG HANH CHANH QUAN TRI');
INSERT INTO `tblkhoaphong` VALUES ('1316', 'BAN GIAM DOC');
INSERT INTO `tblkhoaphong` VALUES ('1317', 'PHONG TAI VU');
INSERT INTO `tblkhoaphong` VALUES ('1318', 'PHONG GIAO VU');
INSERT INTO `tblkhoaphong` VALUES ('1400', 'KHOA TIN HOC Y HOC');
INSERT INTO `tblkhoaphong` VALUES ('1401', 'PHONG GIAO DUC CHINH TRI');
INSERT INTO `tblkhoaphong` VALUES ('1403', 'BO MON DONG Y DUOC');
INSERT INTO `tblkhoaphong` VALUES ('1501', 'BO MON NGOAI');
INSERT INTO `tblkhoaphong` VALUES ('1502', 'BO MON Y HOC CO SO');
INSERT INTO `tblkhoaphong` VALUES ('1503', 'BO MON THUC HANH B.VIEN');
INSERT INTO `tblkhoaphong` VALUES ('1504', 'BO MON NOI NHI LAY');
INSERT INTO `tblkhoaphong` VALUES ('1506', 'GIAI PHAU SINH LY');
INSERT INTO `tblkhoaphong` VALUES ('1507', 'BO MON SAN');
INSERT INTO `tblkhoaphong` VALUES ('1508', 'BO MON VI SINH');
INSERT INTO `tblkhoaphong` VALUES ('1601', 'PHONG GIAO TAI');
INSERT INTO `tblkhoaphong` VALUES ('1602', 'BO MON SINH NGU');
INSERT INTO `tblkhoaphong` VALUES ('1603', 'BO MON VAT LY');
INSERT INTO `tblkhoaphong` VALUES ('1604', 'BO MON VI KY SINH');
INSERT INTO `tblkhoaphong` VALUES ('1605', 'BO MON RANG HAM MAT');
INSERT INTO `tblkhoaphong` VALUES ('1606', 'BO MON D/LIEU - CT C/HINH');
INSERT INTO `tblkhoaphong` VALUES ('1607', 'PHONG THU VIEN');
INSERT INTO `tblkhoaphong` VALUES ('1608', 'BAN THU KY Y TE CO QUAN');
INSERT INTO `tblkhoaphong` VALUES ('1610', 'BO MON TAI MUI HONG');
INSERT INTO `tblkhoaphong` VALUES ('1612', 'BO MON PHOI');
INSERT INTO `tblkhoaphong` VALUES ('1613', 'BO MON GIAI PHAU BENH');
INSERT INTO `tblkhoaphong` VALUES ('1614', 'BO MON SINH LY');

-- ----------------------------
-- Table structure for `tblloaithietbi`
-- ----------------------------
DROP TABLE IF EXISTS `tblloaithietbi`;
CREATE TABLE `tblloaithietbi` (
  `LoaiID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `TenLoai` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LoaiID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblloaithietbi
-- ----------------------------

-- ----------------------------
-- Table structure for `tblmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tblmenu`;
CREATE TABLE `tblmenu` (
  `MenuID` int(3) NOT NULL AUTO_INCREMENT,
  `PhanHeID` tinyint(3) DEFAULT NULL,
  `MenuPosition` int(3) DEFAULT NULL,
  `MenuValue` varchar(45) DEFAULT NULL,
  `MenuFiliationID` int(3) DEFAULT NULL,
  `FormName` varchar(45) DEFAULT NULL,
  `MenuAction` varchar(255) DEFAULT NULL,
  `MenuActionTypeID` tinyint(2) DEFAULT NULL,
  `MenuEnable` tinyint(1) DEFAULT NULL,
  `MenuHotKey` char(1) DEFAULT NULL,
  `MenuIcon` varchar(255) DEFAULT NULL,
  `MenuTypeID` tinyint(2) DEFAULT NULL,
  `MenuStatus` tinyint(1) DEFAULT NULL,
  `Mnemonic` char(1) DEFAULT NULL,
  `CreatedDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Owner` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`MenuID`),
  KEY `PhanHeID` (`PhanHeID`),
  CONSTRAINT `tblmenu_ibfk_1` FOREIGN KEY (`PhanHeID`) REFERENCES `tblphanhe` (`PhanHeID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblmenu
-- ----------------------------
INSERT INTO `tblmenu` VALUES ('1', '1', '1', 'Quản lý', '0', null, null, null, null, null, null, null, null, 'Q', '2012-08-28 11:30:03', null);
INSERT INTO `tblmenu` VALUES ('2', '1', '3', 'Trợ giúp', '0', null, null, null, null, null, null, null, null, 'T', '2012-08-28 11:30:02', null);
INSERT INTO `tblmenu` VALUES ('3', '1', '4', 'Hệ thống', '0', null, null, null, null, null, null, null, null, 'H', '2012-08-28 11:30:02', null);
INSERT INTO `tblmenu` VALUES ('4', '1', '1', 'Thiết bị tin học', '1', null, null, null, null, null, null, null, null, null, '2012-08-29 17:02:12', null);
INSERT INTO `tblmenu` VALUES ('5', '1', '1', 'sub 2.1', '2', null, null, null, null, null, null, null, null, null, '2012-08-28 11:30:01', null);
INSERT INTO `tblmenu` VALUES ('6', '1', '2', 'Giải chi thu nhập tăng thêm', '1', null, null, null, null, 'G', '/Images/coins.png', null, null, null, '2012-08-29 11:27:48', null);
INSERT INTO `tblmenu` VALUES ('7', '1', '3', 'Thoát khỏi ứng dụng', '4', null, null, null, null, null, '/Images/shutdown.png', null, null, null, '2012-09-10 13:14:38', null);
INSERT INTO `tblmenu` VALUES ('8', '1', '3', 'Văn phòng phẩm', '1', null, null, null, null, 'V', null, null, null, null, '2012-08-29 11:26:59', null);
INSERT INTO `tblmenu` VALUES ('9', '1', '2', 'Thống kê - Báo cáo', '0', null, null, null, null, null, null, null, null, 'B', '2012-08-28 11:29:59', null);
INSERT INTO `tblmenu` VALUES ('10', '1', '1', 'Thông tin chương trình', '3', null, null, null, null, null, null, null, null, null, '2012-08-28 11:30:00', null);
INSERT INTO `tblmenu` VALUES ('11', '1', '1', 'Đăng nhập lại', '4', null, null, null, null, null, '/Images/login.png', null, null, null, '2012-09-10 16:55:23', null);
INSERT INTO `tblmenu` VALUES ('12', '1', '2', 'Quản lý hệ thống', '4', null, null, null, null, null, null, null, null, null, '2012-09-10 16:55:42', null);

-- ----------------------------
-- Table structure for `tblmenuactiontype`
-- ----------------------------
DROP TABLE IF EXISTS `tblmenuactiontype`;
CREATE TABLE `tblmenuactiontype` (
  `MenuActionTypeID` tinyint(2) NOT NULL AUTO_INCREMENT,
  `ActionType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MenuActionTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblmenuactiontype
-- ----------------------------
INSERT INTO `tblmenuactiontype` VALUES ('1', 'Open form');
INSERT INTO `tblmenuactiontype` VALUES ('2', 'Open report');
INSERT INTO `tblmenuactiontype` VALUES ('3', 'Run funtion');

-- ----------------------------
-- Table structure for `tblmenutype`
-- ----------------------------
DROP TABLE IF EXISTS `tblmenutype`;
CREATE TABLE `tblmenutype` (
  `MenuTypeID` smallint(2) NOT NULL AUTO_INCREMENT,
  `MenuType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MenuTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblmenutype
-- ----------------------------
INSERT INTO `tblmenutype` VALUES ('1', 'Menubar\'s element');
INSERT INTO `tblmenutype` VALUES ('2', 'Sub menu');
INSERT INTO `tblmenutype` VALUES ('3', 'Separator');
INSERT INTO `tblmenutype` VALUES ('4', 'Radio button menu item');
INSERT INTO `tblmenutype` VALUES ('5', 'Check box menu item');

-- ----------------------------
-- Table structure for `tblnhanvien`
-- ----------------------------
DROP TABLE IF EXISTS `tblnhanvien`;
CREATE TABLE `tblnhanvien` (
  `NhanVienID` varchar(5) NOT NULL,
  `KhoaPhongID` varchar(4) DEFAULT NULL,
  `HoTen` varchar(25) DEFAULT NULL,
  `SoBHXH` varchar(10) DEFAULT NULL,
  `SoCMND` varchar(10) DEFAULT NULL,
  `SoATM` varchar(15) DEFAULT NULL,
  `NgaySinh` varchar(10) DEFAULT NULL,
  `GioiTinh` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`NhanVienID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblnhanvien
-- ----------------------------
INSERT INTO `tblnhanvien` VALUES ('AIL01', '1601', 'L©m H÷u ¸i', '0202100197', '', '711A03517613', '24/05/63', '1');
INSERT INTO `tblnhanvien` VALUES ('AIN01', '1400', 'NguyÔn ThÞ Hoµi', '0296265624', '', '711A12673458', '25/10/63', '0');
INSERT INTO `tblnhanvien` VALUES ('AIN02', '1506', 'NguyÔn B¸ H¶i', '0296265765', '', '711A01171116', '15/7/69', '1');
INSERT INTO `tblnhanvien` VALUES ('AIN03', '1506', 'NguyÔn Thuý Quúnh Mai', '0296265767', '', '711A02590053', '06/12/67', '0');
INSERT INTO `tblnhanvien` VALUES ('ANB01', '1100', 'Bïi ThÞ Kim ¢n', '0296265601', '', '711A02590559', '04/3/62', '0');
INSERT INTO `tblnhanvien` VALUES ('ANB02', '1100', 'Bïi Ngäc Anh', '0207077563', '024037969', '711A06156084', '6/6/1986', '0');
INSERT INTO `tblnhanvien` VALUES ('ANC01', '1301', 'Cao Hoµi TuÊn Anh', '0203174873', '', '711A02670464', '1/1/78', '1');
INSERT INTO `tblnhanvien` VALUES ('AND01', '1603', '§inh Kim Anh', '0296265619', '', '711A07369802', '10/4/60', '0');
INSERT INTO `tblnhanvien` VALUES ('ANH01', '1508', 'Hoµng ThÞ Kim An', '0296265636', '', '711A01173005', '05/10/57', '0');
INSERT INTO `tblnhanvien` VALUES ('ANL02', '1603', 'Lª Duy Ngäc Anh', '0204270883', '', '711A01153686', '2/12/82', '0');
INSERT INTO `tblnhanvien` VALUES ('ANL03', '1301', 'Lª TuÊn Anh', '0207331872', '', '711A07741716', '1986', '1');
INSERT INTO `tblnhanvien` VALUES ('ANN01', '1306', 'NguyÔn Ngäc Anh', '0296265503', '', '711A02589215', '09/3/57', '1');
INSERT INTO `tblnhanvien` VALUES ('ANN03', '1314', 'NguyÔn ThÞ Thy Anh', '0296265582', '', '711A01153753', '09/5/71', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN04', '1403', 'NguyÔn ThÞ Hµ Quang', '0296265546', '', '711A02595707', '17/09/68', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN06', '1400', 'NguyÔn ThÞ Lan', '0296265625', '', '711A04019643', '09/2/68', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN07', '1220', 'NguyÔn V¨n Ng©n', '0296265727', '', '711A03542297', '21/7/67', '1');
INSERT INTO `tblnhanvien` VALUES ('ANN08', '1607', 'NguyÔn ThÞ ¸nh', '0201092970', '', '711A02598859', '16/12/69', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN10', '1220', 'NguyÔn Huúnh Chóc An', '0205370017', '', '711A02597243', '11/8/84', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN11', '1612', 'NguyÔn Lam Anh', '0204320929', '', '711A01154753', '16/01/82', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN12', '1506', 'NguyÔn ThÞ Ngäc Anh', '0206121623', '', '711A03517321', '7/3/84', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN13', '1300', 'NguyÔn ThÞ Duyªn Anh', '0204209002', '', '711A01177744', '08/07/80', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN15', '1309', 'NguyÔn Ngäc Anh', '0296227294', '020226021', '711A20526914', '6/9/59', '1');
INSERT INTO `tblnhanvien` VALUES ('ANN16', '1506', 'NguyÔn ThÞ Thanh An', '0206286310', '', '711A04371783', '15/12/1984', '0');
INSERT INTO `tblnhanvien` VALUES ('ANN17', '1310', 'NguyÔn ThÞ Ngäc ¸nh', '0298089269', '', '711A01242802', '1976', '0');
INSERT INTO `tblnhanvien` VALUES ('ANP01', '1310', 'Ph¹m V¨n Ên', '0296265689', '', '711A01158021', '18/4/60', '0');
INSERT INTO `tblnhanvien` VALUES ('ANP02', '1400', 'Ph¹m TiÕn Thµnh', '0296265626', '', '711A03448637', '11/11/57', '1');
INSERT INTO `tblnhanvien` VALUES ('ANP03', '1304', 'Ph¹m H÷u V¨n', '0296265752', '', '711A03518325', '02/1/56', '1');
INSERT INTO `tblnhanvien` VALUES ('ANP05', '1318', 'Ph¹m V¨n Toµn', '0296265772', '', '711A02596098', '21/6/55', '1');
INSERT INTO `tblnhanvien` VALUES ('ANP07', '1504', 'Phan ThÞ Kim ¸nh', '0201092983', '', '711A01170751', '13/7/72', '0');
INSERT INTO `tblnhanvien` VALUES ('ANP08', '1601', 'Ph¹m ThÞ Thóy Anh', '0204270862', '', '711A02587528', '18/10/82', '0');
INSERT INTO `tblnhanvien` VALUES ('ANT01', '1302', 'TrÇn Kim Anh', '0296265663', '', '711A01327361', '18/10/58', '0');
INSERT INTO `tblnhanvien` VALUES ('ANT02', '1303', 'TrÇn ThÞ ¸nh', '0296265450', '', '711A01150455', '12/10/58', '0');
INSERT INTO `tblnhanvien` VALUES ('ANT03', '1318', 'TrÞnh ThÞ Ngäc Anh', '0205274468', '', '711A03517846', '2/9/83', '0');
INSERT INTO `tblnhanvien` VALUES ('ANT04', '1314', 'TrÇn L­¬ng Anh', '0204154515', '', '711A59992561', '', '1');
INSERT INTO `tblnhanvien` VALUES ('ANT05', '1607', 'Tr­¬ng Lª TuÊn Anh', '0203174857', '', '711A01146996', '', '1');
INSERT INTO `tblnhanvien` VALUES ('ANV01', '1605', 'V¨ng ThÞ NGäc Anh', '0202161009', '', '711A02586196', '05/7/80', '0');
INSERT INTO `tblnhanvien` VALUES ('ANV02', '1614', 'Vò Hoµng Anh', '0205206244', '', '711A01177783', '1/3/80', '1');
INSERT INTO `tblnhanvien` VALUES ('AOP01', '1318', 'Ph¹m Huy T¶o', '0296265768', '', '711A03445058', '03/09/54', '1');
INSERT INTO `tblnhanvien` VALUES ('BAH01', '1507', 'Hoµng V¨n B¶o', '0296265526', '', '711A01173051', '03/03/55', '1');
INSERT INTO `tblnhanvien` VALUES ('BAL01', '1400', 'Lª ThÞ B¶y', '0205274481', '', '711A12673703', '1970', '0');
INSERT INTO `tblnhanvien` VALUES ('BAN01', '1306', 'NguyÔn V¨n B¶o', '0296265509', '', '711A03541376', '8/4/66', '1');
INSERT INTO `tblnhanvien` VALUES ('BAN03', '1506', 'NguyÔn ThÞ B©y', '0296265584', '', '711A02603022', '20/2/60', '0');
INSERT INTO `tblnhanvien` VALUES ('BAN05', '1310', 'NguyÔn V¨n B¹o', '0204298494', '', '711A01158897', '22/09/82', '1');
INSERT INTO `tblnhanvien` VALUES ('BAN06', '1314', 'NguyÔn SÜ B¶o', '0204154516', '', '711A01155903', '22/9/78', '1');
INSERT INTO `tblnhanvien` VALUES ('BAP01', '1220', 'Phan V¨n B¸u', '', '', '711A15774801', '', '1');
INSERT INTO `tblnhanvien` VALUES ('BIL02', '1310', 'Lª Cao Thanh B×nh', '0203315554', '', '711A01158861', '29/9/82', '1');
INSERT INTO `tblnhanvien` VALUES ('BIL03', '1605', 'Lª Thanh B×nh', '0203174849', '', '711A01145092', '11/1/78', '1');
INSERT INTO `tblnhanvien` VALUES ('BIN01', '1100', 'NguyÔn ThÞ BÝch', '0296265593', '', '711A01178144', '14/12/61', '0');
INSERT INTO `tblnhanvien` VALUES ('BIN03', '1302', 'NguyÔn Xu©n B×nh', '0296265525', '', '711A01150901', '12/10/54', '1');
INSERT INTO `tblnhanvien` VALUES ('BIN04', '1403', 'NguyÔn §¹i Biªn', '0296265446', '', '711A03518364', '11/2/52', '1');
INSERT INTO `tblnhanvien` VALUES ('BIN05', '1305', 'N«ng ThÞ Biªn', '0296265401', '', '711A01144191', '', '0');
INSERT INTO `tblnhanvien` VALUES ('BIN06', '1507', 'NguyÔn Xu©n B×nh', '0296265570', '', '711A01172983', '16/2/54', '1');
INSERT INTO `tblnhanvien` VALUES ('BIN08', '1301', 'NguyÔn ThÞ Thanh B×nh', '5696006461', '', '711A02586327', '1969', '0');
INSERT INTO `tblnhanvien` VALUES ('BIN09', '1301', 'NguyÔn ThÞ B×nh', '0205089485', '', '711A01254265', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('BIP02', '1301', 'Ph¹m Ngäc BÝch', '0205089486', '', '711A02586745', '1977', '0');
INSERT INTO `tblnhanvien` VALUES ('BIV01', '1604', 'Vò ThÞ B×nh', '0205089487', '', '711A03517494', '11/7/80', '0');
INSERT INTO `tblnhanvien` VALUES ('BRK01', '1306', 'K Brªl', '0299097790', '', '711A02590653', '24/12/69', '1');
INSERT INTO `tblnhanvien` VALUES ('CAL01', '1305', 'Lª ThÞ Hång Canh', '0296265697', '', '711A01169258', '300960', '0');
INSERT INTO `tblnhanvien` VALUES ('CAL02', '1310', 'L­u ChÝ CÈm', '0203315555', '', '711A01158782', '01/7/80', '1');
INSERT INTO `tblnhanvien` VALUES ('CAL03', '1301', 'S¸i ThÞ Ca', '0205089488', '', '711A01170281', '1979', '0');
INSERT INTO `tblnhanvien` VALUES ('CHB01', '1401', 'Bïi ThÞ Kim Chi', '0296265648', '', '711A03445528', '11/11/63', '0');
INSERT INTO `tblnhanvien` VALUES ('CHB02', '1607', 'Bïi ThÞ Quúnh Ch©u', '0202160952', '', '711A02599873', '11/10/71', '0');
INSERT INTO `tblnhanvien` VALUES ('CHC01', '1610', 'Chu Ngäc Ch©u', '0296265468', '', '711A05376689', '23/9/53', '1');
INSERT INTO `tblnhanvien` VALUES ('CHD01', '1602', 'D­¬ng Hoµng ChiÕu', '0297120891', '', '711A01173545', '12/4/72', '1');
INSERT INTO `tblnhanvien` VALUES ('CHD02', '1608', '§ç Huy Ch¸nh', '0205332654', '', '711A02592573', '14/4/68', '1');
INSERT INTO `tblnhanvien` VALUES ('CHD03', '1301', '§Æng V¨n ChiÕn', '0206389618', '', '711A05470421', '1980', '1');
INSERT INTO `tblnhanvien` VALUES ('CHH01', '1613', 'Hå ThÞ Minh Ch©u', '0298025762', '', '711A02589664', '05/11/74', '0');
INSERT INTO `tblnhanvien` VALUES ('CHL01', '1403', 'Lª ThÞ Chung', '0299097792', '', '711A01174206', '20/12/74', '0');
INSERT INTO `tblnhanvien` VALUES ('CHN01', '1503', 'NguyÔn ThÞ Chung', '0296265649', '', '711A01160143', '04/10/60', '0');
INSERT INTO `tblnhanvien` VALUES ('CHN02', '1310', 'NguyÔn ThÞ Chinh', '0296265695', '', '711A01182712', '20/6/61', '0');
INSERT INTO `tblnhanvien` VALUES ('CHN03', '1506', 'NguyÔn ThÞ Ngäc Ch©u', '0296265566', '', '711A02670204', '17/05/64', '0');
INSERT INTO `tblnhanvien` VALUES ('CHN04', '1314', 'NguyÔn ThÞ Kim Chi', '0201092988', '', '711A02602922', '29/9/79', '0');
INSERT INTO `tblnhanvien` VALUES ('CHN05', '1601', 'NguyÔn Thi Kim Chi', '7798024492', '', '711A08435683', '', '0');
INSERT INTO `tblnhanvien` VALUES ('CHN06', '1302', 'NguyÔn ThÞ Kim Ch©u', '0205264652', '', '711A01327385', '30/1/84', '0');
INSERT INTO `tblnhanvien` VALUES ('CHP01', '1315', 'Ph¹m Kh¾c ChÝnh', '0296265707', '', '711A01177907', '02/10/58', '1');
INSERT INTO `tblnhanvien` VALUES ('CHP02', '1301', 'Ph¹m ThÞ Ngäc Ch©u', '0206121624', '', '711A02670425', '19/10/85', '0');
INSERT INTO `tblnhanvien` VALUES ('CHP03', '1502', 'Ph¹m Ngäc Ch©u', '0205264618', '', '711A02592203', '9/10/84', '0');
INSERT INTO `tblnhanvien` VALUES ('CHP04', '1310', 'Phan Thanh Chung', '0207140808', '', '711A06156443', '1986', '1');
INSERT INTO `tblnhanvien` VALUES ('CHT01', '1501', 'TrÞnh ThÞ Chinh', '0296265558', '', '711A01152651', '20/7/63', '0');
INSERT INTO `tblnhanvien` VALUES ('CHT02', '1300', 'T¹ ThÞ Chóc', '0296328837', '', '711A04478272', '10/10/60', '0');
INSERT INTO `tblnhanvien` VALUES ('CHT04', '1305', 'TrÇn ThÞ Minh Ch©u', '0205264619', '', '711A01182782', '12/11/83', '0');
INSERT INTO `tblnhanvien` VALUES ('CHŸ01', '1310', '§oµn ThÞ ChÝnh', '0299097788', '', '711A02601078', '16/10/78', '0');
INSERT INTO `tblnhanvien` VALUES ('CON01', '1601', 'NguyÔn ThÞ Mü C¬', '0296265422', '', '711A02588168', '20/11/62', '0');
INSERT INTO `tblnhanvien` VALUES ('CUB01', '1400', 'Bïi ThÞ Kim Cóc', '0205274487', '', '711A02595674', '23/12/82', '0');
INSERT INTO `tblnhanvien` VALUES ('CUN01', '1200', 'NguyÔn Kim C­êng', '0296265722', '', '711A01178061', '02/9/64', '1');
INSERT INTO `tblnhanvien` VALUES ('CUP01', '1506', 'Phan ThÞ Cóc', '0202161035', '', '711A01171325', '01/1/77', '0');
INSERT INTO `tblnhanvien` VALUES ('CUP02', '1614', 'Ph¹m Minh C­êng', '0203333821', '', '711A01170511', '17/1/79', '1');
INSERT INTO `tblnhanvien` VALUES ('CUT01', '1304', 'TrÇn ThÞ C­¬ng', '0207331855', '', '711A07741671', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('CUV01', '1305', 'Vò ThÞ Kim Cóc', '0203315586', '', '711A01144764', '9/2/74', '0');
INSERT INTO `tblnhanvien` VALUES ('CUV02', '1401', 'Vâ Quèc C­êng', '0204069587', '', '711A01160112', '17/7/79', '1');
INSERT INTO `tblnhanvien` VALUES ('DAH01', '1314', 'Hå ThÞ Hång §µo', '0204270897', '', '711A01157815', '26/05/82', '0');
INSERT INTO `tblnhanvien` VALUES ('DAN01', '1503', 'NguyÔn ThÞ DÇn', '0296265555', '', '711A01152557', '01/4/62', '0');
INSERT INTO `tblnhanvien` VALUES ('DAN02', '1306', 'Ng« Huy DÇn', '0296265512', '', '711A02591593', '22/5/62', '1');
INSERT INTO `tblnhanvien` VALUES ('DAN04', '1504', 'NguyÔn ThÞ DËu', '0296265701', '', '711A03541712', '06/5/59', '0');
INSERT INTO `tblnhanvien` VALUES ('DAN06', '1302', 'NguyÔn ThÞ §µo', '0203174902', '', '711A01168921', '', '0');
INSERT INTO `tblnhanvien` VALUES ('DAN07', '1612', 'NguyÔn Vò H¶i §¨ng', '0207105749', '', '711A06350828', '1986', '1');
INSERT INTO `tblnhanvien` VALUES ('DAP01', '1507', 'Phan ThÞ Hång §µo', '0204270871', '', '711A02596438', '13/09/82', '0');
INSERT INTO `tblnhanvien` VALUES ('DAP02', '1301', 'Ph¹m ThÞ Hång §µo', '0204343538', '', '711A01170242', '22/6/83', '0');
INSERT INTO `tblnhanvien` VALUES ('DAP03', '1100', 'Ph¹m thÞ Quúnh Dao', '', '', '711A40879589', '', '0');
INSERT INTO `tblnhanvien` VALUES ('DAT01', '1507', 'TrÇn ThÞ §»m', '0296265421', '', '711A02596642', '26/08/59', '0');
INSERT INTO `tblnhanvien` VALUES ('DAT02', '1314', 'TrÇn Quèc §¹t', '0298109059', '', '711A01155804', '01/8/68', '1');
INSERT INTO `tblnhanvien` VALUES ('DAT03', '1601', 'TrÇn Dò §¹i', '0202041134', '', '711A03517424', '13/7/73', '1');
INSERT INTO `tblnhanvien` VALUES ('DEL01', '1612', 'L¹i ThÞ Ngäc §Ñp', '0204320926', '', '711A01154702', '19/07/83', '0');
INSERT INTO `tblnhanvien` VALUES ('DID01', '1301', '§inh ThÞ DiÔn', '0205089490', '', '711A01421381', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('DIH01', '1314', 'Huúnh Xu©n §iÓm', '0204154518', '', '711A02602081', '20/7/79', '1');
INSERT INTO `tblnhanvien` VALUES ('DIN01', '1314', 'NguyÔn Ngäc DiÔm', '0296265583', '', '711A02601843', '6/6/72', '0');
INSERT INTO `tblnhanvien` VALUES ('DIN02', '1503', 'NguyÔn TÊn Nam §×nh', '0205206229', '', '711A01152773', '29/11/82', '1');
INSERT INTO `tblnhanvien` VALUES ('DIN03', '1303', 'NguyÔn ThÞ Thanh DiÔm', '0206121638', '', '711A02598392', '28/8/84', '0');
INSERT INTO `tblnhanvien` VALUES ('DIN04', '1607', 'NguyÔn Ngäc DiÖu', '0202160957', '', '711A01150794', '22/7/79', '0');
INSERT INTO `tblnhanvien` VALUES ('DIN05', '1604', 'NguyÔn Ph­¬ng DiÖn', '0203315587', '', '711A01145429', '30/03/81', '0');
INSERT INTO `tblnhanvien` VALUES ('DIV01', '1300', 'Vâ Thanh Dinh', '0202160948', '', '711A02599815', '22/10/65', '1');
INSERT INTO `tblnhanvien` VALUES ('DIV02', '1610', 'V­u NghÞ §Þnh', '0204101926', '', '711A03445113', '10/10/75', '1');
INSERT INTO `tblnhanvien` VALUES ('DOL01', '1607', 'L­êng V¨n §ång', '0204130570', '', '711A01147155', '14/8/67', '1');
INSERT INTO `tblnhanvien` VALUES ('DON02', '1307', 'NguyÔn ThÞ Doan', '0296265560', '', '711A02588862', '19/7/62', '0');
INSERT INTO `tblnhanvien` VALUES ('DON03', '1305', 'NguyÔn Anh §oµn', '0204298506', '', '711A01143532', '20/1/71', '1');
INSERT INTO `tblnhanvien` VALUES ('DOP02', '1507', 'Phan Phïng ThÞ Kim Doanh', '0205264620', '', '711A01183301', '1/11/83', '0');
INSERT INTO `tblnhanvien` VALUES ('DOV01', '1303', 'Vâ §«n', '0202100189', '', '711A02598231', '07/10/65', '1');
INSERT INTO `tblnhanvien` VALUES ('DOV02', '1301', 'Vâ §¹i §ång', '0205383487', '', '711A02586575', '28/2/82', '1');
INSERT INTO `tblnhanvien` VALUES ('DUB01', '1610', 'Bïi Trung Dòng', '', '', '711A50543782', '7/11/1972', '1');
INSERT INTO `tblnhanvien` VALUES ('DUD01', '1401', '§Æng ThÞ Ph­¬ng Dung', '0201092980', '', '711A03444666', '07/1/73', '0');
INSERT INTO `tblnhanvien` VALUES ('DUD02', '1608', '§ç TiÕn Dòng', '0296265485', '', '711A01154308', '6/8/57', '1');
INSERT INTO `tblnhanvien` VALUES ('DUD05', '1309', '§oµn ThÞ Thïy D­¬ng', '0204270876', '', '711A01153237', '09/01/82', '0');
INSERT INTO `tblnhanvien` VALUES ('DUD06', '1310', 'D­¬ng Träng §øc', '0203174881', '', '711A01182803', '7/2/67', '1');
INSERT INTO `tblnhanvien` VALUES ('DUH02', '1314', 'Huúnh TrÝ Dòng', '0202161019', '', '711A02602251', '16/8/70', '1');
INSERT INTO `tblnhanvien` VALUES ('DUH03', '1100', 'Hµ Hoµng DiÖu Duyªn', '0203133699', '', '711A01178653', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('DUH04', '1303', 'Hå ThÞ Mü Dung', '0207105772', '', '711A06350603', '14/7/85', '0');
INSERT INTO `tblnhanvien` VALUES ('DUL01', '1300', 'Lª Hoµng Thuú D­¬ng', '0296265662', '', '711A01173821', '01/3/60', '0');
INSERT INTO `tblnhanvien` VALUES ('DUL02', '1318', 'L­ ThÞ Mü Duyªn', '0298109068', '', '711A02989113', '14/1/77', '0');
INSERT INTO `tblnhanvien` VALUES ('DUL03', '1604', 'Lª H÷u Dông', '0202100193', '', '711A02588432', '01/1/1967', '1');
INSERT INTO `tblnhanvien` VALUES ('DUL04', '1604', 'L©m Thanh Dòng', '0207140801', '', '711A06156143', '1981', '1');
INSERT INTO `tblnhanvien` VALUES ('DUL05', '1301', 'Lª ThÞ Mü Duyªn', '0204130565', '', '711A02586251', '30/4/1979', '0');
INSERT INTO `tblnhanvien` VALUES ('DUM01', '1508', 'Mai Thuú D­¬ng', '0296265580', '', '711A02593056', '12/1/69', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN01', '1100', 'NguyÔn ThÞ Kim Dung A', '0296265598', '', '711A07368633', '', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN02', '1100', 'NguyÔn ThÞ Kim Dung', '0296265599', '', '711A01177941', '03/5/59', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN03', '1306', 'NguyÔn TÊn §øc', '0296265516', '', '711A03541436', '12/12/66', '1');
INSERT INTO `tblnhanvien` VALUES ('DUN04', '1603', 'NguyÔn V¨n Duyªn', '0296265496', '', '711A05376661', '19/11/60', '1');
INSERT INTO `tblnhanvien` VALUES ('DUN05', '1612', 'NguyÔn ThÞ Mü Dung', '0296265579', '', '711A02600714', '', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN06', '1300', 'NguyÔn Thïy Dung', '0203315571', '', '711A02586212', '27/9/80', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN07', '1601', 'NguyÔn ThÞ Thïy D­¬ng', '0205264622', '', '711A03517463', '30/8/82', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN08', '1308', 'NguyÔn ThÞ Mü Duyªn', '0204270875', '', '711A01160313', '01/10/83', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN09', '1614', 'NguyÔn Vò Minh Duy', '0204356580', '', '711A01178341', '14/12/83', '1');
INSERT INTO `tblnhanvien` VALUES ('DUN10', '1309', 'NguyÔn Phan Tó Dung', '0205148111', '', '711A03447242', '20/3/73', '1');
INSERT INTO `tblnhanvien` VALUES ('DUN11', '1401', 'NguyÔn Ph­¬ng Dung', '0298109052', '', '711A20069746', '', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN12', '1100', 'NguyÔn ThÞ Mü Dung', '0208250109', '024740791', '711A07368933', '08/03/81', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN13', '1507', 'Ninh ThÞ Dung', '0206286311', '', '711A04019324', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('DUN14', '1604', 'NguyÔn Hång §øc', '0207250107', '', '711A07444206', '1979', '1');
INSERT INTO `tblnhanvien` VALUES ('DUT01', '1300', 'Tèng TrÇn Dòng', '0296265604', '', '711A06713786', '15/12/62', '1');
INSERT INTO `tblnhanvien` VALUES ('DUT02', '1304', 'TrÇn V¨n Dòng', '0296265665', '', '711A03447606', '17/3/60', '1');
INSERT INTO `tblnhanvien` VALUES ('DUT03', '1506', 'T¹ Ph­¬ng Dung', '0296265409', '', '711A01169691', '3/3/65', '0');
INSERT INTO `tblnhanvien` VALUES ('DUT04', '1506', 'TrÇn ThÞ Dung', '0296265574', '', '711A03517349', '17/02/62', '0');
INSERT INTO `tblnhanvien` VALUES ('DUT05', '1502', 'TrÇn ThÞ Ph­¬ng Dung', '0296265406', '', '711A03836809', '25/5/73', '0');
INSERT INTO `tblnhanvien` VALUES ('DUT06', '1200', 'TrÇn ThÞ Xu©n Dung', '0296265726', '', '711A03448428', '08/6/63', '0');
INSERT INTO `tblnhanvien` VALUES ('DUT07', '1304', 'TrÇn ThÞ Dung', '0203133677', '', '711A01327603', '23/06/80', '0');
INSERT INTO `tblnhanvien` VALUES ('DUT08', '1506', 'Tr­¬ng ThÞ Thïy D­¬ng', '0203133690', '', '711A01171371', '05/02/80', '0');
INSERT INTO `tblnhanvien` VALUES ('DUT09', '1603', 'TrÇn V¨n D­¬ng', '0202160967', '', '711A01153291', '22/1/72', '1');
INSERT INTO `tblnhanvien` VALUES ('DUT10', '1610', 'TrÞnh ThÞ §øc Dung', '0204101925', '', '711A03445152', '13/1/76', '0');
INSERT INTO `tblnhanvien` VALUES ('DUT11', '1608', 'Tr­¬ng C«ng Dòng', '0203315549', '', '711A01153304', '18/3/72', '1');
INSERT INTO `tblnhanvien` VALUES ('DUT12', '1610', 'TrÇn ThÞ Dung', '0207294626', '', '711A30300462', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('DUV01', '1610', 'Vâ Thµnh §øc', '0296265535', '', '711A01173994', '28/5/63', '1');
INSERT INTO `tblnhanvien` VALUES ('DUV02', '1606', 'Vò M¹nh §øc', '0296265424', '', '711A01145132', '20/12/65', '1');
INSERT INTO `tblnhanvien` VALUES ('DUV03', '1305', 'Vâ ThÞ Thïy Dung', '0205332655', '', '711A02600911', '14/7/81', '0');
INSERT INTO `tblnhanvien` VALUES ('ENH01', '1318', 'Hå Xu©n TuyÕn', '0296265547', '', '711A01174094', '09/03/71', '1');
INSERT INTO `tblnhanvien` VALUES ('ENN01', '1305', 'NguyÔn Thóc Béi HuyÒn', '0296265749', '', '711A02671254', '', '0');
INSERT INTO `tblnhanvien` VALUES ('ENT01', '1608', 'TrÇn Nga Hoµng BÝch Liªn', '0296265758', '', '711A02592297', '06/8/72', '0');
INSERT INTO `tblnhanvien` VALUES ('ENV01', '1403', 'Vò ThÞ LuyÕn', '0296265638', '', '711A02600043', '1/5/71', '0');
INSERT INTO `tblnhanvien` VALUES ('EPN01', '1401', 'NguyÔn Ngäc DiÖp', '0296265653', '', '711A03517388', '', '0');
INSERT INTO `tblnhanvien` VALUES ('EUT01', '1301', 'TrÇn V¨n RiÕu', '0296265545', '', '711A01170321', '15/05/65', '1');
INSERT INTO `tblnhanvien` VALUES ('EYT01', '1601', 'Th¸i ThÞ Mai YÕn', '0296265773', '', '711A02669893', '03/04/66', '0');
INSERT INTO `tblnhanvien` VALUES ('GAD01', '1314', '§µo ThÞ G¸i', '0204270884', '', '711A01157124', '07/01/81', '0');
INSERT INTO `tblnhanvien` VALUES ('GAH01', '1306', 'Hoµng ThÞ B¹ch Nga', '0296265683', '', '711A01153067', '02/12/72', '0');
INSERT INTO `tblnhanvien` VALUES ('GHN01', '1306', 'NguyÔn V¨n Ghªn', '0296265510', '', '711A02591239', '20/5/62', '1');
INSERT INTO `tblnhanvien` VALUES ('GIH01', '1605', 'Hoµng ThÞ Thu Giang', '0205264623', '', '711A02588717', '4/4/82', '0');
INSERT INTO `tblnhanvien` VALUES ('GIL01', '1220', 'Lª ThÞ H­¬ng Giang', '0204343536', '', '711A01175765', '23/5/79', '0');
INSERT INTO `tblnhanvien` VALUES ('GIP01', '1310', 'Ph¹m Tr­êng Giang', '0202161054', '', '711A01158803', '06/6/72', '1');
INSERT INTO `tblnhanvien` VALUES ('GIP02', '1507', 'Ph¹m ThÞ CÈm Giang', '0205264624', '', '711A03518431', '4/11/79', '0');
INSERT INTO `tblnhanvien` VALUES ('GIT01', '1305', 'TrÇn thÞ Tr­êng Giang', '0299097781', '', '711A03541109', '20/1/78', '0');
INSERT INTO `tblnhanvien` VALUES ('GIT02', '1303', 'TrÇn ThÞ Kim Giµu', '0204270864', '', '711A02599014', '21/12/82', '0');
INSERT INTO `tblnhanvien` VALUES ('GIV02', '1507', 'Vâ ThÞ G×', '0205264625', '', '711A02596501', '29/7/84', '0');
INSERT INTO `tblnhanvien` VALUES ('HAB01', '1303', 'Bïi Huy H¶o', '0296265456', '', '711A02597894', '10/4/58', '1');
INSERT INTO `tblnhanvien` VALUES ('HAB02', '1603', 'Bïi ThÞ Thu H»ng', '0298025754', '', '711A07369541', '14/4/67', '0');
INSERT INTO `tblnhanvien` VALUES ('HAB03', '1503', 'Bïi ThÞ Mü H¹nh', '0204069582', '', '711A03541179', '18/9/66', '0');
INSERT INTO `tblnhanvien` VALUES ('HAD01', '1300', '§Æng Xu©n H¶o', '0296265659', '', '711A01175851', '30/12/54', '1');
INSERT INTO `tblnhanvien` VALUES ('HAD03', '1508', 'Do·n ThÞ Hµ', '0298025759', '', '711A01156322', '04/1/69', '0');
INSERT INTO `tblnhanvien` VALUES ('HAD04', '1507', '§µo ThÞ H¹nh', '0203315569', '', '711A01174631', '2/10/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HAD05', '1315', '§ç Ng©n Hµ', '0206151379', '', '711A05507984', '5/2/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HAD06', '1601', '§oµn ThÞ §µo H¹nh', '0207105755', '', '711A06351007', '03/12/86', '0');
INSERT INTO `tblnhanvien` VALUES ('HAH03', '1610', 'Huúnh ThÞ Ngäc Hµ', '0204270868', '', '711A01170223', '17/06/81', '0');
INSERT INTO `tblnhanvien` VALUES ('HAH04', '1318', 'Huúnh ThÞ HiÕu H¹nh', '0203333824', '', '711A01173584', '19/04/81', '0');
INSERT INTO `tblnhanvien` VALUES ('HAH05', '1306', 'Huúnh Hå H¶i', '0205274469', '', '711A02591527', '5/5/78', '1');
INSERT INTO `tblnhanvien` VALUES ('HAH06', '1308', 'Hoµng ViÖt Hµ', '0204154519', '', '711A03517988', '5/8/78', '0');
INSERT INTO `tblnhanvien` VALUES ('HAH07', '1306', 'Hoµng ThÞ H¶i', '0204298497', '', '711A03448652', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('HAL01', '1400', 'Lª ThÞ H¶i', '0296265615', '', '711A07444521', '14/7/64', '0');
INSERT INTO `tblnhanvien` VALUES ('HAL02', '1318', 'Lª ThÞ Thanh Hµ', '0296265431', '', '711A03518522', '21/11/66', '0');
INSERT INTO `tblnhanvien` VALUES ('HAL03', '1604', 'L­u ThÞ Thóy Hµ', '0205089493', '', '711A03517621', '25/5/83', '0');
INSERT INTO `tblnhanvien` VALUES ('HAL04', '1506', 'L©m Thanh H»ng', '0202161025', '', '711A02590223', '15/4/70', '0');
INSERT INTO `tblnhanvien` VALUES ('HAL05', '1304', 'Lª H÷u Hµ', '0202160927', '', '711A02593493', '01/1/73', '0');
INSERT INTO `tblnhanvien` VALUES ('HAL07', '1507', 'Lª Hång H¶i', '0203174866', '', '711A01173439', '20/6/69', '0');
INSERT INTO `tblnhanvien` VALUES ('HAL08', '1506', 'Lý CÈm Hµ', '0205089492', '', '711A02596342', '31/5/78', '1');
INSERT INTO `tblnhanvien` VALUES ('HAL09', '1314', 'Lª ThÞ H¶i', '0202161020', '', '711A02671345', '27/2/74', '1');
INSERT INTO `tblnhanvien` VALUES ('HAL10', '1100', 'Lª ThÞ H¹nh', '0207140799', '111844661', '711A06156124', '18/01/85', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN02', '1305', 'NguyÔn Thu H»ng', '0296265405', '', '711A01144027', '25/6/70', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN03', '1306', 'NguyÔn Thanh H¶i', '0296265532', '', '711A03541333', '17/10/66', '1');
INSERT INTO `tblnhanvien` VALUES ('HAN04', '1308', 'NguyÔn Thanh H»ng', '0296265475', '', '711A01160743', '09/11/58', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN06', '1400', 'NguyÔn ThÞ H¶i', '0296265613', '', '711A05511368', '', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN07', '1403', 'NguyÔn Thanh H¶i', '0296265515', '', '711A04019442', '24/9/65', '1');
INSERT INTO `tblnhanvien` VALUES ('HAN08', '1318', 'NguyÔn ThÞ Hµ', '0296265404', '', '711A01143733', '25/5/71', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN09', '1613', 'NguyÔn Ph­íc H¶i', '0297120896', '', '711A00906723', '05/7/66', '1');
INSERT INTO `tblnhanvien` VALUES ('HAN10', '1607', 'NguyÔn Ngäc Hµ', '0298109064', '', '711A01150767', '07/5/76', '1');
INSERT INTO `tblnhanvien` VALUES ('HAN11', '1306', 'NguyÔn Träng H¹nh', '0298109069', '', '711A02590641', '25/5/76', '1');
INSERT INTO `tblnhanvien` VALUES ('HAN12', '1606', 'NguyÔn ThÞ H»ng', '0205383481', '', '711A03447763', '1979', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN16', '1507', 'NguyÔn ThÞ BÝch H¹nh', '0201092987', '', '711A01252388', '23/12/77', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN17', '1303', 'NguyÔn BÝch Hµ', '0202100192', '', '711A01146881', '09/07/68', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN18', '1507', 'NguyÔn Ngäc Hµ', '0202161002', '', '711A01174549', '07/7/80', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN19', '1507', 'NguyÔn ThÞ Ngäc H¹nh', '0205080886', '', '711A01174291', '2/7/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN20', '1400', 'NguyÔn ThÞ H»ng', '0206151381', '', '711A05682329', '1/6/83', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN21', '1607', 'NguyÔn ThÞ LÖ H»ng', '0205264626', '', '711A02598322', '29/6/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN22', '1304', 'NguyÔn LÖ H»ng', '0202160923', '', '711A01145195', '', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN24', '1607', 'NguyÔn ThÞ Hång H¹nh', '0204320924', '', '711A01150853', '02/06/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN25', '1403', 'Ng« NguyÔn Kim H»ng', '0203174871', '', '711A02670334', '23/10/77', '0');
INSERT INTO `tblnhanvien` VALUES ('HAN27', '1310', 'NguyÔn H÷u HËu', '0203174902', '', '711A05376622', '8/11/69', '1');
INSERT INTO `tblnhanvien` VALUES ('HAN28', '1310', 'NguyÔn V¨n H¶i', '0205080882', '', '711A03446298', '1977', '1');
INSERT INTO `tblnhanvien` VALUES ('HAN29', '1303', 'NGuyÔn Thôy V©n Hµ', '0207105773', '', '711A06350713', '13/7/85', '0');
INSERT INTO `tblnhanvien` VALUES ('HAP01', '1307', 'Ph¹m Xu©n H¹nh', '0296265589', '', '711A03446881', '09/9/56', '1');
INSERT INTO `tblnhanvien` VALUES ('HAP02', '1502', 'Ph¹m ThÞ HËu', '0298109066', '', '711A02591364', '09/6/77', '0');
INSERT INTO `tblnhanvien` VALUES ('HAP04', '1403', 'Phan ThÞ Thanh H¶i', '0299097784', '', '711A02600571', '', '0');
INSERT INTO `tblnhanvien` VALUES ('HAP05', '1506', 'Ph¹m ThÞ Ngäc H¹nh', '0201092985', '', '711A01171285', '21/4/78', '0');
INSERT INTO `tblnhanvien` VALUES ('HAP06', '1310', 'Ph¹m Phóc H¶i', '4896021043', '', '711A02592313', '25/6/61', '1');
INSERT INTO `tblnhanvien` VALUES ('HAP07', '1310', 'Ph¹m ThÞ Hµ', '0207214967', '', '711A07908812', '1982', '1');
INSERT INTO `tblnhanvien` VALUES ('HAT02', '1310', 'TrÇn ThÞ H»ng', '0203315598', '', '711A03445941', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('HAT03', '1300', 'TrÇn Song H¶i', '0296265658', '', '711A01175805', '27/10/57', '1');
INSERT INTO `tblnhanvien` VALUES ('HAT04', '1308', 'TrÇn Ngäc H©n', '0204270899', '', '711A02595923', '21/9/1974', '0');
INSERT INTO `tblnhanvien` VALUES ('HAT06', '1305', 'TrÇn ThÞ H»ng', '0296265608', '', '711A01144706', '10/2/58', '0');
INSERT INTO `tblnhanvien` VALUES ('HAT07', '1606', 'TrÇn ThÞ Thanh Hµ', '4596012329', '', '711A01145034', '12/2/68', '0');
INSERT INTO `tblnhanvien` VALUES ('HAT08', '1400', 'TrÇn ThÞ Nh©n H¹nh', '0204154506', '', '711A12672292', '1978', '0');
INSERT INTO `tblnhanvien` VALUES ('HAT09', '1304', 'TrÇn ThÞ Hµ', '0207331853', '', '711A07741601', '1986', '0');
INSERT INTO `tblnhanvien` VALUES ('HAV01', '1401', 'Vò ThÞ H¹nh', '0296265646', '', '711A02591676', '061158', '0');
INSERT INTO `tblnhanvien` VALUES ('HAV02', '1318', 'Vò ThÞ Ngäc H¹nh', '0203315558', '', '711A03447424', '26/4/81', '0');
INSERT INTO `tblnhanvien` VALUES ('HED01', '1605', '§inh TrÝ Hªn', '0202160937', '', '711A02588483', '11/3/73', '0');
INSERT INTO `tblnhanvien` VALUES ('HIB01', '1305', 'Bïi ThÞ HiÕu', '0205264627', '', '711A01182763', '1/1/84', '0');
INSERT INTO `tblnhanvien` VALUES ('HID01', '1310', '§oµn Hå Trung HiÕu', '0206151382', '', '711A03448261', '2/5/82', '1');
INSERT INTO `tblnhanvien` VALUES ('HID02', '1303', '§Æng B¸ HiÒn', '0206177207', '', '711A02989042', '7/11/76', '1');
INSERT INTO `tblnhanvien` VALUES ('HID03', '1317', '§ç ChÝ HiÕu', '0203333831', '', '711A01173852', '9/2/76', '1');
INSERT INTO `tblnhanvien` VALUES ('HIH01', '1314', 'Huúnh Mü HiÖp', '0203315572', '', '711A01156764', '25/05/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HIH02', '1604', 'Hoµng HiÖp', '0203315551', '', '711A02588341', '2/8/77', '1');
INSERT INTO `tblnhanvien` VALUES ('HIH03', '1507', 'Hoµng ThÞ Hiªn', '0206316859', '', '711A05447684', '2/3/1984', '0');
INSERT INTO `tblnhanvien` VALUES ('HIL03', '1309', 'Lª ThiÖn HiÕu', '5003005584', '', '711A02592474', '', '1');
INSERT INTO `tblnhanvien` VALUES ('HIL04', '1614', 'Lª V¨n HiÕu', '0202161090', '', '711A01177977', '11/8/73', '1');
INSERT INTO `tblnhanvien` VALUES ('HIL05', '1306', 'Lý Trung HiÕu', '0207140811', '', '711A06349972', '1986', '1');
INSERT INTO `tblnhanvien` VALUES ('HIN01', '1200', 'NguyÔn V¨n HiÖu', '0296265705', '', '711A01177567', '12/7/57', '1');
INSERT INTO `tblnhanvien` VALUES ('HIN02', '1601', 'NguyÔn Thanh HiÒn', '0296265415', '', '711A02588672', '30/5/58', '1');
INSERT INTO `tblnhanvien` VALUES ('HIN03', '1308', 'NguyÔn ThÞ HiÖn', '0296265476', '', '711A02596102', '7/7/64', '0');
INSERT INTO `tblnhanvien` VALUES ('HIN04', '1502', 'NguyÔn ThÞ Ngäc HiÒn', '0298109071', '', '711A01173324', '19/6/76', '0');
INSERT INTO `tblnhanvien` VALUES ('HIN05', '1506', 'NguyÔn ThÞ V©n HiÕu', '0202161032', '', '711A01254651', '1/9/79', '0');
INSERT INTO `tblnhanvien` VALUES ('HIP01', '1506', 'Phan V¨n HiÒn', '0298025760', '', '711A02590171', '26/7/70', '1');
INSERT INTO `tblnhanvien` VALUES ('HIP02', '1608', 'Ph¹m V¨n HiÕn', '0296094151', '', '711A05376713', '7/5/61', '1');
INSERT INTO `tblnhanvien` VALUES ('HIP03', '1300', 'Ph¹m Träng HiÖp', '0205182006', '', '711A01152833', '28/6/82', '1');
INSERT INTO `tblnhanvien` VALUES ('HIT01', '1314', 'TrÇn ThÞ Thu HiÒn', '0205274471', '', '711A02601784', '2/4/83', '0');
INSERT INTO `tblnhanvien` VALUES ('HIT02', '1613', 'Tr­¬ng ThÞ HiÒn', '0204130567', '', '711A04019427', '11/10/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HIV01', '1603', 'Vò Trung HiÕu', '0206177208', '', '711A03446992', '10/12/78', '1');
INSERT INTO `tblnhanvien` VALUES ('HOD01', '1601', 'D­¬ng HiÖp Hå', '0299051660', '', '711A01173103', '30/4/70', '1');
INSERT INTO `tblnhanvien` VALUES ('HOH01', '1610', 'Hoµng Kim Hoµng', '0296265470', '', '711A03444973', '26/12/65', '1');
INSERT INTO `tblnhanvien` VALUES ('HOH03', '1304', 'Hoµng ThÞ Hoµn', '0296265700', '', '711A01169558', '01/2/62', '0');
INSERT INTO `tblnhanvien` VALUES ('HOH04', '1503', 'Hµ ThÞ Kim Hång', '0296265400', '', '711A01151265', '10/11/65', '0');
INSERT INTO `tblnhanvien` VALUES ('HOH05', '1403', 'Huúnh ThÞ Hång', '0296265569', '', '711A03518033', '15/2/62', '0');
INSERT INTO `tblnhanvien` VALUES ('HOL01', '1403', 'Lª YÕn Hång', '0299097796', '', '711A02590211', '01/4/73', '0');
INSERT INTO `tblnhanvien` VALUES ('HOL02', '1506', 'Lª ThÞ Kim Hoµng', '0201092986', '', '711A02589976', '28/4/79', '0');
INSERT INTO `tblnhanvien` VALUES ('HOL03', '1100', 'L¹i ThÞ LÖ Hoa', '0202161069', '024097824', '711A01178495', '05/08/72', '0');
INSERT INTO `tblnhanvien` VALUES ('HON04', '1400', 'NguyÔn ThÞ Hång', '0296265610', '', '711A05507827', '01/05/59', '0');
INSERT INTO `tblnhanvien` VALUES ('HON06', '1506', 'NguyÔn ThÞ Mai Hång', '0204101928', '', '711A03517364', '10/10/1981', '0');
INSERT INTO `tblnhanvien` VALUES ('HON07', '1100', 'NguyÔn ThÞ Hßa', '0299059267', '', '711A01178302', '21/1/63', '0');
INSERT INTO `tblnhanvien` VALUES ('HON08', '1303', 'NguyÔn Mai Hoµ', '0203174854', '', '711A02599302', '23/9/77', '1');
INSERT INTO `tblnhanvien` VALUES ('HON09', '1318', 'NguyÔn Ph­¬ng Hång', '0203174877', '', '711A02598464', '1976', '0');
INSERT INTO `tblnhanvien` VALUES ('HON10', '1606', 'Ng« ThÞ CÈm Hoa', '0202161014', '', '711A03517503', '11/10/75', '0');
INSERT INTO `tblnhanvien` VALUES ('HON11', '1613', 'NguyÔn Minh Hoµng', '0205370019', '', '711A02590168', '12/11/83', '1');
INSERT INTO `tblnhanvien` VALUES ('HON12', '1310', 'NguyÔn ThÞ BÝch Hång', '0203333830', '', '711A03445907', '23/7/76', '0');
INSERT INTO `tblnhanvien` VALUES ('HON13', '1504', 'NguyÔn ThÞ ThuÇn Hoa', '0199063083', '', '711A20169573', '28/11/61', '0');
INSERT INTO `tblnhanvien` VALUES ('HON14', '1301', 'NguyÔn ThÞ Ph­¬ng Hoµi', '0205264628', '', '711A02586733', '1979', '0');
INSERT INTO `tblnhanvien` VALUES ('HON15', '1306', 'NguyÔn ThÞ Hoµi', '0207448888', '', '711A13549762', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('HOP01', '1303', 'Ph¹m ThÞ Hång', '0296265452', '', '711A02598641', '15/7/58', '0');
INSERT INTO `tblnhanvien` VALUES ('HOP02', '1310', 'Phan TÊn Hoµng', '0202161058', '', '711A01158688', '24/6/72', '1');
INSERT INTO `tblnhanvien` VALUES ('HOT02', '1306', 'TrÇn Thanh Hßa', '0296265506', '', '711A02590835', '20/10/63', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT03', '1100', 'TrÇn ThÞ Hoµn', '0296265603', '', '711A05470524', '05/01/63', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT06', '1300', 'TrÇn DiÔm Hoµng', '0203174890', '', '711A01156713', '1/1/80', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT07', '1309', 'Tr­¬ng ThÞ Kim Hång', '0204270879', '', '711A02592021', '04/02/81', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT08', '1309', 'TrÇn ThÞ Thu Hång', '0204270878', '', '711A01154662', '04/10/83', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT09', '1601', 'Trµ ThÞ thu Hång', '0203315561', '', '711A02587437', '03/10/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT11', '1607', 'T« ThÞ Hång', '0202160958', '', '711A02598953', '', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT12', '1506', 'TrÇn ThÞ Hoa', '0205383482', '', '711A02989073', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT13', '1507', 'Tr­¬ng ThÞ Hîi', '0206389620', '', '711A05896547', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('HOT14', '1506', 'TrÞnh Thu Hång', '0207448893', '', '711A11741954', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('HOV01', '1318', 'Vâ ThÞ Hoa', '0296265573', '', '711A01156231', '28/7/59', '0');
INSERT INTO `tblnhanvien` VALUES ('HOV02', '1506', 'Vâ ThÞ ¸nh Hång', '0206121626', '', '711A03517266', '7/10/85', '0');
INSERT INTO `tblnhanvien` VALUES ('HUB01', '1507', 'Bïi Phi Hïng', '0203174888', '', '711A02596271', '31/3/75', '1');
INSERT INTO `tblnhanvien` VALUES ('HUC01', '1301', 'Ch©u TrÇn ChÊn Huy', '0205370020', '', '711A01183486', '7/3/79', '1');
INSERT INTO `tblnhanvien` VALUES ('HUD02', '1507', '§inh ThÞ Kim H­¬ng', '0203315568', '', '711A02596414', '15/2/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HUD03', '1507', 'D­¬ng ThÞ Thu HuyÒn', '0204298504', '', '711A02596662', '25/9/77', '0');
INSERT INTO `tblnhanvien` VALUES ('HUD04', '1614', '§Æng V¨n H­ng', '0203133691', '', '711A03448416', '1/8/76', '1');
INSERT INTO `tblnhanvien` VALUES ('HUD05', '1401', '§Æng V¨n H­ng', '0203174912', '', '711A01159865', '19/5/77', '1');
INSERT INTO `tblnhanvien` VALUES ('HUD07', '1612', '§ång §øc H­ng', '0203315550', '', '711A00814948', '1/5/76', '1');
INSERT INTO `tblnhanvien` VALUES ('HUD10', '1309', '§Æng V¨n Hïng', '0207294624', '', '711A12675654', '1979', '0');
INSERT INTO `tblnhanvien` VALUES ('HUH01', '1506', 'Huúnh ThÞ Kim H­¬ng', '0203315577', '', '711A03836469', '17/8/73', '0');
INSERT INTO `tblnhanvien` VALUES ('HUH02', '1507', 'Hå Quèc H­ng', '0203174905', '', '711A01173363', '18/6/77', '1');
INSERT INTO `tblnhanvien` VALUES ('HUL01', '1100', 'Lª M¹nh Hïng', '0296265590', '', '711A03542033', '06/5/59', '1');
INSERT INTO `tblnhanvien` VALUES ('HUL03', '1504', 'L­u Anh Hïng', '0296265732', '', '711A03445341', '16/7/54', '1');
INSERT INTO `tblnhanvien` VALUES ('HUL04', '1506', 'Lª M¹nh Hïng', '0205206239', '', '711A03517376', '1982', '1');
INSERT INTO `tblnhanvien` VALUES ('HUL05', '1304', 'Lª ThÞ Minh H­¬ng', '0296265539', '', '711A02671187', '14/11/69', '0');
INSERT INTO `tblnhanvien` VALUES ('HUL06', '1507', 'La ThÞ HUyÒn', '0202099347', '', '711A02596583', '01/1/79', '0');
INSERT INTO `tblnhanvien` VALUES ('HUL07', '1607', 'Lª BÝch H­¬ng', '0206121627', '', '711A02589045', '6/1/85', '0');
INSERT INTO `tblnhanvien` VALUES ('HUL08', '1503', 'Lª Duy H­ng', '5396008996', '', '711A01151435', '26/10/68', '1');
INSERT INTO `tblnhanvien` VALUES ('HUL09', '1507', 'Lª ThÞ Thanh H­¬ng', '0204154514', '', '711A01173781', '9/10/79', '0');
INSERT INTO `tblnhanvien` VALUES ('HUM02', '1607', 'M· Hoa Hïng', '0202161061', '', '711A01170602', '20/2/71', '1');
INSERT INTO `tblnhanvien` VALUES ('HUN01', '1100', 'NguyÔn Minh Hïng', '0202161072', '', '711A01177425', '1976', '1');
INSERT INTO `tblnhanvien` VALUES ('HUN02', '1200', 'NguyÔn TuÊn H­ng', '0296265719', '', '711A03542337', '16/5/62', '1');
INSERT INTO `tblnhanvien` VALUES ('HUN03', '1302', 'NguyÔn ThÞ H­ëng', '0296265611', '', '711A01169182', '30/8/62', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN04', '1310', 'NguyÔn Xu©n Hïng', '0296265688', '', '711A01158606', '30/5/55', '1');
INSERT INTO `tblnhanvien` VALUES ('HUN05', '1304', 'NguyÔn ThÞ H­êng', '0297120893', '', '711A01169325', '26/5 /70', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN08', '1304', 'NguyÔn §ç Lan H­¬ng', '0299059266', '', '711A02602361', '21/9/71', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN09', '1303', 'NguyÔn ThÞ Lan H­¬ng', '0201092971', '', '711A02598989', '23/10/74', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN10', '1601', 'Ng« NguyÔn Kim H­êng', '0201092967', '', '711A02669902', '1976', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN11', '1220', 'NguyÔn Anh Hïng', '0202161087', '', '711A03518116', '04/10/63', '1');
INSERT INTO `tblnhanvien` VALUES ('HUN12', '1306', 'NguyÔn Th¸i Hïng', '0206151383', '', '711A03541391', '4/6/85', '1');
INSERT INTO `tblnhanvien` VALUES ('HUN13', '1301', 'NguyÔn ThÞ H­êng', '0203174889', '', '711A02586551', '14/8 /76', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN14', '1612', 'NguyÔn ThÞ Ngäc H­¬ng', '0204320928', '', '711A01154726', '08/05/81', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN15', '1506', 'NguyÔn ThÞ H­ng', '0202161026', '', '711A02589906', '', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN16', '1306', 'NguyÔn ThÞ HuÖ', '0204069592', '', '711A02590941', '2/6/79', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN17', '1502', 'NguyÔn ThÞ Lan H­¬ng', '0204343543', '', '711A01173218', '11/7/81', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN18', '1308', 'Ng« §øc Minh Huy', '0202160976', '', '711A03445022', '20/3/72', '1');
INSERT INTO `tblnhanvien` VALUES ('HUN19', '1601', 'NguyÔn Ngäc H­ng', '0202160938', '', '711A02587582', '24/2/76', '1');
INSERT INTO `tblnhanvien` VALUES ('HUN20', '1301', 'NguyÔn ThÞ Thu H­¬ng', '0205206235', '', '711A01170345', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN21', '1613', 'NguyÔn ThÞ HuÖ', '0206369518', '', '711A05470661', '1974', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN22', '1613', 'NguyÔn ThÞ Thu H­êng', '0207140795', '', '711A06351231', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN23', '1506', 'NguyÔn ThÞ BÝch Huyªn', '0207214970', '', '711A05681597', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN24', '1304', 'NguyÔn ThÞ Thanh HuyÒn', '0207400286', '', '711A09817121', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('HUN25', '1606', 'Ng« ThÞ Thanh HuyÒn', '0207400287', '', '711A10715405', '1979', '0');
INSERT INTO `tblnhanvien` VALUES ('HUP01', '1302', 'Phan ThÞ HuÖ', '0296265441', '', '711A01327579', '03/02/62', '0');
INSERT INTO `tblnhanvien` VALUES ('HUP02', '1306', 'Ph¹m ThÞ Thu H­¬ng', '0200054827', '', '711A02591002', '19/10/62', '0');
INSERT INTO `tblnhanvien` VALUES ('HUP03', '1301', 'Ph¹m ThÞ Gi¸ng H­¬ng', '0201092975', '', '711A01254017', '30/6/1972', '0');
INSERT INTO `tblnhanvien` VALUES ('HUP04', '1607', 'Phan ThÞ Xu©n H­¬ng', '0202160961', '', '711A01150834', '28/11/79', '0');
INSERT INTO `tblnhanvien` VALUES ('HUP05', '1316', 'Phan L­¬ng Huy', '0204069591', '', '711A01154362', '14*9/79', '1');
INSERT INTO `tblnhanvien` VALUES ('HUP06', '1608', 'Ph¹m Quèc Hïng', '0206177209', '', '711A03446808', '', '1');
INSERT INTO `tblnhanvien` VALUES ('HUQ02', '1303', '§ç Quèc Hïng', '0202100190', '', '711A02989003', '18/7/67', '1');
INSERT INTO `tblnhanvien` VALUES ('HUT01', '1306', 'TrÇn ThÞ Thu H­¬ng', '0296265507', '', '711A03448222', '25/3/61', '0');
INSERT INTO `tblnhanvien` VALUES ('HUT02', '1400', 'TrÇn ThÞ BÝch HuÖ', '0296265612', '', '711A05742541', '14/11/58', '0');
INSERT INTO `tblnhanvien` VALUES ('HUT04', '1310', 'TrÇn C«ng Hu©n', '0201092982', '', '711A01158664', '1973', '1');
INSERT INTO `tblnhanvien` VALUES ('HUT05', '1314', 'TrÇn Ngäc H­¬ng', '0203133688', '', '711A01156677', '16/04/81', '0');
INSERT INTO `tblnhanvien` VALUES ('HUV01', '1301', 'V¨n ThÞ Thu H­¬ng', '0296265562', '', '711A01169861', '30/11/68', '0');
INSERT INTO `tblnhanvien` VALUES ('HUV02', '1403', 'Vò Thanh H­¬ng', '0298025751', '', '711A02600662', '14/09/68', '0');
INSERT INTO `tblnhanvien` VALUES ('HUV03', '1400', 'Vò Hoµng V©n HuÖ', '0205332656', '', '711A05682207', '9/4/82', '0');
INSERT INTO `tblnhanvien` VALUES ('HUV04', '1403', 'Vâ Kim H­¬ng', '0202161044', '', '711A02600611', '7/6/80', '0');
INSERT INTO `tblnhanvien` VALUES ('HUV05', '1507', 'Vâ TÊn H­ëng', '0204298502', '', '711A01254292', '20/7/75', '1');
INSERT INTO `tblnhanvien` VALUES ('HUV06', '1613', 'Vâ ThÞ Thu H­¬ng', '0207448895', '', '711A10715763', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('HUŸ01', '1301', '§Æng ThÞ H­¬ng', '0204343542', '', '711A02586599', '19/2 /82', '0');
INSERT INTO `tblnhanvien` VALUES ('ICN01', '1401', 'NguyÔn Ngäc BÝch Liªn', '0296265654', '', '711A01160021', '28/4/70', '0');
INSERT INTO `tblnhanvien` VALUES ('IHN01', '1401', 'NguyÔn ThÞ Kim Chi', '0296265655', '', '711A01159995', '1957', '0');
INSERT INTO `tblnhanvien` VALUES ('INN02', '1403', 'NguyÔn ThÞ ChÝn', '0296265762', '', '711A03444718', '', '0');
INSERT INTO `tblnhanvien` VALUES ('INT01', '1100', 'TrÞnh ThÞ B×nh', '0296265730', '', '711A03448522', '25/1/59', '0');
INSERT INTO `tblnhanvien` VALUES ('KHH01', '1501', 'Huúnh ThÞ Tè Khanh', '0206389621', '', '711A05508109', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('KHL01', '1310', 'Lª ThÞ Hång Kh¸nh', '0202161015', '', '711A03446156', '', '0');
INSERT INTO `tblnhanvien` VALUES ('KHL02', '1306', 'L­u KÝnh Kh­¬ng', '0202160986', '', '711A02591112', '05/11/71', '1');
INSERT INTO `tblnhanvien` VALUES ('KHL03', '1306', 'Lª Ph¹m Uyªn Kha', '0204298501', '', '711A03448683', '09/09/83', '0');
INSERT INTO `tblnhanvien` VALUES ('KHN01', '1503', 'NguyÔn ThÞ Minh Khuyªn', '0296265554', '', '711A01327406', '18/1/63', '0');
INSERT INTO `tblnhanvien` VALUES ('KHN04', '1310', 'NguyÔn §øc Khang', '0202100204', '', '711A05376634', '14/6/1968', '1');
INSERT INTO `tblnhanvien` VALUES ('KHN05', '1318', 'NguyÔn Quang Khiªn', '5297020341', '', '711A01144985', '14/5/60', '1');
INSERT INTO `tblnhanvien` VALUES ('KHN07', '1304', 'NguyÔn TÊn Khang', '0297042238', '', '711A01169364', '22/05/70', '1');
INSERT INTO `tblnhanvien` VALUES ('KHP02', '1318', 'Phan MËu Kh¸nh', '0296265551', '', '711A02669969', '08/08/58', '1');
INSERT INTO `tblnhanvien` VALUES ('KHP04', '1314', 'Phïng §¨ng Khoa', '0298109062', '', '711A03518182', '10/11/65', '1');
INSERT INTO `tblnhanvien` VALUES ('KHP05', '1607', 'Ph¹m §¨ng Khoa', '0298109070', '', '711A02598137', '24/12/77', '1');
INSERT INTO `tblnhanvien` VALUES ('KHP07', '1501', 'Ph¹m Ngäc Kh¸nh', '0206151384', '', '711A02989066', '23/1/79', '1');
INSERT INTO `tblnhanvien` VALUES ('KHQ01', '1315', 'Qu¸ch TuÊn Kh¶i', '0298120560', '', '711A03518261', '20/7/58', '1');
INSERT INTO `tblnhanvien` VALUES ('KHT01', '1304', 'TrÇn DiÖp Khoa', '0202100208', '', '711A02671475', '01/01/72', '1');
INSERT INTO `tblnhanvien` VALUES ('KHT03', '1304', 'TrÇn ThÞ KhÈm', '0203174884', '', '711A03836512', '1/1/81', '0');
INSERT INTO `tblnhanvien` VALUES ('KHT04', '1503', 'Tr­¬ng ThÞ Vµnh Khuyªn', '0203333806', '', '711A02595362', '29/2/72', '0');
INSERT INTO `tblnhanvien` VALUES ('KHT05', '1605', 'TrÇn H÷u Toµn Khoa', '0202160943', '', '711A03517482', '19/2/74', '1');
INSERT INTO `tblnhanvien` VALUES ('KHT06', '1601', 'T«n ThÊt TuÊn Khiªm', '0203174876', '', '711A02587504', '20/11/71', '1');
INSERT INTO `tblnhanvien` VALUES ('KHV02', '1310', 'Vò KÕ Kh«i', '0204298496', '', '711A01158949', '11/07/83', '1');
INSERT INTO `tblnhanvien` VALUES ('KHV03', '1503', 'Vâ TuÊn Khoa', '0202160915', '', '711A02595237', '19/3/1975', '1');
INSERT INTO `tblnhanvien` VALUES ('KIL01', '1601', 'L©m ThÞ Mü Kim', '0203315559', '', '711A01145417', '14/2/81', '0');
INSERT INTO `tblnhanvien` VALUES ('KIL02', '1310', 'Lý V¨n Ki¸', '0205148114', '', '711A01254975', '1973', '1');
INSERT INTO `tblnhanvien` VALUES ('KIT01', '1401', 'TriÖu ThÞ Ngäc KiÒu', '0296265645', '', '711A01159814', '23/4/60', '0');
INSERT INTO `tblnhanvien` VALUES ('LAD01', '1301', '§Æng ThÞ Thanh Lan', '0297048010', '', '711A04478408', '25/8/70', '0');
INSERT INTO `tblnhanvien` VALUES ('LAD02', '1100', '§Æng thÞ Hoµi Lan', '0298112395', '', '711A01178732', '22/5/77', '0');
INSERT INTO `tblnhanvien` VALUES ('LAH01', '1300', 'Huúnh ThÞ H­¬ng Lan', '0202160933', '', '711A02588471', '23/2/78', '0');
INSERT INTO `tblnhanvien` VALUES ('LAL01', '1304', 'L©m Ngäc L¾m', '0298025781', '', '711A04478332', '08/8/67', '1');
INSERT INTO `tblnhanvien` VALUES ('LAL02', '1601', 'Lª ThÞ Kim Lan', '0204270863', '', '711A02593348', '21/10/83', '0');
INSERT INTO `tblnhanvien` VALUES ('LAN01', '1310', 'NguyÔn ThÞ Lan', '0296265703', '', '711A03446089', '24/10/68', '0');
INSERT INTO `tblnhanvien` VALUES ('LAN02', '1305', 'NguyÔn TuyÕt Lan', '0296265402', '', '711A01143784', '01/05/62', '0');
INSERT INTO `tblnhanvien` VALUES ('LAN03', '1318', 'NguyÔn H÷u V­¬ng Lan', '0296265459', '', '711A02598274', '17/4/72', '0');
INSERT INTO `tblnhanvien` VALUES ('LAN04', '1309', 'NguyÔn ThÞ Thu Lan', '0206121628', '', '711A02670677', '20/7/85', '0');
INSERT INTO `tblnhanvien` VALUES ('LAN05', '1506', 'NguyÔn ThÞ Hång Lam', '0203315578', '', '711A02670113', '13/09/81', '0');
INSERT INTO `tblnhanvien` VALUES ('LAN06', '1301', 'NguyÔn ThÞ Hång Lan', '0203174865', '', '711A02670614', '30/4/77', '0');
INSERT INTO `tblnhanvien` VALUES ('LAP01', '1506', 'Ph¹m Hoµng L©m', '0297120895', '', '711A01171194', '25/11/68', '1');
INSERT INTO `tblnhanvien` VALUES ('LAP02', '1612', 'Huúnh ThÞ Kim Lan', '0201092979', '', '711A01154484', '1978', '0');
INSERT INTO `tblnhanvien` VALUES ('LAT01', '1315', 'T¹ ThÞ lan', '0204270894', '', '711A02590365', '1978', '0');
INSERT INTO `tblnhanvien` VALUES ('LEH01', '1504', 'Huúnh ThÞ Ngäc LÖ', '0296265735', '', '711A01170672', '040358', '0');
INSERT INTO `tblnhanvien` VALUES ('LEN01', '1200', 'NguyÔn §øc LÕnh', '0296265412', '', '711A04478214', '1/5/57', '1');
INSERT INTO `tblnhanvien` VALUES ('LEP01', '1306', 'Ph¹m ThÞ Hång Len', '0202191868', '', '711A05896638', '28/3/1975', '0');
INSERT INTO `tblnhanvien` VALUES ('LIC01', '1508', 'Cao ThÞ LiÔu', '6004000405', '', '711A02593193', '1975', '0');
INSERT INTO `tblnhanvien` VALUES ('LID01', '1607', '§µm ThÞ CÈm Linh', '0200054823', '', '711A01146866', '1970', '0');
INSERT INTO `tblnhanvien` VALUES ('LID02', '1601', '§Æng LiÖu', '0203333834', '', '711A01173636', '14/10/71', '1');
INSERT INTO `tblnhanvien` VALUES ('LID03', '1100', '§ç thÞ Kim Liªn', '0298046334', '', '711A03448585', '1975', '0');
INSERT INTO `tblnhanvien` VALUES ('LIH01', '1507', 'Hoµng ThÞ Kim Liªn', '0206369519', '', '711A05447653', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('LIL02', '1606', 'L­¬ng ThÞ Kim Liªn', '0200054822', '', '711A04371586', '1969', '0');
INSERT INTO `tblnhanvien` VALUES ('LIL03', '1309', 'Lª ChÝ Linh', '0202160969', '', '711A02670783', '20/06/70', '1');
INSERT INTO `tblnhanvien` VALUES ('LIL04', '1507', 'Lª ThÞ Ngäc Linh', '0205109960', '', '711A01147167', '18/12/80', '0');
INSERT INTO `tblnhanvien` VALUES ('LIL05', '1606', 'L©m TrÇn BÝch Liªn', '0207105756', '', '711A06350236', '27/9/86', '0');
INSERT INTO `tblnhanvien` VALUES ('LIN01', '1607', 'NguyÔn ThÞ Kim Liªn', '0296265448', '', '711A02599448', '29/10/57', '0');
INSERT INTO `tblnhanvien` VALUES ('LIN04', '1303', 'NguyÔn Kim LÜnh', '0298025752', '', '711A01150731', '19/2/63', '0');
INSERT INTO `tblnhanvien` VALUES ('LIN05', '1503', 'NguyÔn Thôy Thïy Linh', '0203315575', '', '711A01152727', '17/10/81', '0');
INSERT INTO `tblnhanvien` VALUES ('LIN06', '1503', 'NguyÔn ThÞ Mü Linh', '0205264631', '', '711A01183186', '25/9/84', '0');
INSERT INTO `tblnhanvien` VALUES ('LIN07', '1608', 'NguyÔn ThÞ Hång Ngäc Liªn', '0206177211', '', '711A03447861', '29/10/85', '0');
INSERT INTO `tblnhanvien` VALUES ('LIP01', '1603', 'Ph¹m ThÞ BÝch Liªn', '0205264632', '', '711A02601023', '25/5/83', '0');
INSERT INTO `tblnhanvien` VALUES ('LIP02', '1300', 'Ph¹m Thuú Linh', '0202160944', '', '711A01177191', '1971', '0');
INSERT INTO `tblnhanvien` VALUES ('LIP04', '1403', 'Ph¹m ChÝ Linh', '0206369520', '', '711A05681534', '1984', '1');
INSERT INTO `tblnhanvien` VALUES ('LIT01', '1401', 'TrÞnh ThÞ D­¬ng LiÔu', '0296265650', '', '711A03445413', '01/9/60', '0');
INSERT INTO `tblnhanvien` VALUES ('LIT02', '1314', 'T« Huy LiÖu', '0296265568', '', '711A01155749', '6/4/55', '1');
INSERT INTO `tblnhanvien` VALUES ('LIT03', '1301', 'TrÇn ThÞ LIªn', '0203333827', '', '711A01170124', '25/3/81', '0');
INSERT INTO `tblnhanvien` VALUES ('LIT05', '1608', 'Tr­¬ng ThÞ Kim Liªn', '0207465116', '', '711A11742224', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('LOD01', '1220', '§ç §øc Lîi', '0296265410', '', '711A03518131', '', '1');
INSERT INTO `tblnhanvien` VALUES ('LOD02', '1314', '§oµn ThÞ Ph­¬ng Loan', '0205264633', '', '711A02602673', '25/8/83', '0');
INSERT INTO `tblnhanvien` VALUES ('LOH01', '1403', 'Hoµng ThÞ Long', '0296265699', '', '711A03444882', '20/10/63', '0');
INSERT INTO `tblnhanvien` VALUES ('LOH03', '1304', 'Huúnh ThÞ Léc', '0298025779', '', '711A03447527', '28/9/68', '0');
INSERT INTO `tblnhanvien` VALUES ('LOH04', '1303', 'Hµ ThÞ Tè Loan', '0207214963', '', '711A08242363', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('LOL02', '1400', 'Lª ThÞ Lîi', '0296265616', '', '711A12672845', '20/9/58', '0');
INSERT INTO `tblnhanvien` VALUES ('LOL03', '1603', 'Lª Thanh Loan', '0203315591', '', '711A01153407', '10/10/77', '0');
INSERT INTO `tblnhanvien` VALUES ('LOL04', '1608', 'L¹i ThÞ Hång Loan', '0206121629', '', '711A02600998', '6/10/85', '0');
INSERT INTO `tblnhanvien` VALUES ('LOL05', '1610', 'Lª ThÞ Thu Loan', '0207105766', '', '711A06350973', '08/12/86', '0');
INSERT INTO `tblnhanvien` VALUES ('LON01', '1200', 'NguyÔn H÷u Líp', '0296265718', '', '711A03448443', '05/8/62', '1');
INSERT INTO `tblnhanvien` VALUES ('LON02', '1300', 'NguyÔn ThÞ Kim Loan', '0296265552', '', '711A02603298', '26/5/57', '0');
INSERT INTO `tblnhanvien` VALUES ('LON03', '1308', 'NguyÔn ThÞ Loan', '0296265467', '', '711A03518052', '01/12/62', '0');
INSERT INTO `tblnhanvien` VALUES ('LON04', '1401', 'NguyÔn Huy Lîi', '0296265647', '', '711A01159968', '20/12/56', '1');
INSERT INTO `tblnhanvien` VALUES ('LON05', '1403', 'NguyÔn ThÞ Thanh Léc', '0296265578', '', '711A02600552', '18/8/57', '0');
INSERT INTO `tblnhanvien` VALUES ('LON06', '1401', 'NguyÔn V¨n Léc', '0202100205', '', '711A01159802', '02/04/1963', '1');
INSERT INTO `tblnhanvien` VALUES ('LON07', '1301', 'NguyÔn V¨n Long', '0296074149', '', '711A01169782', '06/3/63', '1');
INSERT INTO `tblnhanvien` VALUES ('LON08', '1301', 'Ng« Thiªn Long', '0202161011', '', '711A01327634', '25/12/79', '0');
INSERT INTO `tblnhanvien` VALUES ('LON09', '1401', 'NguyÔn Ngäc Hång Loan', '0205264630', '', '711A02591763', '2/7/82', '0');
INSERT INTO `tblnhanvien` VALUES ('LON10', '1403', 'NguyÔn ThÞ Hång Loan', '0202161068', '', '711A03446132', '19/11/72', '0');
INSERT INTO `tblnhanvien` VALUES ('LON11', '1301', 'NguyÔn ThÞ Loan', '0206151385', '', '711A03517412', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('LON12', '1604', 'NguyÔn Ngäc Quèc Long', '0207331851', '', '711A07741743', '1985', '1');
INSERT INTO `tblnhanvien` VALUES ('LOP01', '1607', 'Phan §¨ng Léc', '0203174852', '', '711A01147033', '', '1');
INSERT INTO `tblnhanvien` VALUES ('LOT01', '1304', 'TrÇn ThÞ Loan', '0296265416', '', '711A01145129', '20/8/60', '0');
INSERT INTO `tblnhanvien` VALUES ('LOT02', '1306', 'TrÇn Thanh Long', '0202160979', '', '711A02590602', '1/1/64', '1');
INSERT INTO `tblnhanvien` VALUES ('LOT03', '1601', 'Tr­¬ng Phan Thu Loan', '0205109961', '', '711A02595386', '20/7/80', '0');
INSERT INTO `tblnhanvien` VALUES ('LOV01', '1308', 'Vò H¶i Long', '0296265775', '', '711A01160949', '11/10/60', '1');
INSERT INTO `tblnhanvien` VALUES ('LOV02', '1604', 'Vò ThÞ Thanh Loan', '0207331852', '', '711A07741814', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('LUD01', '1302', 'D­¬ng §×nh L­îng', '0296265436', '', '711A01150925', '28/08/57', '1');
INSERT INTO `tblnhanvien` VALUES ('LUD02', '1610', 'D­¬ng ThÞ Lôa', '0296265477', '', '711A01160712', '13/8/59', '0');
INSERT INTO `tblnhanvien` VALUES ('LUD03', '1308', '§inh Quèc L­u', '0204154520', '', '711A02596086', '30/4/74', '1');
INSERT INTO `tblnhanvien` VALUES ('LUN02', '1302', 'NguyÔn Duy L­îng', '0296265530', '', '711A01150937', '15/08/55', '1');
INSERT INTO `tblnhanvien` VALUES ('LUN03', '1310', 'NguyÔn Thanh L­îng', '0207140791', '', '711A06156349', '13/11/1983', '1');
INSERT INTO `tblnhanvien` VALUES ('LUP02', '1400', 'Ph¹m ThÞ L­îng', '0296265614', '', '711A12673009', '20/06/63', '0');
INSERT INTO `tblnhanvien` VALUES ('LYM01', '1309', 'Mai ThÞ Th¶o Ly', '0299097789', '', '711A01154472', '20/2/78', '0');
INSERT INTO `tblnhanvien` VALUES ('MAD01', '1100', '§µo ThÞ Mai', '0296265600', '', '711A01178014', '29/8/62', '0');
INSERT INTO `tblnhanvien` VALUES ('MAD02', '1403', '§ç THÞ Mai', '0298025757', '', '711A02599972', '21/9/61', '0');
INSERT INTO `tblnhanvien` VALUES ('MAH01', '1309', 'Huúnh ThÞ Tróc Mai', '0203315590', '', '711A01154595', '19/4/82', '0');
INSERT INTO `tblnhanvien` VALUES ('MAL01', '1306', 'L¹i ThÞ Kim Mai', '0206177212', '', '711A03541364', '15/2/82', '0');
INSERT INTO `tblnhanvien` VALUES ('MAN01', '1507', 'NguyÔn Ph¹m Chi Mai', '0204154521', '', '711A01174419', '26/10/77', '0');
INSERT INTO `tblnhanvien` VALUES ('MAN02', '1300', 'NguyÔn ThÞ M©y', '0203333809', '', '711A03517539', '21/6/82', '0');
INSERT INTO `tblnhanvien` VALUES ('MAP01', '1301', 'Ph¹m ThÞ Ph­¬ng Mai', '0207376651', '', '711A04065221', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('MAT01', '1302', 'TrÇn Thanh M©n', '0202100198', '', '711A02596993', '09/12/69', '1');
INSERT INTO `tblnhanvien` VALUES ('MAT02', '1506', 'Th¸i LÖ MÉn', '0202161033', '', '711A02589649', '10/11/76', '0');
INSERT INTO `tblnhanvien` VALUES ('MAV01', '1302', 'Vò ThÞ Mai', '0296265433', '', '711A01169076', '5/7/60', '0');
INSERT INTO `tblnhanvien` VALUES ('MAŸ01', '1506', '§oµn thÞ Ngäc Mai', '0299097797', '', '711A01171246', '09/9/75', '0');
INSERT INTO `tblnhanvien` VALUES ('MIC01', '1100', 'Cao hµ Minh', '0202161074', '', '711A01178392', '23/4/77', '0');
INSERT INTO `tblnhanvien` VALUES ('MID01', '1309', '§ç Hång Minh', '0205264636', '', '711A02592261', '03/3/83', '0');
INSERT INTO `tblnhanvien` VALUES ('MIM01', '1310', 'Mai Quang Minh', '0204298495', '', '711A01158913', '16/10/83', '1');
INSERT INTO `tblnhanvien` VALUES ('MIN01', '1318', 'NguyÔn Minh', '0296265531', '', '711A01174063', '08/10/61', '0');
INSERT INTO `tblnhanvien` VALUES ('MIN02', '1306', 'NguyÔn Hoµng Minh', '0296265511', '', '711A02590811', '10/12/58', '1');
INSERT INTO `tblnhanvien` VALUES ('MIN03', '1304', 'NguyÔn ThÞ Minh', '0296265618', '', '711A03447388', '20/7/63', '0');
INSERT INTO `tblnhanvien` VALUES ('MIN06', '1403', 'NguyÔn Hång Ngäc Minh', '0202161045', '', '711A02670961', '21/11/80', '0');
INSERT INTO `tblnhanvien` VALUES ('MIN07', '1309', 'NguyÔn Anh Minh', '0206177213', '', '711A05470563', '17/1/73', '1');
INSERT INTO `tblnhanvien` VALUES ('MIN08', '1607', 'Ng« B¸ Minh', '0205109962', '', '711A02594891', '4/2/80', '1');
INSERT INTO `tblnhanvien` VALUES ('MIP01', '1603', 'Phan ThÕ Minh', '0202161001', '', '711A03447273', '2/10/75', '1');
INSERT INTO `tblnhanvien` VALUES ('MIT01', '1318', 'Th¸i Xu©n Minh', '0296265483', '', '711A03518234', '03/03/53', '1');
INSERT INTO `tblnhanvien` VALUES ('MIT02', '1613', 'Tr­¬ng Hoµng Minh', '0297120892', '', '711A02590101', '10/6/67', '1');
INSERT INTO `tblnhanvien` VALUES ('MIT03', '1314', 'TrÞnh QuÕ Minh', '0205264635', '', '711A02602733', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('MOD01', '1612', 'D­¬ng V¨n M­êi Mét', '0206389623', '', '711A05376744', '1973', '1');
INSERT INTO `tblnhanvien` VALUES ('MOT01', '1315', 'TrÞnh ThÞ M¬', '0203333817', '', '711A05508163', '13/7/79', '0');
INSERT INTO `tblnhanvien` VALUES ('MUN01', '1302', 'NguyÔn ThÞ Kim M­êi', '0203315563', '', '711A02601224', '23/12/81', '0');
INSERT INTO `tblnhanvien` VALUES ('MUP01', '1304', 'Phïng Ngäc M­êi', '0202160924', '', '711A01145168', '02/10/80', '0');
INSERT INTO `tblnhanvien` VALUES ('MYB01', '1306', 'Bïi ThÞ Mü', '0296265513', '', '711A03448691', '26/7/60', '0');
INSERT INTO `tblnhanvien` VALUES ('MYH01', '1220', 'Huúnh Ngäc Mü', '0201068836', '', '711A04478478', '1970', '0');
INSERT INTO `tblnhanvien` VALUES ('MYL02', '1306', 'Lª Hµ Th¶o My', '0204343556', '', '711A02591182', '3/8/1980', '0');
INSERT INTO `tblnhanvien` VALUES ('MYN02', '1601', 'NGuyÔn Lª My', '0202160922', '', '711A05682423', '08/03/73', '0');
INSERT INTO `tblnhanvien` VALUES ('MYT01', '1403', 'TrÇn ThÞ Mü', '0202161046', '', '711A02600477', '15/1/73', '0');
INSERT INTO `tblnhanvien` VALUES ('NAD01', '1306', '§oµn HuÖ Nam', '0203315566', '', '711A02589376', '20/9/80', '1');
INSERT INTO `tblnhanvien` VALUES ('NAD02', '1308', 'D­¬ng §ç Hoµi Nam', '0204356576', '', '711A02596047', '11/7/79', '1');
INSERT INTO `tblnhanvien` VALUES ('NAL01', '1318', 'Lý ThÞ N¨m', '0296265667', '', '711A01175908', '10/08/64', '0');
INSERT INTO `tblnhanvien` VALUES ('NAL02', '1614', 'Lª ThÞ Ph­¬ng Nam', '0202161028', '', '711A01421457', '25/07/76', '0');
INSERT INTO `tblnhanvien` VALUES ('NAN01', '1310', 'NguyÔn Hoµng Nam', '0203052633', '', '711A01254924', '06/09/80', '1');
INSERT INTO `tblnhanvien` VALUES ('NAN02', '1603', 'NguyÔn Hoµi Nam', '0204209010', '', '711A03447266', '1/1/73', '1');
INSERT INTO `tblnhanvien` VALUES ('NAP02', '1100', 'Ph¹m Hoµng Nam', '0200122192', '', '711A02590392', '1972', '1');
INSERT INTO `tblnhanvien` VALUES ('NGC01', '1607', 'Cao ThÞ Ngä', '0296265455', '', '711A02599463', '18/5/58', '0');
INSERT INTO `tblnhanvien` VALUES ('NGD01', '1300', '§Æng Thu Ng©n', '0296265564', '', '711A01327449', '24/8/73', '0');
INSERT INTO `tblnhanvien` VALUES ('NGD02', '1303', '§oµn ThÞ Nga', '0296265449', '', '711A02599065', '24/4/62', '0');
INSERT INTO `tblnhanvien` VALUES ('NGD03', '1610', '§ç ThÞ BÝch Ngäc', '0296265471', '', '711A03445101', '17/01/62', '0');
INSERT INTO `tblnhanvien` VALUES ('NGD05', '1316', '§ç ThÞ BÝch Ngäc', '0204236504', '320797037', '711A55625694', '20/10/73', '0');
INSERT INTO `tblnhanvien` VALUES ('NGD06', '1314', '§Æng B¶o Ngäc', '0203315583', '', '711A02602073', '8/12/74', '1');
INSERT INTO `tblnhanvien` VALUES ('NGH03', '1300', 'Huúnh ThÞ Thóy Nga', '0203333822', '', '711A01173502', '24/7/74', '0');
INSERT INTO `tblnhanvien` VALUES ('NGH04', '1604', 'Huúnh ThÞ YÕn Ngäc', '0207214961', '', '711A06856133', '1986', '0');
INSERT INTO `tblnhanvien` VALUES ('NGK01', '1303', 'KiÒu ThÞ Thanh Nguyªn', '0203133681', '', '711A02599542', '24/05/80', '0');
INSERT INTO `tblnhanvien` VALUES ('NGL01', '1302', 'Lª ThÞ Xu©n H­êng', '0296265771', '', '711A03517254', '', '0');
INSERT INTO `tblnhanvien` VALUES ('NGL02', '1507', 'Lý BÝch Ngäc', '0205264637', '', '711A02596402', '9/12/83', '0');
INSERT INTO `tblnhanvien` VALUES ('NGL03', '1606', 'L­¬ng ThÞ Kim Ng©n', '0202160926', '', '711A03447649', '13/7/79', '0');
INSERT INTO `tblnhanvien` VALUES ('NGM01', '1403', 'Mai ThÞ Thanh Nga', '0298120564', '', '711A02600453', '18/5/74', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN01', '1503', 'NguyÔn ThÞ Béi Ngäc', '0202100201', '', '711A01151292', '25/11/71', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN02', '1309', 'NguyÔn ThÞ NguyÖt', '0296265680', '', '711A05507705', '28/3/69', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN03', '1603', 'NguyÔn ThÞ Ngoan', '0296265488', '', '711A07369671', '1/3/59', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN04', '1300', 'NguyÔn ThÞ NguyÖt Nga', '0296265575', '', '711A02601942', '13/4/68', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN05', '1314', 'Ng« Nguyªn Quang', '0296265769', '', '711A01155788', '02/10/65', '1');
INSERT INTO `tblnhanvien` VALUES ('NGN06', '1310', 'NguyÔn V¨n Ng©u', '0298120568', '', '711A02600741', '12/9/70', '1');
INSERT INTO `tblnhanvien` VALUES ('NGN07', '1309', 'NguyÔn V¨n NghÜa', '0299051662', '', '711A02592644', '03/11/65', '1');
INSERT INTO `tblnhanvien` VALUES ('NGN08', '1300', 'NguyÔn thÞ Ng©n', '0203333825', '', '711A01177653', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN09', '1100', 'NguyÔn thÞ Nga', '0204270895', '', '711A01255146', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN10', '1507', 'NguyÔn ThÞ Kim Nga', '0202089493', '', '711A01183344', '27/3/76', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN11', '1507', 'NguyÔn ThÞ Hång Ngäc', '0205264638', '', '711A01183383', '5/12/84', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN12', '1400', 'NguyÔn ThÞ Kim Ng©n', '0205274480', '', '711A05507732', '7/12/82', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN14', '1303', 'NguyÔn ThÞ Kim Ng©n', '0205274473', '', '711A02988993', '10/10/84', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN15', '1607', 'NguyÔn ThÞ Thanh Nga', '0203133696', '', '711A02598191', '25/8/82', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN16', '1607', 'NguyÔn ThÞ Kim Nguyªn', '0205264639', '', '711A02599148', '20/7/83', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN17', '1304', 'NguyÔn ThÞ DiÖu Nga', '0204343548', '', '711A05447429', '10/5/81', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN18', '1301', 'NguyÔn ThÞ Ng¸t', '0206389624', '', '711A06156057', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN20', '1606', 'NguyÔn HuyÒn Nga', '0207376648', '', '711A09815583', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('NGN21', '1608', 'NguyÔn Duy Ngäc', '0207448891', '', '711A10715645', '1986', '1');
INSERT INTO `tblnhanvien` VALUES ('NGP02', '1314', 'Ph¹m ThÞ Nga', '0201092991', '', '711A01156437', '22/2/77', '0');
INSERT INTO `tblnhanvien` VALUES ('NGP03', '1306', 'Ph¹m Trung NghÜa', '0202100195', '', '711A02591606', '28/11/75', '1');
INSERT INTO `tblnhanvien` VALUES ('NGP04', '1503', 'Ph¹m ThÞ Ngäc', '0204354130', '', '711A03541143', '17/11/78', '0');
INSERT INTO `tblnhanvien` VALUES ('NGP05', '1507', 'Phan ThÞ Mü Nhung', '0202160995', '', '711A04019557', '', '0');
INSERT INTO `tblnhanvien` VALUES ('NGP06', '1309', 'Ph¹m ThÞ Minh NguyÖt', '0204320925', '', '711A02591949', '03/04/82', '0');
INSERT INTO `tblnhanvien` VALUES ('NGP07', '1303', 'Phan Kim Ng©n', '0205109963', '', '711A01147297', '19/12/80', '0');
INSERT INTO `tblnhanvien` VALUES ('NGT01', '1300', 'TrÇn ThÞ Kim Nguyªn', '0296265664', '', '711A01175887', '7/10/57', '0');
INSERT INTO `tblnhanvien` VALUES ('NGT02', '1506', 'TrÇn ThÞ Linh Ngäc', '0296265661', '', '711A02589921', '14/2/58', '0');
INSERT INTO `tblnhanvien` VALUES ('NGT05', '1306', 'TrÇn thÞ Hång Nga', '0298109063', '', '711A01153134', '08/1/77', '0');
INSERT INTO `tblnhanvien` VALUES ('NGT06', '1613', 'Trang ThÞ CÈm Nguyªn', '0205264640', '', '711A02589885', '', '0');
INSERT INTO `tblnhanvien` VALUES ('NGV01', '1306', 'Vâ ThÞ Nga', '0296265514', '', '711A03517136', '22/11/64', '0');
INSERT INTO `tblnhanvien` VALUES ('NGV02', '1301', 'Vò §×nh Th¾ng', '0296265770', '', '711A02586457', '18/12/63', '1');
INSERT INTO `tblnhanvien` VALUES ('NHC01', '1100', 'Ch©u Quúnh Nh­', '0207250105', '024675219', '711A07368751', '01/09/81', '0');
INSERT INTO `tblnhanvien` VALUES ('NHD01', '1504', '§inh ThÞ Thanh Nhµn', '3702002815', '', '711A06351089', '1975', '0');
INSERT INTO `tblnhanvien` VALUES ('NHH01', '1200', 'Huúnh V¨n Nhi', '0298025758', '', '711A01156034', '12/10/75', '1');
INSERT INTO `tblnhanvien` VALUES ('NHH02', '1401', 'Hå Thanh Nh©n', '0204069586', '', '711A01160084', '7/12/82', '0');
INSERT INTO `tblnhanvien` VALUES ('NHL01', '1100', 'Lª ThÞ Thuý Nhi', '0203174891', '', '711A01177491', '1968', '0');
INSERT INTO `tblnhanvien` VALUES ('NHL02', '1305', 'Lª ThÞ Nh©n', '0296265398', '', '711A02594951', '10/10/61', '0');
INSERT INTO `tblnhanvien` VALUES ('NHL03', '1504', 'Lª ThÞ Nhá', '0296265734', '', '711A01170621', '17/2/59', '0');
INSERT INTO `tblnhanvien` VALUES ('NHL04', '1604', 'Lª ThÞ Nhung', '0207140800', '', '711A06349763', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('NHN01', '1315', 'NguyÔn ThÞ Nh¹n', '0296265609', '', '711A05511277', '15/6/60', '0');
INSERT INTO `tblnhanvien` VALUES ('NHN04', '1310', 'NguyÔn ThÞ Mü H¹nh', '0296265751', '', '711A03446195', '', '0');
INSERT INTO `tblnhanvien` VALUES ('NHN05', '1403', 'NguyÔn C«ng Thµnh', '0296265764', '', '711A03444769', '12/10/65', '1');
INSERT INTO `tblnhanvien` VALUES ('NHN06', '1400', 'NguyÔn ThÞ Hång Nhung', '0202161077', '', '711A03448853', '', '0');
INSERT INTO `tblnhanvien` VALUES ('NHN07', '1304', 'NguyÔn ThÞ Quúnh Nh­', '0204069590', '', '711A01255051', '29/12/78', '0');
INSERT INTO `tblnhanvien` VALUES ('NHN08', '1603', 'NguyÔn ThÞ Nhung', '0205182008', '', '711A02601133', '1981', '0');
INSERT INTO `tblnhanvien` VALUES ('NHN09', '1306', 'NguyÔn ThÞ TuyÕt Nhung', '0204181461', '', '711A12672111', '1981', '0');
INSERT INTO `tblnhanvien` VALUES ('NHP02', '1608', 'Ph¹m ThÞ NhuÇn', '0207400284', '', '711A10715554', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('NHT02', '1403', 'TrÇn Hoµng Nh×', '0296265698', '', '711A03444721', '13/8/55', '1');
INSERT INTO `tblnhanvien` VALUES ('NHT03', '1601', 'TrÇn ThÞ Kim Nhu', '0202160940', '', '711A01145392', '29/10/78', '0');
INSERT INTO `tblnhanvien` VALUES ('NHT04', '1314', 'Tr­¬ng CÈm Nh­', '0207331856', '', '711A07741691', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('NHV03', '1301', 'Vò ThÞ Hµ Nh©n', '0204343547', '', '711A02586702', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('NHV04', '1400', 'Vò ThÞ TuyÕt Nhung', '0203333814', '', '711A05507775', '31/5/77', '0');
INSERT INTO `tblnhanvien` VALUES ('NIT01', '1310', 'TrÇn Phóc Ninh', '0202161059', '', '711A01158846', '1/5/80', '1');
INSERT INTO `tblnhanvien` VALUES ('NUD01', '1301', '§oµn ThÞ N­¬ng', '0207400283', '', '711A11742133', '1986', '0');
INSERT INTO `tblnhanvien` VALUES ('OAC01', '1504', 'Ch©u §Æng Kim Oanh', '0204154507', '', '711A02596141', '10/6/70', '0');
INSERT INTO `tblnhanvien` VALUES ('OAD01', '1305', '§inh Thu Oanh', '0200054821', '', '711A02594924', '1973', '0');
INSERT INTO `tblnhanvien` VALUES ('OAH01', '1100', 'Hoµng ThÞ Kim Oanh', '0200122193', '', '711A03448597', '1971', '0');
INSERT INTO `tblnhanvien` VALUES ('OAH02', '1601', 'Huúnh ThÞ Chiªu Oanh', '0200094258', '', '711A01254253', '1970', '0');
INSERT INTO `tblnhanvien` VALUES ('OAL01', '1400', 'Lª ThÞ Oanh', '0203333818', '', '711A03837764', '9/7/79', '0');
INSERT INTO `tblnhanvien` VALUES ('OAM01', '1302', 'Mai ThÞ Hoa', '0296265445', '', '711A01327512', '13/7/68', '0');
INSERT INTO `tblnhanvien` VALUES ('OAN01', '1507', 'NguyÔn ThÞ Kim Oanh', '0296265501', '', '711A01174036', '03/4/59', '0');
INSERT INTO `tblnhanvien` VALUES ('OAN03', '1610', 'NguyÔn ThÞ Kim Oanh', '0296265430', '', '711A05681728', '20/7 /71', '0');
INSERT INTO `tblnhanvien` VALUES ('OAN04', '1315', 'NguyÔn ThÞ Oanh', '0298120562', '', '711A01178211', '25/05/1976', '0');
INSERT INTO `tblnhanvien` VALUES ('OAN05', '1100', 'NguyÔn ThÞ Oanh', '0299059269', '', '711A02590432', '10/10/70', '0');
INSERT INTO `tblnhanvien` VALUES ('OAN06', '1606', 'NguyÔn ThÞ Ngäc Oanh', '0299097785', '', '711A01158676', '03/3/74', '0');
INSERT INTO `tblnhanvien` VALUES ('OAN07', '1314', 'NguyÔn ThÞ Thu Oanh', '0205383485', '', '711A02586169', '22/9/83', '0');
INSERT INTO `tblnhanvien` VALUES ('OAN08', '1314', 'NguyÔn ThÞ Ngäc Oanh', '0205274474', '', '711A02601879', '26/9/74', '0');
INSERT INTO `tblnhanvien` VALUES ('OAT02', '1606', 'TrÇn ThÞ Kim Oanh', '0202160925', '', '711A01145211', '20/4/1980', '0');
INSERT INTO `tblnhanvien` VALUES ('OAT03', '1302', 'TrÇn ThÞ Thóy Oanh', '0207105739', '', '711A06349902', '15/7/86', '0');
INSERT INTO `tblnhanvien` VALUES ('OAV01', '1301', 'Vâ Kim Oanh', '0203315570', '', '711A01170092', '26/10/81', '0');
INSERT INTO `tblnhanvien` VALUES ('ONL01', '1200', 'Lª V¨n On', '0296265725', '', '711A01178092', '20/10/66', '1');
INSERT INTO `tblnhanvien` VALUES ('ONN01', '1303', 'Ng« ThÞ H­¬ng', '0296265461', '', '711A01150474', '23/07/61', '0');
INSERT INTO `tblnhanvien` VALUES ('ONN02', '1508', 'NguyÔn ThÞ LÖ Hång', '0296265756', '', '711A01172956', '30/1/64', '0');
INSERT INTO `tblnhanvien` VALUES ('ONP02', '1403', 'Ph¹m ThÞ Trßn', '0296265684', '', '711A02600525', '26/3/67', '0');
INSERT INTO `tblnhanvien` VALUES ('PHC01', '1503', 'Chu ThÞ Thanh Ph­¬ng', '0298025772', '', '711A01151332', '21/9/65', '0');
INSERT INTO `tblnhanvien` VALUES ('PHC02', '1300', 'Cao Lan Ph­¬ng', '0206177189', '', '711A05742565', '13/10/84', '0');
INSERT INTO `tblnhanvien` VALUES ('PHD02', '1403', '§µo ThÞ B×nh Phó', '0298109067', '', '711A02586418', '17/12/70', '0');
INSERT INTO `tblnhanvien` VALUES ('PHD03', '1601', 'D­¬ng Thanh Phóc', '0203315560', '', '711A02587401', '25/7/81', '0');
INSERT INTO `tblnhanvien` VALUES ('PHD05', '1305', '§µo Minh Ph­¬ng', '0203174874', '', '711A03541045', '1/1/77', '0');
INSERT INTO `tblnhanvien` VALUES ('PHD07', '1400', '§µo Hång Ph­îng', '0206389626', '', '711A05682368', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('PHD08', '1309', '§oµn ThÞ Ph­¬ng', '0207376654', '', '711A12753083', '1986', '0');
INSERT INTO `tblnhanvien` VALUES ('PHH01', '1602', 'Huúnh Thanh Phong', '0296265420', '', '711A03447988', '1/1/62', '1');
INSERT INTO `tblnhanvien` VALUES ('PHH02', '1400', 'Hoµng ThÞ Ph­îng', '0296265621', '', '711A05896559', '19/5/65', '0');
INSERT INTO `tblnhanvien` VALUES ('PHH03', '1306', 'Huúnh Thanh Phong', '0296265522', '', '711A03517194', '31/3/70', '1');
INSERT INTO `tblnhanvien` VALUES ('PHH04', '1608', 'Huúnh CÈm Ph­îng', '0204270881', '', '711A01153441', '01/09/77', '0');
INSERT INTO `tblnhanvien` VALUES ('PHL04', '1507', 'Lª ThÞ Thuý Ph­îng', '0204154509', '', '711A01173951', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('PHL05', '1604', 'Lª ThÞ Lan Ph­¬ng', '0201092974', '', '711A01183431', '1977', '0');
INSERT INTO `tblnhanvien` VALUES ('PHL06', '1305', 'Lª ThÞ TuyÕt Ph­îng', '0296639020', '', '711A01143327', '08/1/68', '0');
INSERT INTO `tblnhanvien` VALUES ('PHL07', '1304', 'Lª ThÞ Thanh Ph­¬ng', '0203315585', '', '711A01255079', '29/9/82', '0');
INSERT INTO `tblnhanvien` VALUES ('PHL08', '1507', 'Lª Kim Ph­îng', '0205089494', '', '711A03518573', '27/8/75', '0');
INSERT INTO `tblnhanvien` VALUES ('PHL09', '1318', 'Lª Xu©n Phóc', '0204298498', '', '711A03447913', '9/5/82', '1');
INSERT INTO `tblnhanvien` VALUES ('PHL10', '1403', 'L©m TuÊn Phong', '0203174913', '', '711A03444954', '23/4/75', '1');
INSERT INTO `tblnhanvien` VALUES ('PHL11', '1304', 'Lª ThÞ Ph­¬ng', '0207294622', '', '711A07369507', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('PHN01', '1200', 'NguyÔn Quang Phóc', '0296265706', '', '711A01177641', '15/7/56', '1');
INSERT INTO `tblnhanvien` VALUES ('PHN04', '1508', 'Ng« Xu©n Ph­¬ng', '0296265498', '', '711A01172971', '05/01/65', '1');
INSERT INTO `tblnhanvien` VALUES ('PHN06', '1507', 'NguyÔn ThÞ Ph­îng', '0298025785', '', '711A01254699', '08/9/71', '0');
INSERT INTO `tblnhanvien` VALUES ('PHN07', '1506', 'NguyÔn ThÞ Ph­îng', '0298109073', '', '711A01254762', '23/03/77', '0');
INSERT INTO `tblnhanvien` VALUES ('PHN08', '1603', 'NguyÔn §×nh Phó', '4796056727', '', '711A07369872', '07/8/61', '1');
INSERT INTO `tblnhanvien` VALUES ('PHN09', '1403', 'NguyÔn Do·n Ph­¬ng', '0299097799', '', '711A03444745', '', '1');
INSERT INTO `tblnhanvien` VALUES ('PHN11', '1315', 'NguyÔn TÊn Ph¸t', '0202058270', '', '711A02595513', '01/1/76', '1');
INSERT INTO `tblnhanvien` VALUES ('PHN12', '1304', 'NguyÔn H÷u Phè', '0202161038', '', '711A01152715', '22/2/71', '1');
INSERT INTO `tblnhanvien` VALUES ('PHN13', '1301', 'Ng« Uyªn Ph­¬ng', '0204270888', '', '711A02586879', '22/11/82', '0');
INSERT INTO `tblnhanvien` VALUES ('PHN14', '1314', 'NguyÔn ThÞ Ph­îng', '0204270887', '', '711A01157751', '22/03/82', '0');
INSERT INTO `tblnhanvien` VALUES ('PHN15', '1506', 'NguyÔn ThÞ Hång Ph­îng', '0204270891', '', '711A01171494', '11/07/83', '0');
INSERT INTO `tblnhanvien` VALUES ('PHN16', '1506', 'NguyÔn Huy Phong', '0204069584', '', '711A01171404', '21/6/77', '1');
INSERT INTO `tblnhanvien` VALUES ('PHN17', '1606', 'NguyÔn ThÞ Phó', '0203315557', '', '711A01145259', '24/5/82', '0');
INSERT INTO `tblnhanvien` VALUES ('PHN19', '1310', 'NguyÔn §øc Phó', '0207294621', '', '711A07741383', '3/8/1980', '0');
INSERT INTO `tblnhanvien` VALUES ('PHN20', '1607', 'Ng« V¨n Ph­¬ng', '0202160962', '', '711A01146972', '10/9/69', '1');
INSERT INTO `tblnhanvien` VALUES ('PHN21', '1601', 'NguyÔn ThÞ Ngäc Ph­îng', '0207105761', '', '711A06350449', '17/5/86', '0');
INSERT INTO `tblnhanvien` VALUES ('PHP01', '1100', 'Ph¹m ThÞ Ph­¬ng', '0296265597', '', '711A02590483', '1/1/58', '0');
INSERT INTO `tblnhanvien` VALUES ('PHP02', '1506', 'Ph¹m B¸ Ph­¬ng', '0203333839', '', '711A02589621', '16/2/82', '1');
INSERT INTO `tblnhanvien` VALUES ('PHP03', '1603', 'Phan TÊn Phong', '0204209012', '', '711A00087264', '23/5/68', '1');
INSERT INTO `tblnhanvien` VALUES ('PHP04', '1100', 'Phan thÞ ¸nh Ph­¬ng', '0202161076', '', '711A01178511', '1978', '0');
INSERT INTO `tblnhanvien` VALUES ('PHP05', '1506', 'Phan Hång PhÈm', '0205206240', '', '711A02590041', '1979', '1');
INSERT INTO `tblnhanvien` VALUES ('PHQ01', '1220', 'Qu¸ch Ng« YÕn Ph­¬ng', '0205080880', '', '711A01176251', '2/9/7/84', '0');
INSERT INTO `tblnhanvien` VALUES ('PHT01', '1304', 'TrÇn ThÞ ¸nh Ph­îng', '0296265425', '', '711A05681691', '10/11/70', '0');
INSERT INTO `tblnhanvien` VALUES ('PHT02', '1304', 'TrÞnh ThÞ Ph­îng', '0298025750', '', '711A05896602', '26/3/71', '0');
INSERT INTO `tblnhanvien` VALUES ('PHT04', '1314', 'TrÇn Kim Ph­îng', '0298109072', '', '711A01156361', '11/09/76', '1');
INSERT INTO `tblnhanvien` VALUES ('PHT06', '1318', 'TrÇn Ngäc Ph­îng', '0299097783', '', '711A01145338', '16/1/77', '0');
INSERT INTO `tblnhanvien` VALUES ('PHT07', '1305', 'Tr­¬ng ThÞ ¸i Ph­¬ng', '0201079235', '', '711A01143366', '21/11/74', '0');
INSERT INTO `tblnhanvien` VALUES ('PHT08', '1305', 'TRÇn ThÞ Ph­¬ng', '0206121630', '', '711A03447451', '15/1/85', '0');
INSERT INTO `tblnhanvien` VALUES ('PHT10', '1300', 'TrÇn ThÞ Hång Ph­îng', '0203133678', '', '711A02593502', '13/11/80', '0');
INSERT INTO `tblnhanvien` VALUES ('PHT11', '1303', 'TrÇn ThÞ Hoµi Ph­¬ng', '0207294606', '', '711A07908745', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('PHT12', '1613', 'TrÇn Thanh Phong', '0202161037', '', '711A02590092', '15/11/73', '1');
INSERT INTO `tblnhanvien` VALUES ('PHT13', '1601', 'Th­îng Thanh Ph­¬ng', '0203174846', '', '711A03517566', '26/7/78', '1');
INSERT INTO `tblnhanvien` VALUES ('PHT14', '1303', 'TrÇn ThÞ Tróc Ph­¬ng', '0207105774', '', '711A06350697', '18/4/83', '0');
INSERT INTO `tblnhanvien` VALUES ('PHV01', '1506', 'Vò ThÞ Kim Ph­îng', '0202161027', '', '711A12674074', '24/4/81', '0');
INSERT INTO `tblnhanvien` VALUES ('PHV02', '1314', 'Vâ ThÞ Hoµng Phông', '0203315573', '', '711A01252539', '11/5/82', '0');
INSERT INTO `tblnhanvien` VALUES ('QUB01', '1601', 'Bïi §an QuÕ', '0296265427', '', '711A01145298', '23/5/72', '0');
INSERT INTO `tblnhanvien` VALUES ('QUD01', '1607', '§inh Vinh Quang', '0202160945', '', '711A02599657', '21/10/72', '1');
INSERT INTO `tblnhanvien` VALUES ('QUL02', '1307', 'L­¬ng ThÞ QuyÕt', '0296265399', '', '711A01173166', '11/11/61', '0');
INSERT INTO `tblnhanvien` VALUES ('QUL03', '1507', 'L­¬ng Ngäc Quyªn', '0204270873', '', '711A01174813', '22/02/83', '0');
INSERT INTO `tblnhanvien` VALUES ('QUL04', '1317', 'Lª ThÞ NGäc Quyªn', '0207465111', '', '711A12674789', '1981', '0');
INSERT INTO `tblnhanvien` VALUES ('QUM01', '1303', 'M· LÖ Qu©n', '0204154513', '', '711A02596256', '29/7/78', '0');
INSERT INTO `tblnhanvien` VALUES ('QUN01', '1401', 'NguyÔn Thóy Quúnh', '0203133694', '', '711A01160069', '14/11/80', '0');
INSERT INTO `tblnhanvien` VALUES ('QUN02', '1314', 'NguyÔn Minh Quan', '0202161023', '', '711A01153722', '30/4/69', '1');
INSERT INTO `tblnhanvien` VALUES ('QUN03', '1303', 'NguyÔn §×nh Hoµng Quyªn', '0205109964', '', '711A01147182', '29/3/80', '0');
INSERT INTO `tblnhanvien` VALUES ('QUN04', '1301', 'NguyÔn ThÞ Quang', '0205089495', '', '711A04478454', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('QUP01', '1601', 'Ph¹m Tó Quúnh', '0201092966', '', '711A02670022', '1976', '0');
INSERT INTO `tblnhanvien` VALUES ('QUP02', '1606', 'Ph¹m Hång B¶o Qu©n', '0203333807', '', '711A02593442', '11/9/69', '1');
INSERT INTO `tblnhanvien` VALUES ('QUT01', '1315', 'Tr­¬ng Tó Quúnh', '0296265623', '', '711A05742584', '29/12//69', '0');
INSERT INTO `tblnhanvien` VALUES ('QUT02', '1603', 'Tr­¬ng BÝch Qu©n', '0204270880', '', '711A01153434', '07/04/82', '0');
INSERT INTO `tblnhanvien` VALUES ('QUT03', '1601', 'Tr­¬ng LÖ Quyªn', '0203174853', '', '711A02587228', '26/8/77', '0');
INSERT INTO `tblnhanvien` VALUES ('SAD01', '1302', '§inh ThÞ Sao', '0296265442', '', '711A01169128', '21/12/66', '0');
INSERT INTO `tblnhanvien` VALUES ('SAP01', '1302', 'Ph¹m ThÞ S¸ng', '0296265666', '', '711A01169091', '3/4/63', '0');
INSERT INTO `tblnhanvien` VALUES ('SAP02', '1607', 'Ph¹m Hång Sanh', '0207105769', '', '711A06350764', '15/10/85', '0');
INSERT INTO `tblnhanvien` VALUES ('SAT01', '1604', 'TrÇn ThÞ Sang', '0299119525', '', '711A02588223', '18/1/76', '0');
INSERT INTO `tblnhanvien` VALUES ('SAT02', '1301', 'TrÇn Thanh Sang', '0206389628', '', '711A05470476', '1974', '1');
INSERT INTO `tblnhanvien` VALUES ('SED01', '1603', '§Æng Thanh Sen', '0205264642', '', '711A02601062', '7/2/83', '0');
INSERT INTO `tblnhanvien` VALUES ('SEN01', '1315', 'NguyÔn ThÞ Sen', '0202161047', '', '711A01254481', '20/11/66', '0');
INSERT INTO `tblnhanvien` VALUES ('SIP01', '1403', 'Ph¹m ThÞ SÝch', '0202160939', '', '711A01145384', '1/1/78', '0');
INSERT INTO `tblnhanvien` VALUES ('SIT01', '1200', 'TrÇn V¨n Sü', '0296265716', '', '711A02597512', '25/8/52', '1');
INSERT INTO `tblnhanvien` VALUES ('SOD02', '1315', '§Æng Minh S¬n', '0296265632', '', '711A02595623', '24/9/57', '1');
INSERT INTO `tblnhanvien` VALUES ('SOG01', '1401', 'Giang ThÕ S¬n', '0296265643', '', '711A03445504', '1/1/60', '1');
INSERT INTO `tblnhanvien` VALUES ('SOH01', '1303', 'Høa Tó S¬n', '0202160946', '', '711A02599918', '11/4/71', '0');
INSERT INTO `tblnhanvien` VALUES ('SOH02', '1310', 'Huúnh Thanh S¬n', '0203333823', '', '711A06240853', '24/4/1969', '1');
INSERT INTO `tblnhanvien` VALUES ('SOL01', '1314', 'Lª §iÒn S¬n', '0204154517', '', '711A01155934', '', '1');
INSERT INTO `tblnhanvien` VALUES ('SON02', '1306', 'NguyÔn Hoµng S¬n', '0207105745', '', '711A06350152', '28/11/86', '1');
INSERT INTO `tblnhanvien` VALUES ('SOT01', '1301', 'TrÞnh Hoµi S¬n', '0203133686', '', '711A01170053', '22/02/80', '1');
INSERT INTO `tblnhanvien` VALUES ('SOT02', '1507', 'TrÇn V¨n Sãng', '0203174870', '', '711A01254135', '30/9/76', '1');
INSERT INTO `tblnhanvien` VALUES ('SUN01', '1100', 'NguyÔn Ngäc S­¬ng', '0203274344', '', '711A19227902', '23/4/78', '0');
INSERT INTO `tblnhanvien` VALUES ('SYN01', '1310', 'NguyÔn Träng Sü', '0207140790', '', '711A06156388', '1982', '1');
INSERT INTO `tblnhanvien` VALUES ('TAB01', '1603', 'Bïi H÷u T©m', '0296265490', '', '711A05376653', '6/6/62', '1');
INSERT INTO `tblnhanvien` VALUES ('TAD02', '1607', '§oµn C«ng TÊn', '0202100191', '', '711A02599684', '08/03/73', '1');
INSERT INTO `tblnhanvien` VALUES ('TAK01', '1302', 'KhuÊt ThÞ T©m', '0296265438', '', '711A02601339', '02/08/60', '0');
INSERT INTO `tblnhanvien` VALUES ('TAL01', '1307', 'Lª ThÞ T¸m', '0296265557', '', '711A01152609', '15/5/63', '0');
INSERT INTO `tblnhanvien` VALUES ('TAL02', '1613', 'Lª ThÞ Minh T©m', '0203333815', '', '711A05507881', '8/5/77', '0');
INSERT INTO `tblnhanvien` VALUES ('TAM01', '1507', 'Méc Thiªn Tµi', '0204298503', '', '711A01169716', '25/5/77', '1');
INSERT INTO `tblnhanvien` VALUES ('TAN01', '1302', 'NguyÔn ThÞ T©m', '0296265439', '', '711A02601251', '15/04/59', '0');
INSERT INTO `tblnhanvien` VALUES ('TAN03', '1400', 'NguyÔn ThÞ T©n', '0296265617', '', '711A05742514', '22/10/59', '0');
INSERT INTO `tblnhanvien` VALUES ('TAN04', '1606', 'NguyÔn ThÞ T©m', '0296265520', '', '711A02593478', '04/7/71', '0');
INSERT INTO `tblnhanvien` VALUES ('TAN06', '1305', 'NguyÔn C«ng T©n', '0203274322', '', '711A02600974', '17/4/70', '1');
INSERT INTO `tblnhanvien` VALUES ('TAN07', '1603', 'NguyÔn H÷u T©m', '0204209011', '', '711A01153311', '12*9/71', '1');
INSERT INTO `tblnhanvien` VALUES ('TAN08', '1613', 'NguyÔn Ph¹m Hång T©m', '0207448896', '', '711A10715799', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('TAP01', '1613', 'Phan ThÞ Thanh T©m', '0207465113', '', '711A10715748', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('TAP02', '1100', 'Phan ThÞ T©n', '0298025747', '', '711A02670231', '30/10/71', '0');
INSERT INTO `tblnhanvien` VALUES ('TAT01', '1610', 'T¹ ThÞ T©n', '0296265474', '', '711A01160436', '15/08/56', '0');
INSERT INTO `tblnhanvien` VALUES ('TAT02', '1314', 'Tr­¬ng Minh T©n', '0298025782', '', '711A02602164', '01/1/68', '1');
INSERT INTO `tblnhanvien` VALUES ('TAT03', '1503', 'TrÇn thÞ T©m', '0298120567', '', '711A01152682', '10/5/72', '0');
INSERT INTO `tblnhanvien` VALUES ('TAT04', '1506', 'TrÞnh ThÞ Ngäc T©m', '0203133689', '', '711A02589494', '22/12/81', '0');
INSERT INTO `tblnhanvien` VALUES ('TAT05', '1507', 'TrÇn Hïng TÊn', '0202160998', '', '711A01173193', '21/12/69', '1');
INSERT INTO `tblnhanvien` VALUES ('TAT07', '1300', 'TrÇn Vò Minh T©m', '7908477994', '', '711A02351861', '', '0');
INSERT INTO `tblnhanvien` VALUES ('TAV01', '1309', 'Vâ ThÞ Thanh T©m', '0202160973', '', '711A01154544', '10/2/80', '0');
INSERT INTO `tblnhanvien` VALUES ('THB01', '1507', 'Bïi V¨n Thµnh', '0296265724', '', '711A03541664', '01/6/64', '1');
INSERT INTO `tblnhanvien` VALUES ('THB02', '1318', 'Bïi Ph­¬ng Th¶o', '0203133675', '', '711A02594876', '17/1/81', '0');
INSERT INTO `tblnhanvien` VALUES ('THC04', '1301', 'Ch©u ThÞ Ph­¬ng Thanh', '0207376652', '', '711A09817073', '1986', '0');
INSERT INTO `tblnhanvien` VALUES ('THD01', '1310', '§oµn ThÞ Thñy', '0296265458', '', '711A04019518', '23/8/67', '0');
INSERT INTO `tblnhanvien` VALUES ('THD02', '1301', '§µo ThÞ Thanh Th¶o', '0201092977', '', '711A02586796', '20/1/74', '0');
INSERT INTO `tblnhanvien` VALUES ('THD03', '1307', '§µo Minh Thuý', '0296265484', '', '711A01173139', '9/8/57', '0');
INSERT INTO `tblnhanvien` VALUES ('THD04', '1308', '§oµn V¨n ThuËn', '0298025756', '', '711A02595868', '11/6/69', '0');
INSERT INTO `tblnhanvien` VALUES ('THD05', '1507', '§µo ThÞ Kim Thoa', '0204101927', '', '711A01174722', '27/3/81', '0');
INSERT INTO `tblnhanvien` VALUES ('THD06', '1607', 'D­¬ng ThÞ Th¶o', '0205264643', '', '711A02599084', '4/11/82', '0');
INSERT INTO `tblnhanvien` VALUES ('THD07', '1403', 'D­¬ng ThÞ Kim Thanh', '0203133692', '', '711A02671124', '29/08/80', '0');
INSERT INTO `tblnhanvien` VALUES ('THD08', '1304', '§ç BÝch Thñy', '0205274475', '', '711A03448088', '6/12/84', '0');
INSERT INTO `tblnhanvien` VALUES ('THD09', '1310', '§ç ThÞ Thanh Thñy', '0202160947', '', '711A01158751', '28/9/79', '0');
INSERT INTO `tblnhanvien` VALUES ('THD10', '1501', 'D­¬ng Träng §øc Thïy', '0203315601', '', '711A02587531', '26/8/81', '0');
INSERT INTO `tblnhanvien` VALUES ('THD11', '1308', '§Æng Huúnh Ph­¬ng Thy', '0206151388', '', '711A00606592', '13/4/81', '0');
INSERT INTO `tblnhanvien` VALUES ('THD12', '1301', 'D­¬ng Quang Th¶o', '0204356579', '', '711A01169006', '9/3/75', '1');
INSERT INTO `tblnhanvien` VALUES ('THD13', '1607', '§Æng thÞ Thao', '0296265457', '', '711A02598437', '04/4/70', '0');
INSERT INTO `tblnhanvien` VALUES ('THD14', '1304', '§ç ThÞ Kim Thuý', '0201092968', '', '711A05682404', '', '0');
INSERT INTO `tblnhanvien` VALUES ('THD15', '1306', '§oµn Nh­ ThiÕt', '0203333812', '', '711A01152943', '17/5/81', '1');
INSERT INTO `tblnhanvien` VALUES ('THD16', '1301', 'DiÖp ThÞ Th¬m', '0205206230', '', '711A02586851', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('THD17', '1302', '§oµn ThÞ Thªm', '0206316862', '', '711A05447471', '12/11/1984', '1');
INSERT INTO `tblnhanvien` VALUES ('THD18', '1612', 'D­¬ng Ngäc ThiÖn', '0207331857', '', '711A08242309', '1978', '1');
INSERT INTO `tblnhanvien` VALUES ('THD19', '1604', '§ç ViÖt Th¾ng', '0207140805', '', '711A05742481', '1982', '1');
INSERT INTO `tblnhanvien` VALUES ('THH01', '1315', 'Hµ V¨n Thoan', '0296265630', '', '711A01173609', '01/12/1961', '1');
INSERT INTO `tblnhanvien` VALUES ('THH02', '1506', 'H¸n ThÞ Thu', '0298025761', '', '711A01171143', '20/4/70', '0');
INSERT INTO `tblnhanvien` VALUES ('THH05', '1608', 'Huúnh Thanh', '0202160975', '', '711A05376725', '', '1');
INSERT INTO `tblnhanvien` VALUES ('THH06', '1318', 'Hoµng ThÞ Th¶o', '0204154510', '', '711A01252494', '27/7/80', '0');
INSERT INTO `tblnhanvien` VALUES ('THH07', '1607', 'Hµ ThÞ Thanh Thñy', '0204270865', '', '711A02598341', '01/06/82', '0');
INSERT INTO `tblnhanvien` VALUES ('THH08', '1601', 'Huúnh ThÞ Kim Thñy', '0204270861', '', '711A02587483', '01/12/82', '0');
INSERT INTO `tblnhanvien` VALUES ('THH10', '1604', 'Huúnh Ph­¬ng Uyªn Th¶o', '0203174864', '', '711A02591539', '19/5/76', '0');
INSERT INTO `tblnhanvien` VALUES ('THH11', '1318', 'Huúnh ThÞ Ngäc Thóy', '0202161012', '', '711A02670574', '11/12/74', '0');
INSERT INTO `tblnhanvien` VALUES ('THH12', '1315', 'Hå Anh Thy', '0202161048', '', '711A02595544', '20/4/72', '0');
INSERT INTO `tblnhanvien` VALUES ('THH13', '1305', 'Hoµng ThÞ Th¬', '0203133697', '', '711A02586681', '1979', '0');
INSERT INTO `tblnhanvien` VALUES ('THH14', '1301', 'Hoµng ThÞ Th¶o', '0207077565', '', '711A06156179', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('THH16', '1603', 'Hoµng ThÞ THuý', '0207465112', '', '711A12000882', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('THL01', '1302', 'L­¬ng Thanh Thñy', '0298025753', '', '711A01169143', '06/10/57', '0');
INSERT INTO `tblnhanvien` VALUES ('THL02', '1612', 'Lª TiÕn Th¾ng', '0298025776', '', '711A05376641', '09/2/66', '1');
INSERT INTO `tblnhanvien` VALUES ('THL03', '1304', 'Lª ThÞ Xu©n Thóy', '0298109057', '', '711A03447691', '07/3/67', '0');
INSERT INTO `tblnhanvien` VALUES ('THL05', '1403', 'L­îng thÞ Ph­¬ng Thanh', '0299097800', '', '711A02600192', '04/4/78', '0');
INSERT INTO `tblnhanvien` VALUES ('THL06', '1316', 'Lª Diªn ThÞnh', '0200054825', '', '711A02591976', '1970', '1');
INSERT INTO `tblnhanvien` VALUES ('THL08', '1300', 'Lª ThÞ Quý Th¶o', '0202100209', '', '711A01254584', '08/12/73', '0');
INSERT INTO `tblnhanvien` VALUES ('THL09', '1100', 'Lª Huúnh Ngäc Th­', '0202161075', '', '711A01178432', '5/4/76', '0');
INSERT INTO `tblnhanvien` VALUES ('THL10', '1603', 'Lª BÝch Thñy', '0299119522', '', '711A05742553', '24/2/74', '0');
INSERT INTO `tblnhanvien` VALUES ('THL11', '1306', 'Lª ThÞ Anh Th­', '0205274472', '', '711A04019493', '14/1/81', '0');
INSERT INTO `tblnhanvien` VALUES ('THL12', '1605', 'Lª ThÞ Kim Th¶o', '0202160928', '', '711A03517751', '16/2/79', '0');
INSERT INTO `tblnhanvien` VALUES ('THL13', '1303', 'Lª YÕn TH¶o', '0202160955', '', '711A02598607', '7/3/73', '0');
INSERT INTO `tblnhanvien` VALUES ('THL14', '1607', 'Lª Hång Ngäc Th¶o', '0205264644', '', '711A02599105', '', '0');
INSERT INTO `tblnhanvien` VALUES ('THL15', '1506', 'Lª ThÞ Nh­ Thanh', '0206121631', '', '711A02590195', '16/9/83', '0');
INSERT INTO `tblnhanvien` VALUES ('THL16', '1314', 'Lª Trung Thø', '0203315584', '', '711A01155874', '11/6/77', '1');
INSERT INTO `tblnhanvien` VALUES ('THL17', '1310', 'Lª Hoµng Long Thôy', '0201027044', '', '711A03446262', '1973', '1');
INSERT INTO `tblnhanvien` VALUES ('THL18', '1301', 'Lª ThÞ HuyÒn Th­¬ng', '0205089497', '', '711A01254193', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('THL19', '1400', 'Lª ThÞ Kim Thu', '0206151389', '', '711A05742538', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('THL20', '1400', 'Lª ThÞ HuyÒn Th­¬ng', '0206389629', '', '711A57938461', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('THL21', '1612', 'Lý ThÞ Kim Th¶o', '0207105750', '', '711A06350003', '14/7/85', '0');
INSERT INTO `tblnhanvien` VALUES ('THL22', '1400', 'L· ThÞ TuyÕt Thanh', '0207078830', '', '711A06350464', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('THL23', '1301', 'Lª §×nh ThÕ', '0207214969', '', '711A00890065', '1980', '1');
INSERT INTO `tblnhanvien` VALUES ('THN01', '1307', 'NguyÔn §×nh Thanh', '0296265411', '', '711A02588893', '17/7/59', '1');
INSERT INTO `tblnhanvien` VALUES ('THN04', '1304', 'Ng« ThÞ Ngäc Thanh', '0296265426', '', '711A02593305', '20/10/68', '0');
INSERT INTO `tblnhanvien` VALUES ('THN07', '1608', 'NguyÔn ThÞ Thanh', '0296265492', '', '711A01153347', '10/10/60', '0');
INSERT INTO `tblnhanvien` VALUES ('THN12', '1310', 'NguyÔn Anh Thi', '0296265691', '', '711A01254884', '09/12/61', '1');
INSERT INTO `tblnhanvien` VALUES ('THN13', '1310', 'NguyÔn V¨n Thµnh', '0296265692', '', '711A02989106', '01/8/63', '1');
INSERT INTO `tblnhanvien` VALUES ('THN16', '1502', 'NguyÔn ThÞ Thuû', '0296265444', '', '711A01173284', '13/10/72', '0');
INSERT INTO `tblnhanvien` VALUES ('THN18', '1310', 'NguyÔn ThÞ Th×n', '0298025749', '', '711A03445831', '05/1/75', '0');
INSERT INTO `tblnhanvien` VALUES ('THN20', '1607', 'NguyÔn Huy Th¾ng', '0298109078', '', '711A05508045', '25/3/69', '1');
INSERT INTO `tblnhanvien` VALUES ('THN21', '1607', 'NguyÔn Hång NhËt Thuý', '0299097787', '', '711A01150782', '28/12/77', '0');
INSERT INTO `tblnhanvien` VALUES ('THN23', '1602', 'NguyÔn TuÊn Thanh', '0200054831', '', '711A01254538', '1965', '1');
INSERT INTO `tblnhanvien` VALUES ('THN24', '1310', 'NguyÔn H­ò Thä', '0202100203', '', '711A01157918', '16/08/65', '1');
INSERT INTO `tblnhanvien` VALUES ('THN25', '1507', 'NguyÔn Quèc Thèng', '0203315594', '', '711A02596354', '16/5/80', '0');
INSERT INTO `tblnhanvien` VALUES ('THN26', '1507', 'NguyÔn Hång NhËt Th¶o', '0204270872', '', '711A01174761', '15/10/79', '0');
INSERT INTO `tblnhanvien` VALUES ('THN27', '1507', 'NguyÔn ThÞ Thanh Th¶o', '0205264645', '', '711A02596529', '1/12/83', '0');
INSERT INTO `tblnhanvien` VALUES ('THN28', '1306', 'NguyÔn ThÞ ThÊm', '0203333837', '', '711A02590665', '30/9/82', '0');
INSERT INTO `tblnhanvien` VALUES ('THN29', '1300', 'NguyÔn Vò Quang Thanh', '0203315596', '', '711A01156898', '11/4/78', '1');
INSERT INTO `tblnhanvien` VALUES ('THN30', '1314', 'NguyÔn TrÇn Minh Thi', '0206121632', '', '711A02601627', '26/6/85', '0');
INSERT INTO `tblnhanvien` VALUES ('THN31', '1303', 'NguyÔn Thi Kim Th¶o', '0203315589', '', '711A02599499', '', '0');
INSERT INTO `tblnhanvien` VALUES ('THN32', '1607', 'Ng« ThÞ Kim Thoa', '0202160959', '', '711A02598898', '16/5/79', '0');
INSERT INTO `tblnhanvien` VALUES ('THN33', '1601', 'NguyÔn D­¬ng C«ng Thôy', '0202160934', '', '711A02587152', '15/4/79', '0');
INSERT INTO `tblnhanvien` VALUES ('THN34', '1304', 'NguyÔn ThÞ Ngäc Thóy', '0203133676', '', '711A01145231', '30/10/81', '0');
INSERT INTO `tblnhanvien` VALUES ('THN35', '1317', 'NguyÔn ThÞ LiÔu Thóy', '0204356578', '', '711A01176172', '10/9/80', '0');
INSERT INTO `tblnhanvien` VALUES ('THN36', '1604', 'NguyÔn ThÞ Th­¬ng', '0206369522', '', '711A05470619', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('THN37', '1605', 'NguyÔn ThÞ Ngäc Thanh', '0203174855', '', '711A02587307', '14/12/73', '0');
INSERT INTO `tblnhanvien` VALUES ('THN38', '1315', 'NguyÔn Minh Thµnh', '0203133693', '', '711A03518258', '1/1/77', '1');
INSERT INTO `tblnhanvien` VALUES ('THN39', '1506', 'NguyÔn ThÞ Thanh Thïy', '0203174851', '', '711A01327509', '', '0');
INSERT INTO `tblnhanvien` VALUES ('THN40', '1304', 'NguyÔn Anh Th­', '0203174848', '', '711A05470658', '1/1/76', '0');
INSERT INTO `tblnhanvien` VALUES ('THN41', '1308', 'NguyÔn ThÞ Thu Thanh', '0204270857', '', '711A01160664', '9/9/71', '0');
INSERT INTO `tblnhanvien` VALUES ('THN42', '1303', 'NguyÔn ThÞ Kim Thóy', '0202160956', '', '711A02598791', '01/09/80', '0');
INSERT INTO `tblnhanvien` VALUES ('THN44', '1601', 'Ng« ThÞ Thanh Th¶o', '0207105757', '', '711A06350303', '30/1/86', '0');
INSERT INTO `tblnhanvien` VALUES ('THN45', '1601', 'NguyÔn ThÞ DiÖu Th¾m', '0207105758', '', '711A06350282', '31/10/86', '0');
INSERT INTO `tblnhanvien` VALUES ('THN46', '1301', 'NguyÔn Méng Thu', '0207105740', '', '711A06350191', '06/9/84', '0');
INSERT INTO `tblnhanvien` VALUES ('THN47', '1503', 'NguyÔn ThÞ Kim Thy', '0207250108', '', '711A08242351', '1978', '0');
INSERT INTO `tblnhanvien` VALUES ('THN48', '1504', 'NguyÔn Xu©n Th¾ng', '0202015592', '', '711A02596172', '1969', '1');
INSERT INTO `tblnhanvien` VALUES ('THP01', '1100', 'Ph¹m ThÞ Thu', '0296265591', '', '711A01177752', '15/7/57', '0');
INSERT INTO `tblnhanvien` VALUES ('THP02', '1200', 'Ph¹m §¨ng Th¾ng', '0296265559', '', '711A05447372', '20/10/63', '1');
INSERT INTO `tblnhanvien` VALUES ('THP04', '1508', 'Phan ThÞ Thuyªn', '0296265635', '', '711A02593238', '24/8/61', '0');
INSERT INTO `tblnhanvien` VALUES ('THP05', '1401', 'Phan ThÞ Thon', '0204356583', '', '711A01159892', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('THP06', '1403', 'Ph¹m thÞ Thu Th¶o', '0299097798', '', '711A02600122', '10/02/78', '0');
INSERT INTO `tblnhanvien` VALUES ('THP08', '1306', 'Ph¹m ThÞ BÝch Thñy', '0202160983', '', '711A01153028', '29/10/77', '0');
INSERT INTO `tblnhanvien` VALUES ('THP09', '1304', 'Ph¹m ThÞ Thanh Thñy', '0207331854', '', '711A07741637', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('THT01', '1309', 'TrÇn Ph­¬ng Thu', '0296265491', '', '711A05376677', '1960', '0');
INSERT INTO `tblnhanvien` VALUES ('THT04', '1306', 'Tr­¬ng LËp Träng Thñy', '0296265523', '', '711A03517239', '17/5/72', '1');
INSERT INTO `tblnhanvien` VALUES ('THT05', '1200', 'TrÇn §øc Th¾ng', '0298025763', '', '711A01178129', '05/05/59', '1');
INSERT INTO `tblnhanvien` VALUES ('THT06', '1610', 'TrÇn Xu©n Th«ng', '0298025769', '', '711A01160885', '11/12/67', '1');
INSERT INTO `tblnhanvien` VALUES ('THT07', '1305', 'TrÞnh §×nh Th¾ng', '0298109076', '', '711A01143263', '31/5/70', '1');
INSERT INTO `tblnhanvien` VALUES ('THT08', '1603', 'TrÇn ThÞ Ph­¬ng Th¶o', '0204101929', '', '711A01153698', '1973', '0');
INSERT INTO `tblnhanvien` VALUES ('THT09', '1316', 'TrÇn Nam Th¸i', '0201068837', '', '711A02602106', '17/09/76', '1');
INSERT INTO `tblnhanvien` VALUES ('THT10', '1606', 'T¹ C«ng Thµnh', '0202100206', '', '711A04371701', '20/02/70', '1');
INSERT INTO `tblnhanvien` VALUES ('THT11', '1601', 'TrÇn LÖ DiÔm Thuý', '0202100188', '', '711A01252352', '06/02/65', '0');
INSERT INTO `tblnhanvien` VALUES ('THT12', '1310', 'TrÇn Anh Th«ng', '0202161056', '', '711A01158566', '10/1/71', '1');
INSERT INTO `tblnhanvien` VALUES ('THT13', '1318', 'Tr­¬ng ThÞ Thu Th¶o', '0204270859', '', '711A01144894', '16/05/82', '0');
INSERT INTO `tblnhanvien` VALUES ('THT14', '1507', 'Tr­¬ng Ngäc Anh Thy', '0202160996', '', '711A01174513', '25/12/80', '0');
INSERT INTO `tblnhanvien` VALUES ('THT15', '1306', 'TrÇn ThÞ Xu©n Thñy', '0203315592', '', '711A02589391', '25/10/80', '0');
INSERT INTO `tblnhanvien` VALUES ('THT16', '1301', 'T« Thanh Thñy', '0203133679', '', '711A01145401', '06/06/79', '0');
INSERT INTO `tblnhanvien` VALUES ('THT17', '1314', 'Th©n ThÞ Anh Th­', '0204270886', '', '711A01252581', '29/11/81', '0');
INSERT INTO `tblnhanvien` VALUES ('THT18', '1506', 'TrÇn ThÞ Thanh Th¶o', '0202161036', '', '711A02589566', '1/1/79', '0');
INSERT INTO `tblnhanvien` VALUES ('THT19', '1506', 'Tr­¬ng Ph­¬ng Th¶o', '0206121633', '', '711A03517293', '21/5/84', '0');
INSERT INTO `tblnhanvien` VALUES ('THT20', '1606', 'TrÇn ThÞ Thanh Th¶o', '0205264646', '', '711A02587689', '17/09/81', '0');
INSERT INTO `tblnhanvien` VALUES ('THT21', '1608', 'TrÇn ThÞ Méng ThuyÒn', '0206121634', '', '711A02601184', '30/10/83', '0');
INSERT INTO `tblnhanvien` VALUES ('THT22', '1305', 'TrÇn Kinh Thµnh', '0206151390', '', '711A03448782', '12/6/77', '0');
INSERT INTO `tblnhanvien` VALUES ('THT23', '1200', 'TrÞnh Xu©n Thñy', '0204069589', '', '711A01178168', '14/02/82', '1');
INSERT INTO `tblnhanvien` VALUES ('THT24', '1303', 'TrÇn ThÞ Mai Thy', '0203174847', '', '711A01147131', '13/8/78', '0');
INSERT INTO `tblnhanvien` VALUES ('THT25', '1301', 'TrÇn ThÞ Kim Thanh', '0205206233', '', '711A01421402', '1979', '0');
INSERT INTO `tblnhanvien` VALUES ('THV01', '1200', 'Vò Hång Th¸i', '0296265708', '', '711A01177953', '06/10/61', '1');
INSERT INTO `tblnhanvien` VALUES ('THV02', '1403', 'Vâ ThÞ Thoa', '0296265679', '', '711A02600094', '12/09/62', '0');
INSERT INTO `tblnhanvien` VALUES ('THV03', '1100', 'V¨n ThÞ Hång Thñy', '0203160835', ' 023012620', '711A04477968', '03/05/76', '0');
INSERT INTO `tblnhanvien` VALUES ('THV04', '1305', 'Vò ThÞ Thµnh', '0296265396', '', '711A01144697', '15/4/58', '0');
INSERT INTO `tblnhanvien` VALUES ('THV05', '1400', 'Vò Kim Thu', '0296265620', '', '711A05511423', '27/08/74', '0');
INSERT INTO `tblnhanvien` VALUES ('THV06', '1403', 'Vò ThÞ Thuû', '0205182009', '', '711A02671002', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('TIL01', '1309', 'Lª TrÇn §øc TÝn', '0202160968', '', '711A02592064', '1/1/71', '1');
INSERT INTO `tblnhanvien` VALUES ('TIN01', '1310', 'NguyÔn ThÞ Thñy Tiªn', '0296265504', '', '711A03444824', '09/2/61', '0');
INSERT INTO `tblnhanvien` VALUES ('TIN02', '1306', 'NguyÔn Trung TÝn', '0296265517', '', '711A02670294', '30/5/69', '1');
INSERT INTO `tblnhanvien` VALUES ('TIN03', '1401', 'NguyÔn V­¬ng Quèc Mü Tiªn', '0203174903', '', '711A03445483', '4/6/78', '0');
INSERT INTO `tblnhanvien` VALUES ('TIP01', '1306', 'Ph¹m ThÞ TiÕn', '0296265508', '', '711A02591649', '15/11/61', '0');
INSERT INTO `tblnhanvien` VALUES ('TIP02', '1401', 'Phan V¨n T×nh', '0205080881', '', '711A05447523', '30/12/75', '1');
INSERT INTO `tblnhanvien` VALUES ('TIT03', '1306', 'T¨ng TiÕn', '0207105747', '', '711A06350843', '02/1/86', '1');
INSERT INTO `tblnhanvien` VALUES ('TOD01', '1308', '§ç Xu©n Toµn', '0202160974', '', '711A03445034', '01/12/69', '0');
INSERT INTO `tblnhanvien` VALUES ('TON02', '1507', 'NguyÔn C«ng To¹i', '0296265723', '', '711A03517712', '31/12/64', '1');
INSERT INTO `tblnhanvien` VALUES ('TON03', '1220', 'NguyÔn Quèc To¶n', '0296265413', '', '711A01142923', '28/4/63', '1');
INSERT INTO `tblnhanvien` VALUES ('TON04', '1309', 'NguyÔn ThÕ Toµn', '0203174860', '', '711A02592428', '12/12/77', '1');
INSERT INTO `tblnhanvien` VALUES ('TOT01', '1314', 'TrÇn V¨n Tèt', '0296265576', '', '711A02602133', '18/12/68', '0');
INSERT INTO `tblnhanvien` VALUES ('TRB01', '1507', 'Bïi Minh Trung', '0203333832', '', '711A02596693', '10/2/78', '1');
INSERT INTO `tblnhanvien` VALUES ('TRC01', '1306', 'CÊn §×nh Träng', '0296265502', '', '711A03517179', '15/6/53', '1');
INSERT INTO `tblnhanvien` VALUES ('TRD01', '1606', 'D­¬ng Duy Trang', '0298068180', '', '711A21029727', '13/6/71', '1');
INSERT INTO `tblnhanvien` VALUES ('TRD03', '1310', 'D­¬ng Nh· Tróc', '0205080885', '', '711A02596465', '16/9/83', '0');
INSERT INTO `tblnhanvien` VALUES ('TRD04', '1603', '§oµn ThÞ Thu Trang', '0203315564', '', '711A01153398', '3/7/79', '0');
INSERT INTO `tblnhanvien` VALUES ('TRD05', '1403', '§µo Hång Tr©m', '0203174908', '', '711A03444843', '9/10/81', '0');
INSERT INTO `tblnhanvien` VALUES ('TRD06', '1310', 'D­¬ng Do·n Thïy Trang', '0202160920', '', '711A01145061', '27/3/72', '0');
INSERT INTO `tblnhanvien` VALUES ('TRD08', '1608', '§inh NguyÔn Ph­¬ng Tróc', '0207140798', '', '711A08242296', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('TRD09', '1507', 'D­¬ng Thanh Tróc', '0207140814', '', '711A06554883', '1986', '0');
INSERT INTO `tblnhanvien` VALUES ('TRH01', '1400', 'Huúnh HiÒn Trung', '0299051658', '', '711A03518297', '12/9/68', '1');
INSERT INTO `tblnhanvien` VALUES ('TRH02', '1301', 'Huúnh ThÞ Ngäc Tr©m', '0202161007', '', '711A01170014', '21/1/80', '0');
INSERT INTO `tblnhanvien` VALUES ('TRL01', '1610', 'Lª ThÞ Thanh Tróc', '0296265466', '', '711A03444993', '17/12/66', '0');
INSERT INTO `tblnhanvien` VALUES ('TRL02', '1306', 'Lç B¸ Träng', '0296265505', '', '711A02589143', '15/07/53', '1');
INSERT INTO `tblnhanvien` VALUES ('TRL03', '1603', 'Lý TuyÕt Trinh', '0205264647', '', '711A01183104', '21/8/82', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN01', '1315', 'NguyÔn §¨ng Träng', '0296265633', '', '711A02595477', '3/6/56', '1');
INSERT INTO `tblnhanvien` VALUES ('TRN02', '1506', 'NguyÔn Tróc', '0296265521', '', '711A01171076', '06/9/71', '1');
INSERT INTO `tblnhanvien` VALUES ('TRN03', '1502', 'NguyÔn ThÞ TuyÕt Trinh', '0296265429', '', '711A03836551', '', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN06', '1301', 'NguyÔn Thôy Trinh', '0201092973', '', '711A01169944', '03/10/76', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN07', '1507', 'NguyÔn ThÞ Ngäc Trang', '0204270874', '', '711A01174883', '21/12/81', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN08', '1507', 'NguyÔn Thôy Ngäc Tr©n', '0204270896', '', '711A01174904', '29/06/82', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN09', '1314', 'NguyÔn Ngäc Trang', '0202161021', '', '711A01156614', '06/03/78', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN10', '1306', 'NguyÔn THÞ Hång Tr©m', '0205274476', '', '711A02589352', '30/03/80', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN11', '1303', 'NguyÔn V¨n TrÝ', '0203174878', '', '711A02599581', '10/7/73', '1');
INSERT INTO `tblnhanvien` VALUES ('TRN12', '1601', 'NguyÔn ThÞ LÖ Trang', '0205109967', '', '711A01170984', '18/10/80', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN17', '1401', 'NguyÔn Thñy Ph­¬ng Trang', '0206103584', '', '711A02591712', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN19', '1607', 'NguyÔn Ngäc Thïy Trang', '0207105768', '', '711A06350752', '30/11/86', '0');
INSERT INTO `tblnhanvien` VALUES ('TRN20', '1607', 'Ng« ThÞ Kim Trinh', '0207331862', '', '711A05802075', '1977', '0');
INSERT INTO `tblnhanvien` VALUES ('TRP01', '1300', 'Ph¹m ThÞ Thïy Trang', '0202161073', '', '711A01178372', '24/11/79', '0');
INSERT INTO `tblnhanvien` VALUES ('TRP02', '1308', 'Ph¹m Thanh Tróc', '5997022942', '', '711A05376582', '10/12/69', '0');
INSERT INTO `tblnhanvien` VALUES ('TRT01', '1314', 'TrÇn V¨n Tr­êng', '0296265720', '', '711A02601733', '08/11/61', '1');
INSERT INTO `tblnhanvien` VALUES ('TRT02', '1301', 'T¨ng B¶o Tr©n', '0204270867', '', '711A01170163', '27/07/82', '0');
INSERT INTO `tblnhanvien` VALUES ('TRT03', '1314', 'TRÇn ThÞ TuyÕt Trinh', '0205264648', '', '711A02601524', '13/12/84', '0');
INSERT INTO `tblnhanvien` VALUES ('TRT04', '1606', 'Tèng ThÞ Mai Tr©m', '0204270860', '', '711A02593423', '12/01/82', '0');
INSERT INTO `tblnhanvien` VALUES ('TRT05', '1305', 'Th¸i §øc TrÝ', '0206151392', '', '711A03448728', '29/4/81', '1');
INSERT INTO `tblnhanvien` VALUES ('TRT06', '1601', 'TrÇn Thóy Anh Trang', '0206151391', '', '711A02989054', '28/9/81', '0');
INSERT INTO `tblnhanvien` VALUES ('TRT07', '1303', 'Th©n ThÞ Minh Trung', '0296010604', '', '711A01288079', '28/1/67', '0');
INSERT INTO `tblnhanvien` VALUES ('TRT09', '1310', 'TrÇn V­¬ng Trung', '0204078829', '', '711A08242272', '1973', '1');
INSERT INTO `tblnhanvien` VALUES ('TRV01', '1606', 'Vò ThÞ Thu Trang', '0107034979', '', '711A05896586', '1981', '0');
INSERT INTO `tblnhanvien` VALUES ('TRV02', '1613', 'Vâ Huúnh Mai Tr©m', '0203174901', '', '711A04019272', '19/1/80', '0');
INSERT INTO `tblnhanvien` VALUES ('TRŸ01', '1501', '§oµn ThÞ HuyÒn Tr©n', '0296247171', '', '711A02595422', '17/9/68', '0');
INSERT INTO `tblnhanvien` VALUES ('TUB01', '1504', 'Bïi ThÞ B¹ch TuyÕt', '0296265736', '', '711A01170705', '15/11/59', '0');
INSERT INTO `tblnhanvien` VALUES ('TUB02', '1310', 'Bïi Trung TuyÕn', '0207140792', '', '711A11813586', '1986', '1');
INSERT INTO `tblnhanvien` VALUES ('TUD01', '1603', '§ç V¨n TuÊn', '0296265493', '', '711A01153362', '10/1/62', '1');
INSERT INTO `tblnhanvien` VALUES ('TUD02', '1316', '§oµn ThÞ Kim TuyÒn', '0204270866', '', '711A01173075', '13/12/81', '0');
INSERT INTO `tblnhanvien` VALUES ('TUD03', '1310', 'D­¬ng Hoµi TuÊn', '0205080883', '', '711A01254951', '11/5/83', '1');
INSERT INTO `tblnhanvien` VALUES ('TUH02', '1302', 'Hoµng ThÞ Tuyªn', '0296265440', '', '711A01169116', '15/4/61', '0');
INSERT INTO `tblnhanvien` VALUES ('TUH03', '1306', 'Hµ ThÞ Kim TuyÕn', '5601001658', '', '711A02590965', '02/9/76', '0');
INSERT INTO `tblnhanvien` VALUES ('TUK01', '1507', 'Kh©u Minh TuÊn', '0205089499', '', '711A03518391', '26/9/75', '1');
INSERT INTO `tblnhanvien` VALUES ('TUL04', '1605', 'Lª Minh Tó', '0202100200', '', '711A03517581', '30/07/70', '1');
INSERT INTO `tblnhanvien` VALUES ('TUL05', '1306', 'Lª ThÞ Hång T­¬i', '0203315567', '', '711A02591096', '7/12/81', '0');
INSERT INTO `tblnhanvien` VALUES ('TUL06', '1506', 'Lª ThÞ Ngäc TuyÒn', '0206151393', '', '711A03517313', '24/7/83', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN01', '1501', 'NguyÔn Träng Tù', '0296265711', '', '711A01174982', '08/8/56', '1');
INSERT INTO `tblnhanvien` VALUES ('TUN03', '1401', 'NguyÔn §×nh TuÊn', '0296265644', '', '711A03444681', '7/1/61', '1');
INSERT INTO `tblnhanvien` VALUES ('TUN04', '1314', 'NguyÔn V¨n TuÊn', '0298109056', '', '711A03518222', '02/12/71', '1');
INSERT INTO `tblnhanvien` VALUES ('TUN05', '1508', 'NguyÔn ThÞ Hång TuyÕt', '0204154522', '', '711A01173032', '1982', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN06', '1315', 'Ng« Quang TuÖ', '0296294037', '', '711A02595604', '01/12/70', '1');
INSERT INTO `tblnhanvien` VALUES ('TUN07', '1301', 'NguyÔn ThÞ Ngäc TuyÒn', '0201092976', '', '711A01169991', '16/3/79', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN09', '1314', 'NguyÔn Hoµng BÝch TuyÒn', '0201092990', '', '711A01156491', '03/11/79', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN10', '1603', 'Ng« ¸nh TuyÕt', '0206121635', '', '711A02601102', '27/10/84', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN11', '1314', 'NguyÔn ThÞ Nh­ TuyÕt', '0204270885', '', '711A01157696', '14/01/83', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN12', '1314', 'NguyÔn D­¬ng BÝch TuyÒn', '0204270900', '', '711A01157799', '19/03/82', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN14', '1303', 'NguyÔn ThÞ T­¬i', '0202160960', '', '711A02599254', '8/12/72', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN15', '1401', 'NguyÔn THÞ CÈm Tó', '0203052635', '', '711A03445471', '25/05/80', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN16', '1501', 'NguyÔn Anh TuyÒn', '0206151394', '', '711A04019382', '5/7/78', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN17', '1316', 'NguyÔn Phóc Minh TuÖ', '0204209009', '', '711A02592664', '1/1/79', '1');
INSERT INTO `tblnhanvien` VALUES ('TUN19', '1606', 'NguyÔn ThÞ Quúnh T­', '0202160919', '', '711A01145089', '31/08/72', '0');
INSERT INTO `tblnhanvien` VALUES ('TUN20', '1310', 'NguyÔn ThÞ TuyÕt', '0206369525', '', '711A05470358', '1983', '0');
INSERT INTO `tblnhanvien` VALUES ('TUP02', '1605', 'Ph¹m §×nh TuyÕn', '0296023388', '', '711A02587046', '24/04/64', '1');
INSERT INTO `tblnhanvien` VALUES ('TUP04', '1503', 'Ph¹m ThÞ Thanh TuyÒn', '0204270890', '', '711A01152793', '19/03/82', '0');
INSERT INTO `tblnhanvien` VALUES ('TUP05', '1306', 'Phan Thanh T­¬i', '0202160978', '', '711A03517151', '16/8/58', '1');
INSERT INTO `tblnhanvien` VALUES ('TUT01', '1220', 'TrÇn Thanh Tïng', '0296265553', '', '711A03447964', '20/2/56', '1');
INSERT INTO `tblnhanvien` VALUES ('TUT03', '1507', 'TrÞnh Minh TuÊn', '0296265543', '', '711A02596942', '06/6/72', '1');
INSERT INTO `tblnhanvien` VALUES ('TUT04', '1314', 'TrÇn Thanh TuyÒn', '0298025771', '', '711A03448889', '02/10/67', '1');
INSERT INTO `tblnhanvien` VALUES ('TUT05', '1400', 'TrÇn Kh¶i Tó', '0205274488', '', '711A02595647', '30/7/83', '0');
INSERT INTO `tblnhanvien` VALUES ('TUT06', '1304', 'TrÇn ThÞ Hång TuyÕt', '0205274477', '', '711A02602322', '5/9/83', '0');
INSERT INTO `tblnhanvien` VALUES ('TUT07', '1309', 'TrÇn Kh¸nh T­êng', '0202160993', '', '711A01174604', '27/04/77', '1');
INSERT INTO `tblnhanvien` VALUES ('TUT08', '1507', 'TrÇn Thanh Tïng', '0203174863', '', '711A01153161', '1975', '1');
INSERT INTO `tblnhanvien` VALUES ('TUT09', '1315', 'Tõ Quèc TuÊn', '0203052632', '', '711A04371938', '', '1');
INSERT INTO `tblnhanvien` VALUES ('TUV01', '1504', 'Vâ ThÞ Méng TuyÒn', '0298025784', '', '711A01170799', '05/3/', '0');
INSERT INTO `tblnhanvien` VALUES ('UEP01', '1300', 'Ph¹m ThÞ Minh Huª', '0296265685', '', '711A03448301', '17/9/67', '0');
INSERT INTO `tblnhanvien` VALUES ('UID01', '1504', '§Æng ThÞ Thóy Mïi', '0296265682', '', '711A01170732', '09/03/63', '0');
INSERT INTO `tblnhanvien` VALUES ('UNG01', '1503', 'Giang ThÞ LÖ Dung', '0296265565', '', '711A01255012', '17/1/67', '0');
INSERT INTO `tblnhanvien` VALUES ('UTN01', '1310', 'NguyÔn ThÞ óT', '0298025783', '', '711A03446365', '07/02/68', '0');
INSERT INTO `tblnhanvien` VALUES ('UTN02', '1310', 'NguyÔn V¨n ót', '0202161057', '', '711A01158072', '3/4/73', '1');
INSERT INTO `tblnhanvien` VALUES ('UTT01', '1310', 'Tèng V¨n ót', '0299097801', '', '711A03541293', '17/12/65', '1');
INSERT INTO `tblnhanvien` VALUES ('UUN01', '1507', 'NguyÔn Ngäc Lùu', '0296265549', '', '711A02602579', '09/2/69', '0');
INSERT INTO `tblnhanvien` VALUES ('UYD01', '1318', 'D­¬ng ThÞ Thanh Thñy', '0296265750', '', '711A01168893', '', '0');
INSERT INTO `tblnhanvien` VALUES ('UYD02', '1100', '§ç ThÞ Uy', '0202161071', '', '711A02590511', '22/12/70', '0');
INSERT INTO `tblnhanvien` VALUES ('UYL01', '1305', 'Lª ThÞ DiÔm Thóy', '0296265408', '', '711A01143673', '21/7/68', '0');
INSERT INTO `tblnhanvien` VALUES ('UYN01', '1308', 'NguyÔn ThÞ Thu Thñy', '0296265639', '', '711A02596023', '21/10/65', '0');
INSERT INTO `tblnhanvien` VALUES ('UYN02', '1608', 'NguyÔn Quang Huy', '0296265757', '', '711A02592691', '13/11/67', '1');
INSERT INTO `tblnhanvien` VALUES ('UYN03', '1310', 'NguyÔn ThÞ LÖ Uyªn', '0203174883', '', '711A01182743', '11/9/80', '0');
INSERT INTO `tblnhanvien` VALUES ('UYP01', '1200', 'Ph¹m H÷u Y', '0296265714', '', '711A05447302', '20/11/56', '1');
INSERT INTO `tblnhanvien` VALUES ('UYP02', '1301', 'Phan ThÞ Thuý Uyªn', '0203174867', '', '711A02586366', '24/11/76', '0');
INSERT INTO `tblnhanvien` VALUES ('UYT01', '1314', 'Tr­¬ng ThÞ Thu Thñy', '0296265585', '', '711A01156307', '20/9/66', '0');
INSERT INTO `tblnhanvien` VALUES ('VAL01', '1508', 'Lª ¸i V©n', '0296265561', '', '711A01254502', '17/07/64', '0');
INSERT INTO `tblnhanvien` VALUES ('VAL02', '1314', 'L­u Hoµng Hång V©n', '0296265581', '', '711A02671318', '4/6/69', '0');
INSERT INTO `tblnhanvien` VALUES ('VAL03', '1610', 'Lª thÞ H¶i V©n', '0298109074', '', '711A02586939', '11/6/77', '0');
INSERT INTO `tblnhanvien` VALUES ('VAL04', '1307', 'Lª ThÞ Ngäc V©n', '0298109081', '', '711A01173099', '02/1/64', '0');
INSERT INTO `tblnhanvien` VALUES ('VAL05', '1607', 'Lª ThÞ Hång V©n', '0202160953', '', '711A02599732', '23/3/79', '0');
INSERT INTO `tblnhanvien` VALUES ('VAL06', '1315', 'Lª ThÞ CÈm V©n', '0206121641', '', '711A06350874', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('VAL07', '1506', 'Lª H¶i V©n', '0206316867', '', '711A05376771', '1979', '0');
INSERT INTO `tblnhanvien` VALUES ('VAN01', '1507', 'NguyÔn ThÞ Hång v©n', '0296265437', '', '711A01174943', '06/01/60', '0');
INSERT INTO `tblnhanvien` VALUES ('VAN02', '1608', 'NguyÔn ThÞ Thanh V©n', '0296265519', '', '711A01152891', '04/8/68', '0');
INSERT INTO `tblnhanvien` VALUES ('VAN03', '1304', 'NguyÔn ThÞ Thu v©n', '0296265419', '', '711A02593324', '11/2/58', '0');
INSERT INTO `tblnhanvien` VALUES ('VAN04', '1306', 'NguyÔn ThÞ Hång V©n', '0202160984', '', '711A01254454', '', '0');
INSERT INTO `tblnhanvien` VALUES ('VAN05', '1506', 'Ng« ThÞ ¸nh V©n', '0202161030', '', '711A01171364', '6/7/80', '0');
INSERT INTO `tblnhanvien` VALUES ('VAP01', '1604', 'Ph¹m ThÞ V©n', '0207105735', '', '711A05903762', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('VAT01', '1314', 'TrÇn Th­îng V¨n', '', '', '711A03518219', '', '1');
INSERT INTO `tblnhanvien` VALUES ('VAT02', '1400', 'TrÞnh T­êng V©n', '0202161078', '', '711A12673588', '25/01/66', '0');
INSERT INTO `tblnhanvien` VALUES ('VAT03', '1403', 'TrÇn ThÞ ¸i V©n', '0205264649', '', '711A02587452', '30/6/83', '0');
INSERT INTO `tblnhanvien` VALUES ('VAT05', '1506', 'TrÇn ThÞ B¹ch V©n', '0202161029', '', '711A02590117', '9/1/67', '0');
INSERT INTO `tblnhanvien` VALUES ('VAV01', '1220', 'Vò ThÞ Thanh V©n', '0204154523', '', '711A02591782', '17/10/80', '0');
INSERT INTO `tblnhanvien` VALUES ('VEL01', '1303', 'Lª ThÞ VÑn', '0202160954', '', '711A02599384', '4/10/79', '0');
INSERT INTO `tblnhanvien` VALUES ('VID02', '1507', '§Æng Quang Vinh', '0205264650', '', '711A01175047', '10/6/69', '1');
INSERT INTO `tblnhanvien` VALUES ('VIH01', '1305', 'Høa Thóy Vi', '0203333811', '', '711A02595067', '13/4/77', '0');
INSERT INTO `tblnhanvien` VALUES ('VIM01', '1501', 'Mai Ph­íc VÜnh', '0206286315', '', '711A04019309', '1979', '1');
INSERT INTO `tblnhanvien` VALUES ('VIN01', '1508', 'NguyÔn ThÞ VÜnh', '0205089500', '', '711A01170278', '13/1/81', '0');
INSERT INTO `tblnhanvien` VALUES ('VIN02', '1607', 'Ng« Thôy Hµ Vi', '0206121637', '', '711A02598677', '17/5/85', '0');
INSERT INTO `tblnhanvien` VALUES ('VIP01', '1306', 'Phïng ThÞ Xu©n Vinh', '0203133683', '', '711A01153094', '13/07/81', '0');
INSERT INTO `tblnhanvien` VALUES ('VIP02', '1304', 'Phan Quèc Vinh', '0203133698', '', '711A01169431', '20/11/76', '1');
INSERT INTO `tblnhanvien` VALUES ('VIT01', '1507', 'TrÇn Duy ViÔn', '0297120890', '', '711A03447333', '05/2/54', '1');
INSERT INTO `tblnhanvien` VALUES ('VIT02', '1613', 'Tr­¬ng SÜ Vinh', '0202161000', '', '711A01327661', '11/10/72', '1');
INSERT INTO `tblnhanvien` VALUES ('VIT03', '1607', 'TrÇn ThÞ Vinh', '0202160950', '', '711A02598771', '28/5/75', '0');
INSERT INTO `tblnhanvien` VALUES ('VIV01', '1200', 'V­¬ng TuÊn Vinh', '0296265713', '', '711A01178007', '12/12/60', '1');
INSERT INTO `tblnhanvien` VALUES ('VON02', '1301', 'NguyÔn ThÞ Vãc', '0299097793', '', '711A01169892', '18/6/76', '0');
INSERT INTO `tblnhanvien` VALUES ('VUL01', '1506', 'Lª ThÞ Hång Vò', '0203315580', '', '711A02586512', '27/8/74', '0');
INSERT INTO `tblnhanvien` VALUES ('VUN02', '1610', 'NguyÔn ThÞ V­îng', '0205325879', '', '711A08808549', '1980', '0');
INSERT INTO `tblnhanvien` VALUES ('VUP01', '1506', 'Ph¹m ThÞ Hoµng Vò', '0203315597', '', '711A02590014', '9/3/82', '0');
INSERT INTO `tblnhanvien` VALUES ('VUT01', '1310', 'TrÇn Thanh Vò', '0203174910', '', '711A01157969', '30/11/76', '1');
INSERT INTO `tblnhanvien` VALUES ('XAL01', '1310', 'Lª ThÞ Xu©n Xanh', '0298025748', '', '711A02600801', '29/9/70', '0');
INSERT INTO `tblnhanvien` VALUES ('XUL01', '1315', 'Lý V¨n Xu©n', '0296265595', '', '711A01142959', '21/3/60', '1');
INSERT INTO `tblnhanvien` VALUES ('XUN01', '1403', 'NguyÔn ThÞ XuyÕn', '0296265678', '', '711A02600544', '01/10/62', '0');
INSERT INTO `tblnhanvien` VALUES ('XUN02', '1310', 'NguyÔn ThÞ Thu X­¬ng', '0299097794', '', '711A01254095', '11/12/78', '0');
INSERT INTO `tblnhanvien` VALUES ('XUN03', '1504', 'NguyÔn ThÞ Thanh Xu©n', '0204154526', '', '711A02670811', '7/1/1968', '0');
INSERT INTO `tblnhanvien` VALUES ('XUT01', '1315', 'TrÇn LÖ Xu©n', '0204270893', '', '711A05507969', '14/01/82', '0');
INSERT INTO `tblnhanvien` VALUES ('XUV01', '1507', 'Vâ ThÞ Xu©n', '0296265534', '', '711A01252436', '25/6/71', '0');
INSERT INTO `tblnhanvien` VALUES ('ŸAB01', '1306', 'Bïi ThÞ §¶o', '0201092978', '', '711A02590972', '1978', '0');
INSERT INTO `tblnhanvien` VALUES ('ŸAP01', '1606', 'Ph¹m §øc §¹t', '0200054830', '', '711A02588092', '19/11/72', '1');
INSERT INTO `tblnhanvien` VALUES ('YEC01', '1310', 'Ch©u Hoµng YÕn', '0299059281', '', '711A03541254', '06/11/71', '0');
INSERT INTO `tblnhanvien` VALUES ('YEH01', '1306', 'Hoµng ThÞ YÕn', '0298109077', '', '711A01152979', '04/8/64', '0');
INSERT INTO `tblnhanvien` VALUES ('YEH02', '1301', 'Huúnh ThÞ Kim YÕn', '0204270870', '', '711A02586894', '18/02/83', '0');
INSERT INTO `tblnhanvien` VALUES ('YEL01', '1318', 'L©m Hoµng YÕn', '0296265779', '', '711A02595792', '12/10/63', '0');
INSERT INTO `tblnhanvien` VALUES ('YEN01', '1303', 'NguyÔn ThÞ YÕn', '0296265451', '', '711A02598183', '20/02/60', '0');
INSERT INTO `tblnhanvien` VALUES ('YEN02', '1310', 'NguyÔn ThÞ Hång YÕn', '0299097791', '', '711A01174158', '8/4/78', '0');
INSERT INTO `tblnhanvien` VALUES ('YEN03', '1506', 'NguyÔn ThÞ Kim YÕn', '0203185367', '', '711A04019296', '8/5/77', '0');
INSERT INTO `tblnhanvien` VALUES ('YEN04', '1305', 'NguyÔn ThÞ Xu©n YÕn', '0203333810', '', '711A02594912', '1/9/78', '0');
INSERT INTO `tblnhanvien` VALUES ('YEN05', '1504', 'NguyÔn ThÞ H¶i YÕn', '0207140787', '', '711A06351053', '1985', '0');
INSERT INTO `tblnhanvien` VALUES ('YEP01', '1306', 'Ph¹m ThÞ YÕn', '0207294609', '', '711A07740893', '1984', '0');
INSERT INTO `tblnhanvien` VALUES ('YET02', '1507', 'Tr­¬ng ThÞ Hoµng yÕn', '0203133684', '', '711A02596192', '27/05/80', '0');
INSERT INTO `tblnhanvien` VALUES ('YEV01', '1606', 'Vâ ThÞ Mü Yªn', '0205264653', '', '711A02593387', '29/5/84', '0');
INSERT INTO `tblnhanvien` VALUES ('ŸIL01', '1401', 'Lª ThÞ Ngäc §iÖp', '0201092981', '', '711A01160033', '02/12/73', '0');
INSERT INTO `tblnhanvien` VALUES ('YNG01', '1314', 'NguyÔn ThÞ Nh­ ý', '0205264651', '', '711A02601457', '2/1/84', '0');
INSERT INTO `tblnhanvien` VALUES ('YNG02', '1608', 'Ng« Thµnh ý', '0206177216', '', '711A03446771', '1981', '1');

-- ----------------------------
-- Table structure for `tblphanhe`
-- ----------------------------
DROP TABLE IF EXISTS `tblphanhe`;
CREATE TABLE `tblphanhe` (
  `PhanHeID` tinyint(3) NOT NULL AUTO_INCREMENT,
  `TenPhanHe` char(255) NOT NULL,
  PRIMARY KEY (`PhanHeID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblphanhe
-- ----------------------------
INSERT INTO `tblphanhe` VALUES ('1', 'HIS');
INSERT INTO `tblphanhe` VALUES ('2', 'Duoc');

-- ----------------------------
-- Table structure for `tblthietbi`
-- ----------------------------
DROP TABLE IF EXISTS `tblthietbi`;
CREATE TABLE `tblthietbi` (
  `ThietBiID` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT 'Ma thiet bi',
  `TenThietBi` varchar(255) DEFAULT NULL COMMENT 'Ten thiet bi',
  `CauHinh` varchar(255) DEFAULT NULL,
  `LoaiThietBiID` int(8) DEFAULT NULL,
  `KhoaPhongID` int(8) DEFAULT NULL,
  `NgayMua` date DEFAULT NULL,
  `NgaySuDung` date DEFAULT NULL,
  `BaoHanh` int(1) DEFAULT NULL,
  `SoHopDong` varchar(25) DEFAULT NULL,
  `DonGia` double DEFAULT NULL,
  `TinhTrang` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ThietBiID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblthietbi
-- ----------------------------
INSERT INTO `tblthietbi` VALUES ('00000001', 'May vi tính', 'CPU: core2dual', null, null, '2011-01-01', '2011-02-01', '3', null, '10000000', null);
INSERT INTO `tblthietbi` VALUES ('00000002', 'Máy in Lazer Canon 3380', 'Tốc đọ in ?trang/p', null, null, null, null, '3', null, '10000000.1254789', null);

-- ----------------------------
-- Table structure for `tblthietbid`
-- ----------------------------
DROP TABLE IF EXISTS `tblthietbid`;
CREATE TABLE `tblthietbid` (
  `ThietBiID` int(8) NOT NULL DEFAULT '0',
  `NgaySua` date NOT NULL DEFAULT '0000-00-00',
  `NoiDung` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`ThietBiID`,`NgaySua`,`NoiDung`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tblthietbid
-- ----------------------------

-- ----------------------------
-- Table structure for `tbluser`
-- ----------------------------
DROP TABLE IF EXISTS `tbluser`;
CREATE TABLE `tbluser` (
  `UserID` varchar(12) NOT NULL,
  `Password` varchar(12) DEFAULT NULL,
  `PWDLevel2` varchar(12) DEFAULT NULL,
  `UserName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `LockedUser` bit(1) DEFAULT NULL,
  `LockedDate` datetime DEFAULT NULL,
  `LockedReason` varchar(150) DEFAULT NULL,
  `LastLogIn` timestamp NULL DEFAULT NULL,
  `LastChangedPassword` timestamp NULL DEFAULT NULL,
  `DeadlineOfUsing` datetime DEFAULT NULL,
  `Delegate` bit(1) DEFAULT NULL,
  `NhanVienID` varchar(12) DEFAULT NULL,
  `CreatedDate` timestamp NULL DEFAULT NULL,
  `Owner` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbluser
-- ----------------------------
INSERT INTO `tbluser` VALUES ('hieulv', '352007', null, 'Le Van Hieu', 'hieulvh@gmail.com', null, null, null, null, null, null, null, null, '2012-07-01 00:00:00', null);
INSERT INTO `tbluser` VALUES ('test', '123456', null, 'Test Man', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `tbluserright`
-- ----------------------------
DROP TABLE IF EXISTS `tbluserright`;
CREATE TABLE `tbluserright` (
  `PhanHeID` tinyint(3) NOT NULL DEFAULT '0',
  `UserID` varchar(12) NOT NULL DEFAULT '',
  `UserRight` tinytext,
  `CreateDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Owner` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`PhanHeID`,`UserID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `tbluserright_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `tbluser` (`UserID`),
  CONSTRAINT `tbluserright_ibfk_2` FOREIGN KEY (`PhanHeID`) REFERENCES `tblphanhe` (`PhanHeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbluserright
-- ----------------------------
INSERT INTO `tbluserright` VALUES ('1', 'hieulv', ';1;2;3;4;5;6;7;8;9;10;11;12;', '2012-09-10 13:19:21', 'hieulv');

-- ----------------------------
-- View structure for `abc`
-- ----------------------------
DROP VIEW IF EXISTS `abc`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `abc` AS select `tblgroupuser`.`GroupID` AS `GroupID`,`tblgroupuser`.`UserID` AS `UserID`,`tblgroupuser`.`CreateDate` AS `CreateDate`,`tblgroupuser`.`Owner` AS `Owner`,`tblgroupuser`.`Status` AS `Status` from `tblgroupuser` ;

-- ----------------------------
-- Procedure structure for `aaa`
-- ----------------------------
DROP PROCEDURE IF EXISTS `aaa`;
DELIMITER ;;
CREATE DEFINER=`erp4hUser`@`192.168.12.%` PROCEDURE `aaa`(out `@aaa` tinyint)
BEGIN
	#Routine body goes here...
	SELECT * FROM tblphanhe
	WHERE PhanHeID=@aaa;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for `spUser_SelectUserRight`
-- ----------------------------
DROP PROCEDURE IF EXISTS `spUser_SelectUserRight`;
DELIMITER ;;
CREATE DEFINER=`erp4hUser`@`192.168.12.%` PROCEDURE `spUser_SelectUserRight`(IN `@PhanHeID` tinyint(3))
BEGIN
	#Routine body goes here... 
	SELECT * 
	FROM tblUser AS t1
		INNER JOIN tblgroupuser AS t2 ON t1.UserID=t2.UserID
		INNER JOIN tblgroup AS t3 ON t2.GroupID=t3.GroupID
		INNER JOIN tblgroupright AS t4 ON t3.GroupID=t4.GroupID
		INNER JOIN tbluserright AS t5 ON t1.UserID=t5.UserID
	WHERE t4.PhanHeID=@PhanHeID;
END
;;
DELIMITER ;
